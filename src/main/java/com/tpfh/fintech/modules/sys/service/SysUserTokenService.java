/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.sys.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.R;
import com.tpfh.fintech.modules.sys.entity.SysUserTokenEntity;

public interface SysUserTokenService
extends IService<SysUserTokenEntity> {
    public R createToken(long var1);

    public void logout(long var1);
}

