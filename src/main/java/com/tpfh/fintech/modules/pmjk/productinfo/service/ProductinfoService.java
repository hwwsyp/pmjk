/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.productinfo.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.productinfo.entity.ProductinfoEntity;
import java.util.HashMap;
import java.util.List;

public interface ProductinfoService
extends IService<ProductinfoEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public ProductinfoEntity getInfoById(Long var1);

    public ProductinfoEntity getInfoByProductidAndVersionnum(Long var1, Long var2);

    public List<ProductinfoEntity> getInfoList(HashMap<String, Object> var1);
}

