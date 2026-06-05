/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.productpostinvest.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.productpostinvest.entity.ProductpostinvestEntity;
import java.util.HashMap;
import java.util.List;

public interface ProductpostinvestService
extends IService<ProductpostinvestEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public ProductpostinvestEntity getInfoById(Long var1);

    public List<ProductpostinvestEntity> getInfoList(HashMap<String, Object> var1);
}

