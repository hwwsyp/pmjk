/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tpfh.fintech.modules.sys.entity.SysRoleEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysRoleDao
extends BaseMapper<SysRoleEntity> {
    public List<Long> queryRoleIdList(Long var1);

    public List<SysRoleEntity> getRolesList();
}

