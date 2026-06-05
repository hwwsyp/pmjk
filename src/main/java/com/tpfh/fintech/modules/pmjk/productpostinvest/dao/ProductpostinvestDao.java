/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.pmjk.productpostinvest.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.productpostinvest.entity.ProductpostinvestEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductpostinvestDao
extends BaseMapper<ProductpostinvestEntity> {
    public List<ProductpostinvestEntity> getProductpostinvestListForPage(Page<ProductpostinvestEntity> var1, HashMap<String, Object> var2);

    public List<ProductpostinvestEntity> getProductpostinvestList(HashMap<String, Object> var1);

    public int addProductpostinvest(Map<String, String> var1);
}

