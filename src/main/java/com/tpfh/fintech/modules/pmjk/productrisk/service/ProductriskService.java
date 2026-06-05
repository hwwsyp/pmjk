/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.productrisk.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.productrisk.entity.ProductriskEntity;
import java.util.HashMap;
import java.util.List;

public interface ProductriskService
extends IService<ProductriskEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public ProductriskEntity getInfoById(Long var1);

    public List<ProductriskEntity> getInfoList(HashMap<String, Object> var1);
}

