/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  com.alibaba.fastjson.JSONObject
 *  org.apache.commons.lang.StringUtils
 *  org.quartz.DisallowConcurrentExecution
 *  org.quartz.JobExecutionContext
 *  org.quartz.JobExecutionException
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.scheduling.quartz.QuartzJobBean
 */
package com.tpfh.fintech.modules.job.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tpfh.fintech.common.utils.SpringContextUtils;
import com.tpfh.fintech.modules.job.entity.ScheduleJobEntity;
import com.tpfh.fintech.modules.job.entity.ScheduleJobLogEntity;
import com.tpfh.fintech.modules.job.service.ScheduleJobLogService;
import com.tpfh.fintech.modules.job.utils.ScheduleRunnable;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.commons.lang.StringUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

@DisallowConcurrentExecution
public class ScheduleJob
extends QuartzJobBean {
    private Logger logger = LoggerFactory.getLogger(((Object)((Object)this)).getClass());
    private ExecutorService service = Executors.newSingleThreadExecutor();

    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        Object scheduleObj = context.getMergedJobDataMap().get((Object)"JOB_PARAM_KEY");
        JSONObject obj = (JSONObject)JSON.toJSON((Object)scheduleObj);
        ScheduleJobEntity scheduleJob = new ScheduleJobEntity();
        scheduleJob.setBeanName(obj.getString("beanName"));
        scheduleJob.setCreateTime(obj.getDate("createTime"));
        scheduleJob.setCronExpression(obj.getString("cronExpression"));
        scheduleJob.setParams(obj.getString("params"));
        scheduleJob.setRemark(obj.getString("remark"));
        scheduleJob.setStatus(obj.getInteger("status"));
        scheduleJob.setMethodName(obj.getString("methodName"));
        scheduleJob.setJobId(obj.getLong("jobId"));
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService)SpringContextUtils.getBean("scheduleJobLogService");
        ScheduleJobLogEntity log = new ScheduleJobLogEntity();
        log.setJobId(scheduleJob.getJobId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());
        log.setCreateTime(new Date());
        long startTime = System.currentTimeMillis();
        try {
            try {
                this.logger.info("\u4efb\u52a1\u51c6\u5907\u6267\u884c\uff0c\u4efb\u52a1ID\uff1a" + scheduleJob.getJobId());
                ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(), scheduleJob.getParams());
                Future<?> future = this.service.submit(task);
                future.get();
                long times = System.currentTimeMillis() - startTime;
                log.setTimes((int)times);
                log.setStatus(0);
                this.logger.info("\u4efb\u52a1\u6267\u884c\u5b8c\u6bd5\uff0c\u4efb\u52a1ID\uff1a" + scheduleJob.getJobId() + "  \u603b\u5171\u8017\u65f6\uff1a" + times + "\u6beb\u79d2");
            }
            catch (Exception e) {
                this.logger.error("\u4efb\u52a1\u6267\u884c\u5931\u8d25\uff0c\u4efb\u52a1ID\uff1a" + scheduleJob.getJobId(), (Throwable)e);
                long times = System.currentTimeMillis() - startTime;
                log.setTimes((int)times);
                log.setStatus(1);
                log.setError(StringUtils.substring((String)e.toString(), (int)0, (int)2000));
                scheduleJobLogService.insert(log);
            }
        }
        finally {
            scheduleJobLogService.insert(log);
        }
    }
}

