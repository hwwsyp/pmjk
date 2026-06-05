/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.productcontact.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.baseInstitutionscontact.entity.BaseInstitutionscontactEntity;
import com.tpfh.fintech.modules.pmjk.productcontact.entity.ProductcontactEntity;
import java.util.HashMap;
import java.util.List;

public interface ProductcontactService
extends IService<ProductcontactEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public ProductcontactEntity getInfoById(Long var1);

    public List<ProductcontactEntity> getInfoList(HashMap<String, Object> var1);

    public List<BaseInstitutionscontactEntity> getContactList(HashMap<String, Object> var1);
}

