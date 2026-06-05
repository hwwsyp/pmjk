/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.baseAreas.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.baseAreas.dao.BaseAreasDao;
import com.tpfh.fintech.modules.pmjk.baseAreas.entity.BaseAreasEntity;
import com.tpfh.fintech.modules.pmjk.baseAreas.service.BaseAreasService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="baseAreasService")
public class BaseAreasServiceImpl
extends ServiceImpl<BaseAreasDao, BaseAreasEntity>
implements BaseAreasService {
    @Autowired
    private BaseAreasDao baseAreasDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.baseAreasDao.getBaseAreasListForPage((Page<BaseAreasEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public BaseAreasEntity getInfoById(Long id) {
        return (BaseAreasEntity)((BaseAreasDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<BaseAreasEntity> getInfoList(HashMap<String, Object> params) {
        return ((BaseAreasDao)this.baseMapper).getBaseAreasList(params);
    }

    @Override
    public BaseAreasEntity getInfoByCode(String code) {
        BaseAreasEntity entity = new BaseAreasEntity();
        entity.setCode(code);
        return (BaseAreasEntity)((BaseAreasDao)this.baseMapper).selectOne(entity);
    }
}

