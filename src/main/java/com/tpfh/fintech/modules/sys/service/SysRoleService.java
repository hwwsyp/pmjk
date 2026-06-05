/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.sys.entity.SysRoleEntity;
import java.util.List;
import java.util.Map;

public interface SysRoleService
extends IService<SysRoleEntity> {
    public PageUtils queryPage(Map<String, Object> var1);

    public void save(SysRoleEntity var1);

    public void update(SysRoleEntity var1);

    public void deleteBatch(Long[] var1);

    public List<Long> queryRoleIdList(Long var1);

    public List<SysRoleEntity> getRolesList();
}

