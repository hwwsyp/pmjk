/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.creditenhance.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.creditenhance.entity.CreditenhanceEntity;
import java.util.HashMap;
import java.util.List;

public interface CreditenhanceService
extends IService<CreditenhanceEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public CreditenhanceEntity getInfoById(Long var1);

    public List<CreditenhanceEntity> getInfoList(HashMap<String, Object> var1);

    public CreditenhanceEntity getInfoByProductidAndVersionnum(Long var1, Long var2);
}

