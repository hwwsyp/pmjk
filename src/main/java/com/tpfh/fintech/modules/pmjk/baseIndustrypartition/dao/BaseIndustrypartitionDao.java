/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.pmjk.baseIndustrypartition.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.baseIndustrypartition.entity.BaseIndustrypartitionEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseIndustrypartitionDao
extends BaseMapper<BaseIndustrypartitionEntity> {
    public List<BaseIndustrypartitionEntity> getBaseIndustrypartitionListForPage(Page<BaseIndustrypartitionEntity> var1, HashMap<String, Object> var2);

    public List<BaseIndustrypartitionEntity> getBaseIndustrypartitionListForSelectDialogPage(Page<BaseIndustrypartitionEntity> var1, HashMap<String, Object> var2);

    public List<BaseIndustrypartitionEntity> getBaseIndustrypartitionList(HashMap<String, Object> var1);

    public int addBaseIndustrypartition(Map<String, String> var1);
}

