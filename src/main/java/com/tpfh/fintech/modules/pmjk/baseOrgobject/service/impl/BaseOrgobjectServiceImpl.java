/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.baseOrgobject.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.baseOrgobject.dao.BaseOrgobjectDao;
import com.tpfh.fintech.modules.pmjk.baseOrgobject.entity.BaseOrgobjectEntity;
import com.tpfh.fintech.modules.pmjk.baseOrgobject.service.BaseOrgobjectService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="baseOrgobjectService")
public class BaseOrgobjectServiceImpl
extends ServiceImpl<BaseOrgobjectDao, BaseOrgobjectEntity>
implements BaseOrgobjectService {
    @Autowired
    private BaseOrgobjectDao baseOrgobjectDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.baseOrgobjectDao.getBaseOrgobjectListForPage((Page<BaseOrgobjectEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public BaseOrgobjectEntity getInfoById(Long id) {
        return (BaseOrgobjectEntity)((BaseOrgobjectDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<BaseOrgobjectEntity> getInfoList(HashMap<String, Object> params) {
        return ((BaseOrgobjectDao)this.baseMapper).getBaseOrgobjectList(params);
    }

    @Override
    public List<BaseOrgobjectEntity> getBaseOrgobjectListByOrgids(List<String> orgids) {
        return this.baseOrgobjectDao.getBaseOrgobjectListByOrgids(orgids);
    }
}

