/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tpfh.fintech.modules.sys.entity.SysMenuEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysMenuDao
extends BaseMapper<SysMenuEntity> {
    public List<SysMenuEntity> queryListParentId(Long var1);

    public List<SysMenuEntity> queryNotButtonList();
}

