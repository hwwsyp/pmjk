/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.cashflowdata.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.cashflowdata.entity.CashflowdataEntity;
import com.tpfh.fintech.modules.pmjk.cashflowdata.entity.CashflowdataVO;
import java.util.HashMap;
import java.util.List;

public interface CashflowdataService
extends IService<CashflowdataEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public CashflowdataEntity getInfoById(Long var1);

    public List<CashflowdataEntity> getInfoList(HashMap<String, Object> var1);

    public List<CashflowdataVO> getBuyRatio(String var1);
}

