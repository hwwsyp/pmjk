package com.tpfh.fintech.modules.sys.service;

import java.util.HashMap;
import java.util.List;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.sys.entity.SysDeptEntity;

/**
 * 部门管理
 * 


 * @date 2017-06-20 15:23:47
 */
public interface SysDeptService extends IService<SysDeptEntity>{

	/**
	 * 查询子部门ID列表
	 * @param parentId  上级部门ID
	 */
	List<String> queryDetpIdList(Integer parentId);

	/**
	 * 获取子部门
	 */
	List<SysDeptEntity> getSubDeptList(Integer deptId);
	
	/**
	 * 部门列表
	 * @param sysDeptEntity
	 * @return
	 */
	List<SysDeptEntity> queryList(SysDeptEntity sysDeptEntity);

	SysDeptEntity queryObject(String deptId);

	PageUtils queryPage(HashMap<String, Object> params);

	List<SysDeptEntity> getDeptsList();

	List<SysDeptEntity> queryList(Long userId);

}
