/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 *  org.apache.ibatis.annotations.Param
 */
package com.tpfh.fintech.modules.pmjk.baseOrgobject.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.baseOrgobject.entity.BaseOrgobjectEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BaseOrgobjectDao
extends BaseMapper<BaseOrgobjectEntity> {
    public List<BaseOrgobjectEntity> getBaseOrgobjectListForPage(Page<BaseOrgobjectEntity> var1, HashMap<String, Object> var2);

    public List<BaseOrgobjectEntity> getBaseOrgobjectList(HashMap<String, Object> var1);

    public int addBaseOrgobject(Map<String, String> var1);

    public List<BaseOrgobjectEntity> getBaseOrgobjectListByOrgids(@Param(value="orgids") List<String> var1);
}

