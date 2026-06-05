/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.pmjk.baseAreas.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.baseAreas.entity.BaseAreasEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseAreasDao
extends BaseMapper<BaseAreasEntity> {
    public List<BaseAreasEntity> getBaseAreasListForPage(Page<BaseAreasEntity> var1, HashMap<String, Object> var2);

    public List<BaseAreasEntity> getBaseAreasList(HashMap<String, Object> var1);

    public int addBaseAreas(Map<String, String> var1);
}

