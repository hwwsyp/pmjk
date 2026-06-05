/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.service.IService
 */
package com.tpfh.fintech.modules.job.service;

import com.baomidou.mybatisplus.service.IService;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.modules.job.entity.ScheduleJobEntity;
import java.util.Map;

public interface ScheduleJobService
extends IService<ScheduleJobEntity> {
    public PageUtils queryPage(Map<String, Object> var1);

    public void save(ScheduleJobEntity var1);

    public void update(ScheduleJobEntity var1);

    public void deleteBatch(Long[] var1);

    public int updateBatch(Long[] var1, int var2);

    public void run(Long[] var1);

    public void pause(Long[] var1);

    public void resume(Long[] var1);
}

