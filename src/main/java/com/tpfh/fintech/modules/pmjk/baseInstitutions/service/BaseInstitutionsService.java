/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.baseInstitutions.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.baseInstitutions.entity.BaseInstitutionsEntity;
import java.util.HashMap;
import java.util.List;

public interface BaseInstitutionsService
extends IService<BaseInstitutionsEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public BaseInstitutionsEntity getInfoById(Long var1);

    public List<BaseInstitutionsEntity> getInfoList(HashMap<String, Object> var1);
}

