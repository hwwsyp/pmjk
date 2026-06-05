/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.productcontact.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.baseInstitutionscontact.entity.BaseInstitutionscontactEntity;
import com.tpfh.fintech.modules.pmjk.productcontact.dao.ProductcontactDao;
import com.tpfh.fintech.modules.pmjk.productcontact.entity.ProductcontactEntity;
import com.tpfh.fintech.modules.pmjk.productcontact.service.ProductcontactService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="productcontactService")
public class ProductcontactServiceImpl
extends ServiceImpl<ProductcontactDao, ProductcontactEntity>
implements ProductcontactService {
    @Autowired
    private ProductcontactDao productcontactDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.productcontactDao.getProductcontactListForPage((Page<ProductcontactEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public ProductcontactEntity getInfoById(Long id) {
        return (ProductcontactEntity)((ProductcontactDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<ProductcontactEntity> getInfoList(HashMap<String, Object> params) {
        return ((ProductcontactDao)this.baseMapper).getProductcontactList(params);
    }

    @Override
    public List<BaseInstitutionscontactEntity> getContactList(HashMap<String, Object> params) {
        return ((ProductcontactDao)this.baseMapper).getContactList(params);
    }
}

