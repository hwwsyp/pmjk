/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.cashflowdata.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.cashflowdata.dao.CashflowdataDao;
import com.tpfh.fintech.modules.pmjk.cashflowdata.entity.CashflowdataEntity;
import com.tpfh.fintech.modules.pmjk.cashflowdata.entity.CashflowdataVO;
import com.tpfh.fintech.modules.pmjk.cashflowdata.service.CashflowdataService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="cashflowdataService")
public class CashflowdataServiceImpl
extends ServiceImpl<CashflowdataDao, CashflowdataEntity>
implements CashflowdataService {
    @Autowired
    private CashflowdataDao cashflowdataDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.cashflowdataDao.getCashflowdataListForPage((Page<CashflowdataEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public CashflowdataEntity getInfoById(Long id) {
        return (CashflowdataEntity)((CashflowdataDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<CashflowdataEntity> getInfoList(HashMap<String, Object> params) {
        return ((CashflowdataDao)this.baseMapper).getCashflowdataList(params);
    }

    @Override
    public List<CashflowdataVO> getBuyRatio(String productshortname) {
        return this.cashflowdataDao.getBuyRatio(productshortname);
    }
}

