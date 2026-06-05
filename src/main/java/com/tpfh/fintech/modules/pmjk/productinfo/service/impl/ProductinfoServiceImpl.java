/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.productinfo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.productinfo.dao.ProductinfoDao;
import com.tpfh.fintech.modules.pmjk.productinfo.entity.ProductinfoEntity;
import com.tpfh.fintech.modules.pmjk.productinfo.service.ProductinfoService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="productinfoService")
public class ProductinfoServiceImpl
extends ServiceImpl<ProductinfoDao, ProductinfoEntity>
implements ProductinfoService {
    @Autowired
    private ProductinfoDao productinfoDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.productinfoDao.getProductinfoListForPage((Page<ProductinfoEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public ProductinfoEntity getInfoById(Long id) {
        return (ProductinfoEntity)((ProductinfoDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<ProductinfoEntity> getInfoList(HashMap<String, Object> params) {
        return ((ProductinfoDao)this.baseMapper).getProductinfoList(params);
    }

    @Override
    public ProductinfoEntity getInfoByProductidAndVersionnum(Long productid, Long versionnum) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("productid", productid);
        params.put("versionnum", versionnum);
        List<ProductinfoEntity> infoList = this.getInfoList(params);
        if (infoList != null && infoList.size() > 0) {
            return infoList.get(0);
        }
        return null;
    }
}

