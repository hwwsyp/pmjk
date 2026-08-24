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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="productcontactService")
public class ProductcontactServiceImpl
extends ServiceImpl<ProductcontactDao, ProductcontactEntity>
implements ProductcontactService {
    private static final String RELEASEINFO_SOURCE_NAME = "PMJKRELEASEINFO";

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

    @Override
    public void syncReleaseinfoContacts(Long releaseinfoId, List<Long> contactIds) {
        if (releaseinfoId == null) {
            return;
        }
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("sourceid", releaseinfoId);
        params.put("sourcename", RELEASEINFO_SOURCE_NAME);
        List<ProductcontactEntity> existingList = this.getInfoList(params);
        if (existingList != null && !existingList.isEmpty()) {
            ArrayList<Long> removeIds = new ArrayList<Long>();
            for (ProductcontactEntity entity : existingList) {
                removeIds.add(entity.getId());
            }
            this.deleteBatchIds(removeIds);
        }
        if (contactIds == null || contactIds.isEmpty()) {
            return;
        }
        for (Long contactId : contactIds) {
            if (contactId == null) {
                continue;
            }
            ProductcontactEntity entity = new ProductcontactEntity();
            entity.setContactid(contactId);
            entity.setSourceid(releaseinfoId);
            entity.setSourcename(RELEASEINFO_SOURCE_NAME);
            this.insert(entity);
        }
    }

    @Override
    public List<Long> getContactIdsByReleaseinfoId(Long releaseinfoId) {
        ArrayList<Long> contactIds = new ArrayList<Long>();
        if (releaseinfoId == null) {
            return contactIds;
        }
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("sourceid", releaseinfoId);
        params.put("sourcename", RELEASEINFO_SOURCE_NAME);
        List<ProductcontactEntity> list = this.getInfoList(params);
        if (list == null || list.isEmpty()) {
            return contactIds;
        }
        for (ProductcontactEntity entity : list) {
            contactIds.add(entity.getContactid());
        }
        return contactIds;
    }
}

