/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.EntityWrapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.apache.commons.lang.StringUtils
 *  org.springframework.stereotype.Service
 */
package com.tpfh.fintech.modules.job.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.Query;
import com.tpfh.fintech.modules.job.dao.ScheduleJobLogDao;
import com.tpfh.fintech.modules.job.entity.ScheduleJobLogEntity;
import com.tpfh.fintech.modules.job.service.ScheduleJobLogService;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service(value="scheduleJobLogService")
public class ScheduleJobLogServiceImpl
extends ServiceImpl<ScheduleJobLogDao, ScheduleJobLogEntity>
implements ScheduleJobLogService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String jobId = (String)params.get("jobId");
        Page page = this.selectPage(new Query(params).getPage(), new EntityWrapper().like(StringUtils.isNotBlank((String)jobId), "job_id", jobId).orderBy("log_id", false));
        return new PageUtils(page);
    }
}

