package com.tpfh.fintech.modules.job.utils;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tpfh.fintech.common.utils.JsonUtil;
import com.tpfh.fintech.common.utils.SpringContextUtils;
import com.tpfh.fintech.modules.job.entity.ScheduleJobEntity;
import com.tpfh.fintech.modules.job.entity.ScheduleJobLogEntity;
import com.tpfh.fintech.modules.job.service.ScheduleJobLogService;


/**
 * 定时任务
 */
//@DisallowConcurrentExecution add by owen in 20210820 要求任务不并发运行，仅单线程执行。避免因job执行时间超过corn设置的时长，而出现并发执行同一个job的问题发生
@DisallowConcurrentExecution
public class ScheduleJob extends QuartzJobBean {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private ExecutorService service = Executors.newSingleThreadExecutor(); 
	
    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        
    	Object scheduleObj = context.getMergedJobDataMap()
        		.get(ScheduleJobEntity.JOB_PARAM_KEY);
    	JSONObject obj = (JSONObject) JSON.toJSON(scheduleObj);
    	ScheduleJobEntity scheduleJob = new ScheduleJobEntity();
    	scheduleJob.setBeanName(obj.getString("beanName"));
    	scheduleJob.setCreateTime(obj.getDate("createTime"));
    	scheduleJob.setCronExpression(obj.getString("cronExpression"));
    	scheduleJob.setParams(obj.getString("params"));
    	scheduleJob.setRemark(obj.getString("remark"));
    	scheduleJob.setStatus(obj.getInteger("status"));
    	scheduleJob.setMethodName(obj.getString("methodName"));
    	scheduleJob.setJobId(obj.getLong("jobId"));
    	/*ScheduleJobEntity scheduleJob = (ScheduleJobEntity) context.getMergedJobDataMap()
        		.get(ScheduleJobEntity.JOB_PARAM_KEY);*/
        
        //获取spring bean
        ScheduleJobLogService scheduleJobLogService = (ScheduleJobLogService) SpringContextUtils.getBean("scheduleJobLogService");
        
        //数据库保存执行记录
        ScheduleJobLogEntity log = new ScheduleJobLogEntity();
        log.setJobId(scheduleJob.getJobId());
        log.setBeanName(scheduleJob.getBeanName());
        log.setMethodName(scheduleJob.getMethodName());
        log.setParams(scheduleJob.getParams());
        log.setCreateTime(new Date());
        
        //任务开始时间
        long startTime = System.currentTimeMillis();
        
        try {
            //执行任务
        	logger.info("任务准备执行，任务ID：" + scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(),
            		scheduleJob.getMethodName(), scheduleJob.getParams());
            Future<?> future = service.submit(task);
            
			future.get();
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			//任务状态    0：成功    1：失败
			log.setStatus(0);
			
			logger.info("任务执行完毕，任务ID：" + scheduleJob.getJobId() + "  总共耗时：" + times + "毫秒");
		} catch (Exception e) {
			logger.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);
			
			//任务执行总时长
			long times = System.currentTimeMillis() - startTime;
			log.setTimes((int)times);
			
			//任务状态    0：成功    1：失败
			log.setStatus(1);
			log.setError(StringUtils.substring(e.toString(), 0, 2000));
		}finally {
			scheduleJobLogService.insert(log);
		}
    }
}
