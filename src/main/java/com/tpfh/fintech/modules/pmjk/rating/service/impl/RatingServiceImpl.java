/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.rating.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.rating.dao.RatingDao;
import com.tpfh.fintech.modules.pmjk.rating.entity.RatingEntity;
import com.tpfh.fintech.modules.pmjk.rating.service.RatingService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="ratingService")
public class RatingServiceImpl
extends ServiceImpl<RatingDao, RatingEntity>
implements RatingService {
    @Autowired
    private RatingDao ratingDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.ratingDao.getRatingListForPage((Page<RatingEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public RatingEntity getInfoById(Long id) {
        return (RatingEntity)((RatingDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<RatingEntity> getInfoList(HashMap<String, Object> params) {
        return ((RatingDao)this.baseMapper).getRatingList(params);
    }
}

