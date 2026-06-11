package com.tpfh.fintech.modules.sys.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tpfh.fintech.common.utils.Constant;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.sys.entity.SysDeptEntity;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import com.tpfh.fintech.modules.sys.service.SysDeptService;
import com.tpfh.fintech.modules.sys.service.SysUserService;


/**
 * 部门管理
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController extends AbstractController {
	@Autowired
	private SysDeptService sysDeptService;
	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:dept:list")
	public List<SysDeptEntity> list(){
		List<SysDeptEntity> deptList = sysDeptService.queryList(getUserId());
		return deptList;
	}
	
	/**
	 * 选择部门(添加、修改菜单)
	 */
	@RequestMapping("/select")
	@RequiresPermissions("sys:dept:select")
	public R select(){
		List<SysDeptEntity> deptList = sysDeptService.queryList(getUserId());
		return R.ok().put("deptList", deptList);
	}

	/**
	 * 上级部门Id(管理员则为0)
	 */
	@RequestMapping("/info")
	@RequiresPermissions("sys:dept:list")
	public R info(){
		Integer deptId = null;
		if(getUserId() != Constant.SUPER_ADMIN){
			SysDeptEntity dept = sysDeptService.selectById(super.getDeptId());
			deptId = dept.getParentId();
		}
		return R.ok().put("deptId", deptId);
	}
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{deptId}")
	@RequiresPermissions("sys:dept:info")
	public R info(@PathVariable("deptId") String deptId){
		SysDeptEntity dept = sysDeptService.queryObject(deptId);
		
		return R.ok().put("dept", dept);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:dept:save")
	public R save(@RequestBody SysDeptEntity dept){
		sysDeptService.insert(dept);
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:dept:update")
	public R update(@RequestBody SysDeptEntity dept){
		sysDeptService.updateById(dept);
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:dept:delete")
	public R delete(Integer deptId){
		//判断是否有子部门
		List<String> deptList = sysDeptService.queryDetpIdList(deptId);
		if(!deptList.isEmpty()){
			return R.error("请先删除子部门");
		}
		List<SysUserEntity> userList = sysUserService.getUsersListByDeptId(deptId);
		if(!userList.isEmpty()){
			return R.error("该部门下存在用户，无法删除");
		}
		SysDeptEntity dept = new SysDeptEntity();
		dept.setDeptId(deptId);
		dept.setDelFlag(1);
		sysDeptService.updateById(dept);
		return R.ok();
	}
	
	@RequestMapping("/getDeptsList")
	public R getDeptsList(){
		List<SysDeptEntity> deptsList=sysDeptService.getDeptsList();
		return R.ok().put("deptsList", deptsList);
	}
	
	/**
	 * 
	 * 获取子部门
	 * @return
	 * List<SysDeptEntity>
	 * @exception:
	 */
	@RequestMapping("/getSubDeptList/{deptId}")
	public R  getSubDeptIdList(@PathVariable("deptId") Integer deptId){
		List<SysDeptEntity> deptsList = sysDeptService.getSubDeptList(deptId);
		return R.ok().put("deptsList", deptsList);
	}
	
}
