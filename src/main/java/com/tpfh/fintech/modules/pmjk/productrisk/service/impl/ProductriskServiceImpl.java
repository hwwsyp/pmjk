/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.productrisk.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.productrisk.dao.ProductriskDao;
import com.tpfh.fintech.modules.pmjk.productrisk.entity.ProductriskEntity;
import com.tpfh.fintech.modules.pmjk.productrisk.service.ProductriskService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="productriskService")
public class ProductriskServiceImpl
extends ServiceImpl<ProductriskDao, ProductriskEntity>
implements ProductriskService {
    @Autowired
    private ProductriskDao productriskDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.productriskDao.getProductriskListForPage((Page<ProductriskEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public ProductriskEntity getInfoById(Long id) {
        return (ProductriskEntity)((ProductriskDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<ProductriskEntity> getInfoList(HashMap<String, Object> params) {
        return ((ProductriskDao)this.baseMapper).getProductriskList(params);
    }
}

