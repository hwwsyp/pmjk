/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.institutionsrela.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.institutionsrela.dao.InstitutionsrelaDao;
import com.tpfh.fintech.modules.pmjk.institutionsrela.entity.InstitutionsrelaEntity;
import com.tpfh.fintech.modules.pmjk.institutionsrela.service.InstitutionsrelaService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="institutionsrelaService")
public class InstitutionsrelaServiceImpl
extends ServiceImpl<InstitutionsrelaDao, InstitutionsrelaEntity>
implements InstitutionsrelaService {
    @Autowired
    private InstitutionsrelaDao institutionsrelaDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.institutionsrelaDao.getInstitutionsrelaListForPage((Page<InstitutionsrelaEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public InstitutionsrelaEntity getInfoById(Long id) {
        return (InstitutionsrelaEntity)((InstitutionsrelaDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<InstitutionsrelaEntity> getInfoList(HashMap<String, Object> params) {
        return ((InstitutionsrelaDao)this.baseMapper).getInstitutionsrelaList(params);
    }

    @Override
    public InstitutionsrelaEntity getInfoByCreditenhanceidAndType(Long creditenhanceid, Long danbaorentype) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("creditenhanceid", creditenhanceid);
        params.put("danbaorentype", danbaorentype);
        List<InstitutionsrelaEntity> infoList = this.getInfoList(params);
        if (infoList != null && infoList.size() > 0) {
            return infoList.get(0);
        }
        return null;
    }
}

