/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.groupvotinginfo.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.groupvotinginfo.entity.GroupvotinginfoEntity;
import java.util.HashMap;
import java.util.List;

public interface GroupvotinginfoService
extends IService<GroupvotinginfoEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public GroupvotinginfoEntity getInfoById(Long var1);

    public List<GroupvotinginfoEntity> getInfoList(HashMap<String, Object> var1);
}

