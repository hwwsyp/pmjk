/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.quartz.CronScheduleBuilder
 *  org.quartz.CronTrigger
 *  org.quartz.JobBuilder
 *  org.quartz.JobDataMap
 *  org.quartz.JobDetail
 *  org.quartz.JobKey
 *  org.quartz.ScheduleBuilder
 *  org.quartz.Scheduler
 *  org.quartz.SchedulerException
 *  org.quartz.Trigger
 *  org.quartz.TriggerBuilder
 *  org.quartz.TriggerKey
 */
package com.tpfh.fintech.modules.job.utils;

import com.tpfh.fintech.common.exception.TpfhException;
import com.tpfh.fintech.common.utils.Constant;
import com.tpfh.fintech.modules.job.entity.ScheduleJobEntity;
import com.tpfh.fintech.modules.job.utils.ScheduleJob;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;

public class ScheduleUtils {
    private static final String JOB_NAME = "TASK_";

    public static TriggerKey getTriggerKey(Long jobId) {
        return TriggerKey.triggerKey((String)(JOB_NAME + jobId));
    }

    public static JobKey getJobKey(Long jobId) {
        return JobKey.jobKey((String)(JOB_NAME + jobId));
    }

    public static CronTrigger getCronTrigger(Scheduler scheduler, Long jobId) {
        try {
            return (CronTrigger)scheduler.getTrigger(ScheduleUtils.getTriggerKey(jobId));
        }
        catch (SchedulerException e) {
            throw new TpfhException("\u83b7\u53d6\u5b9a\u65f6\u4efb\u52a1CronTrigger\u51fa\u73b0\u5f02\u5e38", e);
        }
    }

    public static void createScheduleJob(Scheduler scheduler, ScheduleJobEntity scheduleJob) {
        try {
            JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(ScheduleUtils.getJobKey(scheduleJob.getJobId())).build();
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule((String)scheduleJob.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity(ScheduleUtils.getTriggerKey(scheduleJob.getJobId())).withSchedule((ScheduleBuilder)scheduleBuilder).build();
            jobDetail.getJobDataMap().put("JOB_PARAM_KEY", (Object)scheduleJob);
            scheduler.scheduleJob(jobDetail, (Trigger)trigger);
            if (scheduleJob.getStatus().intValue() == Constant.ScheduleStatus.PAUSE.getValue()) {
                ScheduleUtils.pauseJob(scheduler, scheduleJob.getJobId());
            }
        }
        catch (SchedulerException e) {
            throw new TpfhException("\u521b\u5efa\u5b9a\u65f6\u4efb\u52a1\u5931\u8d25", e);
        }
    }

    public static void updateScheduleJob(Scheduler scheduler, ScheduleJobEntity scheduleJob) {
        try {
            TriggerKey triggerKey = ScheduleUtils.getTriggerKey(scheduleJob.getJobId());
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule((String)scheduleJob.getCronExpression()).withMisfireHandlingInstructionDoNothing();
            CronTrigger trigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            trigger = (CronTrigger)trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule((ScheduleBuilder)scheduleBuilder).build();
            trigger.getJobDataMap().put("JOB_PARAM_KEY", (Object)scheduleJob);
            scheduler.rescheduleJob(triggerKey, (Trigger)trigger);
            if (scheduleJob.getStatus().intValue() == Constant.ScheduleStatus.PAUSE.getValue()) {
                ScheduleUtils.pauseJob(scheduler, scheduleJob.getJobId());
            }
        }
        catch (SchedulerException e) {
            throw new TpfhException("\u66f4\u65b0\u5b9a\u65f6\u4efb\u52a1\u5931\u8d25", e);
        }
    }

    public static void run(Scheduler scheduler, ScheduleJobEntity scheduleJob) {
        try {
            JobDataMap dataMap = new JobDataMap();
            dataMap.put("JOB_PARAM_KEY", (Object)scheduleJob);
            scheduler.triggerJob(ScheduleUtils.getJobKey(scheduleJob.getJobId()), dataMap);
        }
        catch (SchedulerException e) {
            throw new TpfhException("\u7acb\u5373\u6267\u884c\u5b9a\u65f6\u4efb\u52a1\u5931\u8d25", e);
        }
    }

    public static void pauseJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.pauseJob(ScheduleUtils.getJobKey(jobId));
        }
        catch (SchedulerException e) {
            throw new TpfhException("\u6682\u505c\u5b9a\u65f6\u4efb\u52a1\u5931\u8d25", e);
        }
    }

    public static void resumeJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.resumeJob(ScheduleUtils.getJobKey(jobId));
        }
        catch (SchedulerException e) {
            throw new TpfhException("\u6682\u505c\u5b9a\u65f6\u4efb\u52a1\u5931\u8d25", e);
        }
    }

    public static void deleteScheduleJob(Scheduler scheduler, Long jobId) {
        try {
            scheduler.deleteJob(ScheduleUtils.getJobKey(jobId));
        }
        catch (SchedulerException e) {
            throw new TpfhException("\u5220\u9664\u5b9a\u65f6\u4efb\u52a1\u5931\u8d25", e);
        }
    }
}

