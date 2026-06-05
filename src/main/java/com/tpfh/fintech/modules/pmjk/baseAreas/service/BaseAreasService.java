/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.baseAreas.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.baseAreas.entity.BaseAreasEntity;
import java.util.HashMap;
import java.util.List;

public interface BaseAreasService
extends IService<BaseAreasEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public BaseAreasEntity getInfoById(Long var1);

    public List<BaseAreasEntity> getInfoList(HashMap<String, Object> var1);

    public BaseAreasEntity getInfoByCode(String var1);
}

