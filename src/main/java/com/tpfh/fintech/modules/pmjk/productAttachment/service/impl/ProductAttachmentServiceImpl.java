/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.productAttachment.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.productAttachment.dao.ProductAttachmentDao;
import com.tpfh.fintech.modules.pmjk.productAttachment.entity.ProductAttachmentEntity;
import com.tpfh.fintech.modules.pmjk.productAttachment.service.ProductAttachmentService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="productAttachmentService")
public class ProductAttachmentServiceImpl
extends ServiceImpl<ProductAttachmentDao, ProductAttachmentEntity>
implements ProductAttachmentService {
    @Autowired
    private ProductAttachmentDao productAttachmentDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.productAttachmentDao.getProductAttachmentListForPage((Page<ProductAttachmentEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public ProductAttachmentEntity getInfoById(Long id) {
        return (ProductAttachmentEntity)((ProductAttachmentDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<ProductAttachmentEntity> getInfoList(HashMap<String, Object> params) {
        return ((ProductAttachmentDao)this.baseMapper).getProductAttachmentList(params);
    }
}

