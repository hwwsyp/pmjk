/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.baseInstitutionscontact.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.baseInstitutionscontact.dao.BaseInstitutionscontactDao;
import com.tpfh.fintech.modules.pmjk.baseInstitutionscontact.entity.BaseInstitutionscontactEntity;
import com.tpfh.fintech.modules.pmjk.baseInstitutionscontact.service.BaseInstitutionscontactService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="baseInstitutionscontactService")
public class BaseInstitutionscontactServiceImpl
extends ServiceImpl<BaseInstitutionscontactDao, BaseInstitutionscontactEntity>
implements BaseInstitutionscontactService {
    @Autowired
    private BaseInstitutionscontactDao baseInstitutionscontactDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.baseInstitutionscontactDao.getBaseInstitutionscontactListForPage((Page<BaseInstitutionscontactEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public BaseInstitutionscontactEntity getInfoById(Long id) {
        return (BaseInstitutionscontactEntity)((BaseInstitutionscontactDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<BaseInstitutionscontactEntity> getInfoList(HashMap<String, Object> params) {
        return ((BaseInstitutionscontactDao)this.baseMapper).getBaseInstitutionscontactList(params);
    }
}

