/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.pmjk.stakeholder.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.pmjk.stakeholder.entity.StakeholderEntity;
import java.util.HashMap;
import java.util.List;

public interface StakeholderService
extends IService<StakeholderEntity> {
    public PageUtils queryPage(HashMap<String, Object> var1);

    public StakeholderEntity getInfoById(Long var1);

    public StakeholderEntity getInfoByProductidAndVersionnum(Long var1, Long var2);

    public List<StakeholderEntity> getInfoList(HashMap<String, Object> var1);
}

