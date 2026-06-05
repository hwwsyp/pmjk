/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.modules.sys.entity.SysUserRoleEntity;
import java.util.List;

public interface SysUserRoleService
extends IService<SysUserRoleEntity> {
    public void saveOrUpdate(Long var1, List<Long> var2);

    public List<Long> queryRoleIdList(Long var1);

    public int deleteBatch(Long[] var1);
}

