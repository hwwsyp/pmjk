/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 *  org.apache.ibatis.annotations.Param
 *  org.apache.ibatis.annotations.Select
 */
package com.tpfh.fintech.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserDao
extends BaseMapper<SysUserEntity> {
    public List<String> queryAllPerms(Long var1);

    public List<Long> queryAllMenuId(Long var1);

    public SysUserEntity queryByUserName(String var1);

    public List<SysUserEntity> getUsersList(@Param(value="word") String var1);

    public List<SysUserEntity> getUsersListByDeptId(@Param(value="deptId") Integer var1);

    public List<SysUserEntity> getUsersListByRoleId(@Param(value="roleId") String var1);

    public List<SysUserEntity> getUserList(Page<SysUserEntity> var1, HashMap<String, Object> var2);

    public List<SysUserEntity> getUserList(HashMap<String, String> var1);

    public int addUser(Map<String, String> var1);

    public void addUserRole(Map<String, String> var1);

    @Select(value={"select\tgroup_concat(t2.role_id) roleIds FROM\tsys_user t1 left join sys_user_role t2 on t1.user_id = t2.user_id where t1.username = #{parentUserCode} group by t1.user_id"})
    public String getParentRoles(@Param(value="parentUserCode") String var1);

    public List<SysUserEntity> getAllUsersList();

    public SysUserEntity getUserBaseInfo(@Param(value="userName") String var1);
}

