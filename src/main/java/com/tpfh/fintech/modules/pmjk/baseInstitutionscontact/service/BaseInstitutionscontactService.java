/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.baseInstitutionscontact.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.baseInstitutionscontact.entity.BaseInstitutionscontactEntity;
import java.util.HashMap;
import java.util.List;

public interface BaseInstitutionscontactService
extends IService<BaseInstitutionscontactEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public BaseInstitutionscontactEntity getInfoById(Long var1);

    public List<BaseInstitutionscontactEntity> getInfoList(HashMap<String, Object> var1);
}

