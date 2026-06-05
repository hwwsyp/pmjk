/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tpfh.fintech.modules.sys.entity.SysUserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysUserTokenDao
extends BaseMapper<SysUserTokenEntity> {
    public SysUserTokenEntity queryByToken(String var1);
}

