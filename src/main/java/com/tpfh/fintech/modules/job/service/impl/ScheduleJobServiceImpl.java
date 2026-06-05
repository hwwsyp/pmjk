/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.mapper.EntityWrapper
 *  com.baomidou.mybatisplus.plugins.Page
 *  com.baomidou.mybatisplus.service.impl.ServiceImpl
 *  org.apache.commons.lang.StringUtils
 *  org.quartz.CronTrigger
 *  org.quartz.Scheduler
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package com.tpfh.fintech.modules.job.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.tpfh.fintech.common.utils.Constant;
import com.tpfh.fintech.common.utils.PageUtils;
import com.tpfh.fintech.common.utils.Query;
import com.tpfh.fintech.modules.job.dao.ScheduleJobDao;
import com.tpfh.fintech.modules.job.entity.ScheduleJobEntity;
import com.tpfh.fintech.modules.job.service.ScheduleJobService;
import com.tpfh.fintech.modules.job.utils.ScheduleUtils;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.StringUtils;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value="scheduleJobService")
public class ScheduleJobServiceImpl
extends ServiceImpl<ScheduleJobDao, ScheduleJobEntity>
implements ScheduleJobService {
    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void init() {
        List scheduleJobList = this.selectList(null);
        for (ScheduleJobEntity scheduleJob : scheduleJobList) {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(this.scheduler, scheduleJob.getJobId());
            if (cronTrigger == null) {
                ScheduleUtils.createScheduleJob(this.scheduler, scheduleJob);
                continue;
            }
            ScheduleUtils.updateScheduleJob(this.scheduler, scheduleJob);
        }
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String beanName = (String)params.get("beanName");
        Page page = this.selectPage(new Query(params).getPage(), new EntityWrapper().like(StringUtils.isNotBlank((String)beanName), "bean_name", beanName));
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public void save(ScheduleJobEntity scheduleJob) {
        scheduleJob.setCreateTime(new Date());
        scheduleJob.setStatus(Constant.ScheduleStatus.NORMAL.getValue());
        this.insert(scheduleJob);
        ScheduleUtils.createScheduleJob(this.scheduler, scheduleJob);
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public void update(ScheduleJobEntity scheduleJob) {
        ScheduleUtils.updateScheduleJob(this.scheduler, scheduleJob);
        this.updateById(scheduleJob);
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public void deleteBatch(Long[] jobIds) {
        Long[] longArray = jobIds;
        int n = jobIds.length;
        int n2 = 0;
        while (n2 < n) {
            Long jobId = longArray[n2];
            ScheduleUtils.deleteScheduleJob(this.scheduler, jobId);
            ++n2;
        }
        this.deleteBatchIds(Arrays.asList(jobIds));
    }

    @Override
    public int updateBatch(Long[] jobIds, int status) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("list", jobIds);
        map.put("status", status);
        return ((ScheduleJobDao)this.baseMapper).updateBatch(map);
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public void run(Long[] jobIds) {
        Long[] longArray = jobIds;
        int n = jobIds.length;
        int n2 = 0;
        while (n2 < n) {
            Long jobId = longArray[n2];
            ScheduleUtils.run(this.scheduler, (ScheduleJobEntity)this.selectById(jobId));
            ++n2;
        }
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public void pause(Long[] jobIds) {
        Long[] longArray = jobIds;
        int n = jobIds.length;
        int n2 = 0;
        while (n2 < n) {
            Long jobId = longArray[n2];
            ScheduleUtils.pauseJob(this.scheduler, jobId);
            ++n2;
        }
        this.updateBatch(jobIds, Constant.ScheduleStatus.PAUSE.getValue());
    }

    @Override
    @Transactional(rollbackFor={Exception.class})
    public void resume(Long[] jobIds) {
        Long[] longArray = jobIds;
        int n = jobIds.length;
        int n2 = 0;
        while (n2 < n) {
            Long jobId = longArray[n2];
            ScheduleUtils.resumeJob(this.scheduler, jobId);
            ++n2;
        }
        this.updateBatch(jobIds, Constant.ScheduleStatus.NORMAL.getValue());
    }
}

