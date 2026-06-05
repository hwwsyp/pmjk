/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.releaseinfo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.releaseinfo.dao.ReleaseinfoDao;
import com.tpfh.fintech.modules.pmjk.releaseinfo.entity.ReleaseinfoEntity;
import com.tpfh.fintech.modules.pmjk.releaseinfo.service.ReleaseinfoService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="releaseinfoService")
public class ReleaseinfoServiceImpl
extends ServiceImpl<ReleaseinfoDao, ReleaseinfoEntity>
implements ReleaseinfoService {
    @Autowired
    private ReleaseinfoDao releaseinfoDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.releaseinfoDao.getReleaseinfoListForPage((Page<ReleaseinfoEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public ReleaseinfoEntity getInfoById(Long id) {
        return (ReleaseinfoEntity)((ReleaseinfoDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<ReleaseinfoEntity> getInfoList(HashMap<String, Object> params) {
        return ((ReleaseinfoDao)this.baseMapper).getReleaseinfoList(params);
    }

    @Override
    public ReleaseinfoEntity getInfoByProductidAndVersionnum(Long productid, Long versionnum) {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("productid", productid);
        params.put("versionnum", versionnum);
        List<ReleaseinfoEntity> infoList = this.getInfoList(params);
        if (infoList != null && infoList.size() > 0) {
            return infoList.get(0);
        }
        return null;
    }
}

