/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.BaseMapper
 *  org.apache.ibatis.annotations.Mapper
 */
package com.tpfh.fintech.modules.job.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.tpfh.fintech.modules.job.entity.ScheduleJobEntity;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ScheduleJobDao
extends BaseMapper<ScheduleJobEntity> {
    public int updateBatch(Map<String, Object> var1);
}

