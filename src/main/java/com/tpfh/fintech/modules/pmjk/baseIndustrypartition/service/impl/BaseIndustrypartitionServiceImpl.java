/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.baseIndustrypartition.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.baseIndustrypartition.dao.BaseIndustrypartitionDao;
import com.tpfh.fintech.modules.pmjk.baseIndustrypartition.entity.BaseIndustrypartitionEntity;
import com.tpfh.fintech.modules.pmjk.baseIndustrypartition.service.BaseIndustrypartitionService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="baseIndustrypartitionService")
public class BaseIndustrypartitionServiceImpl
extends ServiceImpl<BaseIndustrypartitionDao, BaseIndustrypartitionEntity>
implements BaseIndustrypartitionService {
    @Autowired
    private BaseIndustrypartitionDao baseIndustrypartitionDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.baseIndustrypartitionDao.getBaseIndustrypartitionListForPage((Page<BaseIndustrypartitionEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public BaseIndustrypartitionEntity getInfoById(Long id) {
        return (BaseIndustrypartitionEntity)((BaseIndustrypartitionDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<BaseIndustrypartitionEntity> getInfoList(HashMap<String, Object> params) {
        return ((BaseIndustrypartitionDao)this.baseMapper).getBaseIndustrypartitionList(params);
    }

    @Override
    public PageUtils queryForSelectDialogPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.baseIndustrypartitionDao.getBaseIndustrypartitionListForSelectDialogPage((Page<BaseIndustrypartitionEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public BaseIndustrypartitionEntity getInfoByIndustrycode(String industrycode) {
        BaseIndustrypartitionEntity entity = new BaseIndustrypartitionEntity();
        entity.setIndustrycode(industrycode);
        return (BaseIndustrypartitionEntity)((BaseIndustrypartitionDao)this.baseMapper).selectOne(entity);
    }
}

