package com.tpfh.fintech.modules.sys.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.AESDecryptorUtil;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.sys.dao.SysUserDao;
import com.tpfh.fintech.modules.sys.entity.SysDeptEntity;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import com.tpfh.fintech.modules.sys.service.SysDeptService;
import com.tpfh.fintech.modules.sys.service.SysUserRoleService;
import com.tpfh.fintech.modules.sys.service.SysUserService;


/**
 * 系统用户
 * 
 * @author tpfh
 * @email tpfh@tpfh.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
	private SysUserDao sysUserDao;


	@Override
	public PageUtils queryPage(HashMap<String,Object> params) {
		Page<SysUserEntity> page = new Page<SysUserEntity>();
		Integer pageno = Integer.parseInt(params.get("page").toString());
		Integer limit = Integer.parseInt(params.get("limit").toString());
		page.setCurrent(pageno.intValue());
		page.setSize(limit.intValue());
		page.setRecords(sysUserDao.getUserList(page, params));

		return new PageUtils(page);
	}

	@Override
	public List<String> queryAllPerms(Long userId) {
		return baseMapper.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	public SysUserEntity queryByUserName(String username) {
		return baseMapper.queryByUserName(username);
	}

	@Override
	@Transactional
	public void save(SysUserEntity user) {
		user.setCreateTime(new Date());
		//sha256加密
		String salt = RandomStringUtils.randomAlphanumeric(20);
		user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
		user.setSalt(salt);
		this.insert(user);

		//检查角色是否越权
		//checkRole(user);

		//保存用户与角色关系
		sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void update(SysUserEntity user) {
		try {
			if(StringUtils.isBlank(user.getPassword())){
				user.setPassword(null);
			}else{

				//add by owen in 20260520 解密
				String encryptedData = user.getPassword(); // Base64格式的加密数据
				String key = "jk%fdsa2QERX_2+2"; // 16字节密钥（128位）
				String iv = "1iopi7&FDS123456"; // 16字节IV

				String password = AESDecryptorUtil.decrypt(encryptedData, key, iv);
				user.setPassword(new Sha256Hash(password, user.getSalt()).toHex());

			}
			this.updateById(user);

			//检查角色是否越权
			//checkRole(user);

			//保存用户与角色关系
			sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
		}catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void deleteBatch(Long[] userId) {
		this.deleteBatchIds(Arrays.asList(userId));
	}

	@Override
	public boolean updatePassword(Long userId, String password, String newPassword) {
		SysUserEntity userEntity = new SysUserEntity();
		userEntity.setPassword(newPassword);
		return this.update(userEntity,
				new EntityWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password));
	}

	@Override
	public List<SysUserEntity> getUsersList(String word) {
		return baseMapper.getUsersList(word);
	}

	@Override
	public List<SysUserEntity> getAllUsersList() {
		return baseMapper.getAllUsersList();
	}

	@Override
	public List<SysUserEntity> getUsersListByDeptId(Integer deptId) {
		return baseMapper.getUsersListByDeptId(deptId);
	}

	@Override
	public List<SysUserEntity> getUsersListByRoleId(String roleId) {
		return baseMapper.getUsersListByRoleId(roleId);
	}

	@Override
	public SysUserEntity getUserInfo(Long userId) {
		SysUserEntity user = baseMapper.selectById(userId);

		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);

		//获取用户所属的部门信息
		SysDeptEntity sysDept = sysDeptService.selectById(user.getDeptId());
		user.setDeptName(sysDept.getName());
		return user;
	}

}
