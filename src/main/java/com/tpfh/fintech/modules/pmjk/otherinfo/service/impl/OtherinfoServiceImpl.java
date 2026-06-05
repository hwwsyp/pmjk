/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.otherinfo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.otherinfo.dao.OtherinfoDao;
import com.tpfh.fintech.modules.pmjk.otherinfo.entity.OtherinfoEntity;
import com.tpfh.fintech.modules.pmjk.otherinfo.service.OtherinfoService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="otherinfoService")
public class OtherinfoServiceImpl
extends ServiceImpl<OtherinfoDao, OtherinfoEntity>
implements OtherinfoService {
    @Autowired
    private OtherinfoDao otherinfoDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.otherinfoDao.getOtherinfoListForPage((Page<OtherinfoEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public OtherinfoEntity getInfoById(Long id) {
        return (OtherinfoEntity)((OtherinfoDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<OtherinfoEntity> getInfoList(HashMap<String, Object> params) {
        return ((OtherinfoDao)this.baseMapper).getOtherinfoList(params);
    }

    @Override
    public OtherinfoEntity getInfoByProductidAndVersionnum(Long productid, Long versionnum) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("productid", productid);
        params.put("versionnum", versionnum);
        List<OtherinfoEntity> infoList = this.getInfoList(params);
        if (infoList != null && infoList.size() > 0) {
            return infoList.get(0);
        }
        return null;
    }
}

