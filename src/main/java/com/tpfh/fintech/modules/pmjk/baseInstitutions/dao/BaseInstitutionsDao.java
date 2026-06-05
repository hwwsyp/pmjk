/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.pmjk.baseInstitutions.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.baseInstitutions.entity.BaseInstitutionsEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseInstitutionsDao
extends BaseMapper<BaseInstitutionsEntity> {
    public List<BaseInstitutionsEntity> getBaseInstitutionsListForPage(Page<BaseInstitutionsEntity> var1, HashMap<String, Object> var2);

    public List<BaseInstitutionsEntity> getBaseInstitutionsList(HashMap<String, Object> var1);

    public int addBaseInstitutions(Map<String, String> var1);
}

