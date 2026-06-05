/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.product.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.product.dao.ProductDao;
import com.tpfh.fintech.modules.pmjk.product.entity.ProductEntity;
import com.tpfh.fintech.modules.pmjk.product.service.ProductService;
import com.tpfh.fintech.modules.pmjk.product.vo.ProductVo;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="productService")
public class ProductServiceImpl
extends ServiceImpl<ProductDao, ProductEntity>
implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.productDao.getProductListForPage((Page<ProductVo>)page, params));
        return new PageUtils(page);
    }

    @Override
    public ProductEntity getInfoById(Long id) {
        return (ProductEntity)((ProductDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<ProductEntity> getInfoList(HashMap<String, Object> params) {
        return ((ProductDao)this.baseMapper).getProductList(params);
    }
}

