/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.sys.service;

import com.tpfh.fintech.modules.sys.entity.SysUserEntity;
import com.tpfh.fintech.modules.sys.entity.SysUserTokenEntity;
import java.util.Set;

public interface ShiroService {
    public Set<String> getUserPermissions(long var1);

    public SysUserTokenEntity queryByToken(String var1);

    public SysUserEntity queryUser(Long var1);
}

