/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import java.util.HashMap;
import java.util.List;

public interface SysUserService
extends IService<SysUserEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public List<String> queryAllPerms(Long var1);

    public List<Long> queryAllMenuId(Long var1);

    public SysUserEntity queryByUserName(String var1);

    public void save(SysUserEntity var1);

    public void update(SysUserEntity var1);

    public void deleteBatch(Long[] var1);

    public boolean updatePassword(Long var1, String var2, String var3);

    public List<SysUserEntity> getUsersList(String var1);

    public List<SysUserEntity> getUsersListByDeptId(Integer var1);

    public List<SysUserEntity> getUsersListByRoleId(String var1);

    public List<SysUserEntity> getAllUsersList();

    public SysUserEntity getUserInfo(Long var1);
}

