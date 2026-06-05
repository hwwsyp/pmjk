/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.borrowinfo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.borrowinfo.dao.BorrowinfoDao;
import com.tpfh.fintech.modules.pmjk.borrowinfo.entity.BorrowinfoEntity;
import com.tpfh.fintech.modules.pmjk.borrowinfo.service.BorrowinfoService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="borrowinfoService")
public class BorrowinfoServiceImpl
extends ServiceImpl<BorrowinfoDao, BorrowinfoEntity>
implements BorrowinfoService {
    @Autowired
    private BorrowinfoDao borrowinfoDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.borrowinfoDao.getBorrowinfoListForPage((Page<BorrowinfoEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public BorrowinfoEntity getInfoById(Long id) {
        return (BorrowinfoEntity)((BorrowinfoDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<BorrowinfoEntity> getInfoList(HashMap<String, Object> params) {
        return ((BorrowinfoDao)this.baseMapper).getBorrowinfoList(params);
    }

    @Override
    public BorrowinfoEntity getInfoByCreditenhanceid(Long creditenhanceid) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("creditenhanceid", creditenhanceid);
        List<BorrowinfoEntity> infoList = this.getInfoList(params);
        if (infoList != null && infoList.size() > 0) {
            return infoList.get(0);
        }
        return null;
    }
}

