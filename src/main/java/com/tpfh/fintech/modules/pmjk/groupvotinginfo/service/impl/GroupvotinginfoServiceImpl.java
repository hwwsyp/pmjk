/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.pmjk.groupvotinginfo.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.groupvotinginfo.dao.GroupvotinginfoDao;
import com.tpfh.fintech.modules.pmjk.groupvotinginfo.entity.GroupvotinginfoEntity;
import com.tpfh.fintech.modules.pmjk.groupvotinginfo.service.GroupvotinginfoService;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="groupvotinginfoService")
public class GroupvotinginfoServiceImpl
extends ServiceImpl<GroupvotinginfoDao, GroupvotinginfoEntity>
implements GroupvotinginfoService {
    @Autowired
    private GroupvotinginfoDao groupvotinginfoDao;

    @Override
    public PageUtils queryPage(HashMap<String, Object> params) {
        Page page = new Page();
        Integer pageno = Integer.parseInt(params.get("page").toString());
        Integer limit = Integer.parseInt(params.get("limit").toString());
        page.setCurrent(pageno.intValue());
        page.setSize(limit.intValue());
        page.setRecords(this.groupvotinginfoDao.getGroupvotinginfoListForPage((Page<GroupvotinginfoEntity>)page, params));
        return new PageUtils(page);
    }

    @Override
    public GroupvotinginfoEntity getInfoById(Long id) {
        return (GroupvotinginfoEntity)((GroupvotinginfoDao)this.baseMapper).selectById(id);
    }

    @Override
    public List<GroupvotinginfoEntity> getInfoList(HashMap<String, Object> params) {
        return ((GroupvotinginfoDao)this.baseMapper).getGroupvotinginfoList(params);
    }
}

