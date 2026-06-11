package com.tpfh.fintech.common.aspect;

import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.HttpContextUtils;
import com.tpfh.fintech.common.utils.IPUtils;
import com.tpfh.fintech.common.utils.JsonUtil;
import com.tpfh.fintech.common.utils.ShiroUtils;
import com.tpfh.fintech.modules.sys.entity.SysLogEntity;
import com.tpfh.fintech.modules.sys.service.SysLogService;


/**
 * 系统日志，切面处理类
 */
@Aspect
@Component
public class SysLogAspect {
	@Autowired
	private SysLogService sysLogService;
	
	@Pointcut("@annotation(com.tpfh.fintech.common.annotation.SysLog)")
	public void logPointCut() { 
		
	}

	@Around("logPointCut()")
	public Object around(ProceedingJoinPoint point) throws Throwable {
		long beginTime = System.currentTimeMillis();
		//执行方法
		Object result = point.proceed();
		//执行时长(毫秒)
		long time = System.currentTimeMillis() - beginTime;

		//保存日志
		saveSysLog(point, time);

		return result;
	}

	private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SysLogEntity sysLog = new SysLogEntity();
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			//注解上的描述
			sysLog.setOperation(syslog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		try{
			//add by owen in 20230330 检查第一个参数是否为上传的文件，如果是，则忽略掉
			if(args.length>0) {
				if(args[0] instanceof MultipartFile) {
					// do nothing
				}else {
					String params = JsonUtil.getJsonByObj(args[0]);
					sysLog.setParams(params);
				}
			}
		}catch (Exception e){

		}

		//modify by owen in 20230718 可能存在某些方法不是外部触发的，没有外部的请求头
		try {
			//获取request
			HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
			//设置IP地址
			sysLog.setIp(IPUtils.getIpAddr(request));
		}catch (Exception e) {
			//do nothing 
		}
		
		//用户名
		String username = ShiroUtils.getUserNameEvenError();
		sysLog.setUsername(username);

		sysLog.setTime(time);
		sysLog.setCreateDate(new Date());
		//保存系统日志
		sysLogService.insert(sysLog);
	}
}
