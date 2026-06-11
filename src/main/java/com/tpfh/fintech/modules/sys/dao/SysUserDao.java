package com.tpfh.fintech.modules.sys.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;

/**
 * 系统用户
 * 
 * @author tpfh
 * @email tpfh@tpfh.com
 * @date 2016年9月18日 上午9:34:11
 */
@Mapper
public interface SysUserDao extends BaseMapper<SysUserEntity> {
	
	/**
	 * 查询用户的所有权限
	 * @param userId  用户ID
	 */
	List<String> queryAllPerms(Long userId);
	
	/**
	 * 查询用户的所有菜单ID
	 */
	List<Long> queryAllMenuId(Long userId);
	
	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserEntity queryByUserName(String username);

	List<SysUserEntity> getUsersList(@Param("word") String word);

	List<SysUserEntity> getUsersListByDeptId(@Param("deptId") Integer deptId);

	List<SysUserEntity> getUsersListByRoleId(@Param("roleId") String roleId);

	List<SysUserEntity> getUserList(Page<SysUserEntity> page, HashMap<String, Object> params);

	List<SysUserEntity> getUserList(HashMap<String, String> params);

	int addUser(Map<String, String> map);

	void addUserRole(Map<String, String> map);
	//查询用户的角色
    @Select("select	group_concat(t2.role_id) roleIds FROM	sys_user t1 left join sys_user_role t2 on t1.user_id = t2.user_id where t1.username = #{parentUserCode} group by t1.user_id")
	String getParentRoles(@Param("parentUserCode") String parentUserCode);

	List<SysUserEntity> getAllUsersList();

	//根据工号查询用户基本信息
	SysUserEntity getUserBaseInfo(@Param("userName")String staffId);

}
