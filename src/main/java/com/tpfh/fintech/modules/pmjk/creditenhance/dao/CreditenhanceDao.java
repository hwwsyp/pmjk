/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.pmjk.creditenhance.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.creditenhance.entity.CreditenhanceEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CreditenhanceDao
extends BaseMapper<CreditenhanceEntity> {
    public List<CreditenhanceEntity> getCreditenhanceListForPage(Page<CreditenhanceEntity> var1, HashMap<String, Object> var2);

    public List<CreditenhanceEntity> getCreditenhanceList(HashMap<String, Object> var1);

    public int addCreditenhance(Map<String, String> var1);
}

