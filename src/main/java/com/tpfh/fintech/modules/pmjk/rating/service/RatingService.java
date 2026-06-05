/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.rating.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.rating.entity.RatingEntity;
import java.util.HashMap;
import java.util.List;

public interface RatingService
extends IService<RatingEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public RatingEntity getInfoById(Long var1);

    public List<RatingEntity> getInfoList(HashMap<String, Object> var1);
}

