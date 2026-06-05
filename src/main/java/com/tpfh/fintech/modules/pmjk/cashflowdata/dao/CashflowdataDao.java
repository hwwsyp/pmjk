/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 *  org.apache.ibatis.annotations.Param
 */
package com.tpfh.fintech.modules.pmjk.cashflowdata.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.cashflowdata.entity.CashflowdataEntity;
import com.tpfh.fintech.modules.pmjk.cashflowdata.entity.CashflowdataVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CashflowdataDao
extends BaseMapper<CashflowdataEntity> {
    public List<CashflowdataEntity> getCashflowdataListForPage(Page<CashflowdataEntity> var1, HashMap<String, Object> var2);

    public List<CashflowdataEntity> getCashflowdataList(HashMap<String, Object> var1);

    public int addCashflowdata(Map<String, String> var1);

    public List<CashflowdataVO> getBuyRatio(@Param(value="productshortname") String var1);
}

