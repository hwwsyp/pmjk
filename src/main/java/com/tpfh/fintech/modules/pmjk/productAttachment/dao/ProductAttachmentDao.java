/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.pmjk.productAttachment.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.productAttachment.entity.ProductAttachmentEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductAttachmentDao
extends BaseMapper<ProductAttachmentEntity> {
    public List<ProductAttachmentEntity> getProductAttachmentListForPage(Page<ProductAttachmentEntity> var1, HashMap<String, Object> var2);

    public List<ProductAttachmentEntity> getProductAttachmentList(HashMap<String, Object> var1);

    public int addProductAttachment(Map<String, String> var1);
}

