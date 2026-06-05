/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.pmjk.stakeholder.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.tpfh.fintech.modules.pmjk.stakeholder.entity.StakeholderEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StakeholderDao
extends BaseMapper<StakeholderEntity> {
    public List<StakeholderEntity> getStakeholderListForPage(Page<StakeholderEntity> var1, HashMap<String, Object> var2);

    public List<StakeholderEntity> getStakeholderList(HashMap<String, Object> var1);

    public int addStakeholder(Map<String, String> var1);
}

