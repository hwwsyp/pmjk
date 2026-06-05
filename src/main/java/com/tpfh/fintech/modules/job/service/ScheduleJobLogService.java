/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.job.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.job.entity.ScheduleJobLogEntity;
import java.util.Map;

public interface ScheduleJobLogService
extends IService<ScheduleJobLogEntity> {
    public PageUtils queryPage(Map<String, Object> var1);
}

