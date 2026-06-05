/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.sys.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tpfh.fintech.modules.sys.entity.SysDeptEntity;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SysDeptDao
extends BaseMapper<SysDeptEntity> {
    public List<String> queryDetpIdList(Integer var1);

    public SysDeptEntity getDeptByName(String var1);

    public void updateDeptByDeptId(SysDeptEntity var1);

    public List<SysDeptEntity> getDepts();

    public List<SysDeptEntity> queryList(SysDeptEntity var1);

    public SysDeptEntity queryObject(String var1);

    public void updateOrg();

    public List<SysDeptEntity> getDeptsList();

    public List<SysDeptEntity> queryList(Long var1);

    public List<SysDeptEntity> getAllDeptList();
}

