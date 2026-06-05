/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.creditenhance.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.creditenhance.dao.CreditenhanceDao;
import com.tpfh.fintech.modules.pmjk.creditenhance.entity.CreditenhanceEntity;
import com.tpfh.fintech.modules.pmjk.creditenhance.service.CreditenhanceService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="creditenhanceService")
public class CreditenhanceServiceImpl
extends ServiceImpl<CreditenhanceDao, CreditenhanceEntity>
implements CreditenhanceService {
    @Autowired
    private CreditenhanceDao creditenhanceDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.creditenhanceDao.getCreditenhanceListForPage((Page<CreditenhanceEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public CreditenhanceEntity getInfoById(Long id) {
        return (CreditenhanceEntity)((CreditenhanceDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<CreditenhanceEntity> getInfoList(HashMap<String, Object> params) {
        return ((CreditenhanceDao)this.baseMapper).getCreditenhanceList(params);
    }

    @Override
    public CreditenhanceEntity getInfoByProductidAndVersionnum(Long productid, Long versionnum) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("productid", productid);
        params.put("versionnum", versionnum);
        List<CreditenhanceEntity> infoList = this.getInfoList(params);
        if (infoList != null && infoList.size() > 0) {
            return infoList.get(0);
        }
        return null;
    }
}

