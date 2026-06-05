/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.baseInstitutions.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.baseInstitutions.dao.BaseInstitutionsDao;
import com.tpfh.fintech.modules.pmjk.baseInstitutions.entity.BaseInstitutionsEntity;
import com.tpfh.fintech.modules.pmjk.baseInstitutions.service.BaseInstitutionsService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="baseInstitutionsService")
public class BaseInstitutionsServiceImpl
extends ServiceImpl<BaseInstitutionsDao, BaseInstitutionsEntity>
implements BaseInstitutionsService {
    @Autowired
    private BaseInstitutionsDao baseInstitutionsDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.baseInstitutionsDao.getBaseInstitutionsListForPage((Page<BaseInstitutionsEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public BaseInstitutionsEntity getInfoById(Long id) {
        return (BaseInstitutionsEntity)((BaseInstitutionsDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<BaseInstitutionsEntity> getInfoList(HashMap<String, Object> params) {
        return ((BaseInstitutionsDao)this.baseMapper).getBaseInstitutionsList(params);
    }
}

