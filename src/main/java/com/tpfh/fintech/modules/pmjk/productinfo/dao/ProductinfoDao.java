/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.pmjk.productinfo.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.productinfo.entity.ProductinfoEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductinfoDao
extends BaseMapper<ProductinfoEntity> {
    public List<ProductinfoEntity> getProductinfoListForPage(Page<ProductinfoEntity> var1, HashMap<String, Object> var2);

    public List<ProductinfoEntity> getProductinfoList(HashMap<String, Object> var1);

    public int addProductinfo(Map<String, String> var1);
}

