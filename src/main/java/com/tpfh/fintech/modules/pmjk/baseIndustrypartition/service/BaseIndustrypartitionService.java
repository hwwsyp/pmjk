/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.baseIndustrypartition.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.baseIndustrypartition.entity.BaseIndustrypartitionEntity;
import java.util.HashMap;
import java.util.List;

public interface BaseIndustrypartitionService
extends IService<BaseIndustrypartitionEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public PageUtils queryForSelectDialogPage(HashMap<String, Object> var1);

    public BaseIndustrypartitionEntity getInfoById(Long var1);

    public List<BaseIndustrypartitionEntity> getInfoList(HashMap<String, Object> var1);

    public BaseIndustrypartitionEntity getInfoByIndustrycode(String var1);
}

