/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.pmjk.baseInstitutionscontact.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.baseInstitutionscontact.entity.BaseInstitutionscontactEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseInstitutionscontactDao
extends BaseMapper<BaseInstitutionscontactEntity> {
    public List<BaseInstitutionscontactEntity> getBaseInstitutionscontactListForPage(Page<BaseInstitutionscontactEntity> var1, HashMap<String, Object> var2);

    public List<BaseInstitutionscontactEntity> getBaseInstitutionscontactList(HashMap<String, Object> var1);

    public int addBaseInstitutionscontact(Map<String, String> var1);
}

