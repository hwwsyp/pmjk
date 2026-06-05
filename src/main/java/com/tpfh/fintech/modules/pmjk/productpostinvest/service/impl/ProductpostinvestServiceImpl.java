/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.productpostinvest.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.productpostinvest.dao.ProductpostinvestDao;
import com.tpfh.fintech.modules.pmjk.productpostinvest.entity.ProductpostinvestEntity;
import com.tpfh.fintech.modules.pmjk.productpostinvest.service.ProductpostinvestService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="productpostinvestService")
public class ProductpostinvestServiceImpl
extends ServiceImpl<ProductpostinvestDao, ProductpostinvestEntity>
implements ProductpostinvestService {
    @Autowired
    private ProductpostinvestDao productpostinvestDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.productpostinvestDao.getProductpostinvestListForPage((Page<ProductpostinvestEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public ProductpostinvestEntity getInfoById(Long id) {
        return (ProductpostinvestEntity)((ProductpostinvestDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<ProductpostinvestEntity> getInfoList(HashMap<String, Object> params) {
        return ((ProductpostinvestDao)this.baseMapper).getProductpostinvestList(params);
    }
}

