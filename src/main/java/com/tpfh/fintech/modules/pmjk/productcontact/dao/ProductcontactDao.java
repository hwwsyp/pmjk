/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.pmjk.productcontact.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.baseInstitutionscontact.entity.BaseInstitutionscontactEntity;
import com.tpfh.fintech.modules.pmjk.productcontact.entity.ProductcontactEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductcontactDao
extends BaseMapper<ProductcontactEntity> {
    public List<ProductcontactEntity> getProductcontactListForPage(Page<ProductcontactEntity> var1, HashMap<String, Object> var2);

    public List<ProductcontactEntity> getProductcontactList(HashMap<String, Object> var1);

    public int addProductcontact(Map<String, String> var1);

    public List<BaseInstitutionscontactEntity> getContactList(HashMap<String, Object> var1);
}

