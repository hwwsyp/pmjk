/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.stakeholder.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.stakeholder.dao.StakeholderDao;
import com.tpfh.fintech.modules.pmjk.stakeholder.entity.StakeholderEntity;
import com.tpfh.fintech.modules.pmjk.stakeholder.service.StakeholderService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="stakeholderService")
public class StakeholderServiceImpl
extends ServiceImpl<StakeholderDao, StakeholderEntity>
implements StakeholderService {
    @Autowired
    private StakeholderDao stakeholderDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.stakeholderDao.getStakeholderListForPage((Page<StakeholderEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public StakeholderEntity getInfoById(Long id) {
        return (StakeholderEntity)((StakeholderDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<StakeholderEntity> getInfoList(HashMap<String, Object> params) {
        return ((StakeholderDao)this.baseMapper).getStakeholderList(params);
    }

    @Override
    public StakeholderEntity getInfoByProductidAndVersionnum(Long productid, Long versionnum) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("productid", productid);
        params.put("versionnum", versionnum);
        List<StakeholderEntity> infoList = this.getInfoList(params);
        if (infoList != null && infoList.size() > 0) {
            return infoList.get(0);
        }
        return null;
    }
}

