/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.productAttachment.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.productAttachment.entity.ProductAttachmentEntity;
import java.util.HashMap;
import java.util.List;

public interface ProductAttachmentService
extends IService<ProductAttachmentEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public ProductAttachmentEntity getInfoById(Long var1);

    public List<ProductAttachmentEntity> getInfoList(HashMap<String, Object> var1);
}

