/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.pmjk.product.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.product.entity.ProductEntity;
import com.tpfh.fintech.modules.pmjk.product.vo.ProductVo;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductDao
extends BaseMapper<ProductEntity> {
    public List<ProductVo> getProductListForPage(Page<ProductVo> var1, HashMap<String, Object> var2);

    public List<ProductEntity> getProductList(HashMap<String, Object> var1);

    public int addProduct(Map<String, String> var1);
}

