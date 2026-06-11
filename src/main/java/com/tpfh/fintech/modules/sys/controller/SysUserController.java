package com.tpfh.fintech.modules.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.AESDecryptorUtil;
import com.tpfh.fintech.common.utils.Constant;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.common.validator.Assert;
import com.tpfh.fintech.common.validator.PasswordValidator;
import com.tpfh.fintech.common.validator.ValidatorUtils;
import com.tpfh.fintech.common.validator.group.AddGroup;
import com.tpfh.fintech.common.validator.group.UpdateGroup;
import com.tpfh.fintech.modules.sys.entity.SysRoleEntity;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import com.tpfh.fintech.modules.sys.form.PasswordForm;
import com.tpfh.fintech.modules.sys.service.SysRoleService;
import com.tpfh.fintech.modules.sys.service.SysUserRoleService;
import com.tpfh.fintech.modules.sys.service.SysUserService;

/**
 * 系统用户
 * 
 * @author tpfh
 * @email tpfh@tpfh.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(@RequestParam HashMap<String,Object> params){
		//只有超级管理员，才能查看所有管理员列表
		/*if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}*/
		params.put("userId", getUserId());
		PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("page", page);
	}

	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public R info(){
		SysUserEntity user = getUser();
		user.setSalt("");
		user.setPassword("");
		//获取用户所属的角色列表
		if(user.getUserId()== Constant.SUPER_ADMIN){
			List<SysRoleEntity> list = sysRoleService.selectByMap(null);
			List<Long> roleIdList = new ArrayList<Long>();
			for (SysRoleEntity role : list) {
				roleIdList.add(role.getRoleId());
			}
			user.setRoleIdList(roleIdList);
			return R.ok().put("user", user);
		}
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(user.getUserId());
		user.setRoleIdList(roleIdList);
		return R.ok().put("user", user);
	}

	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@PostMapping("/password")
	public R password(@RequestBody PasswordForm form){
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");

		String oldEncryptedData = form.getPassword();
		String newEncryptedData = form.getNewPassword();

		String key = "jk%fdsa2QERX_2+2";
		String iv = "1iopi7&FDS123456";

		try {
			String oldPassword = AESDecryptorUtil.decrypt(oldEncryptedData, key, iv);
			String newPasswordPlain = AESDecryptorUtil.decrypt(newEncryptedData, key, iv);
			PasswordValidator.validate(newPasswordPlain);

			String password = new Sha256Hash(oldPassword, getUser().getSalt()).toHex();
			String newPassword = new Sha256Hash(newPasswordPlain, getUser().getSalt()).toHex();

			boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
			if(!flag){
				return R.error("原密码不正确");
			}

			SysUserEntity user = new SysUserEntity();
			user.setUserId(getUserId());
			user.setStatus(1);
			sysUserService.updateById(user);

			return R.ok();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return R.error(e.getMessage() != null ? e.getMessage() : "更新密码失败");
		}
	}

	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	//@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		return R.ok().put("user", sysUserService.getUserInfo(userId));
	}

	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody JSONObject userJSON ){
		SysUserEntity user = userJSON.to(SysUserEntity.class);
		ValidatorUtils.validateEntity(user, AddGroup.class); 

		//add by owen in 20260511 因为要求新增用户第一次登录时需要修改个人密码，保证高强度，为此我们简单处理，利用用户状态位，
		//目前状态为1 正常、0 禁用；2 新增用户或者叫未修改密码
		//新增用户时，我们强制改为2状态
		user.setStatus(2);//表示未修改密码

		try {
			if(user.getType() != null && user.getType() == 1 && StringUtils.isNotBlank(user.getPassword())){
				String key = "jk%fdsa2QERX_2+2";
				String iv = "1iopi7&FDS123456";
				String password = AESDecryptorUtil.decrypt(user.getPassword(), key, iv);
				PasswordValidator.validate(password);
				user.setPassword(password);
			}

			user.setCreateUserId(getUserId());
			sysUserService.save(user);

		}catch (Exception e) { 
			logger.error(e.getMessage(),e);
			return R.error(e.getMessage() != null ? e.getMessage() : "保存用户失败");
		}

		return R.ok();
	}

	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		user.setCreateUserId(getUserId());
		sysUserService.update(user);

		return R.ok();
	}

	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}

		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}

		sysUserService.deleteBatch(userIds);

		return R.ok();
	}

	@RequestMapping("/getUsersList")
	public R getUsersList(String word){
		List<SysUserEntity> usersList=sysUserService.getUsersList(word);
		return R.ok().put("usersList", usersList);
	}

	@RequestMapping("/getAllUsersList")
	public R getAllUsersList(){
		List<SysUserEntity> usersList=sysUserService.getAllUsersList();
		return R.ok().put("usersList", usersList);
	}

	@RequestMapping("/getUsersListByDeptId")
	public R getUsersListByDeptId(Integer deptId){
		List<SysUserEntity> usersList=sysUserService.getUsersListByDeptId(deptId);
		return R.ok().put("usersList", usersList);
	}

	@RequestMapping("/getUsersListByRoleId")
	public R getUsersListByRoleId(String roleId){
		List<SysUserEntity> usersList=sysUserService.getUsersListByRoleId(roleId);
		return R.ok().put("usersList", usersList);
	}

}
