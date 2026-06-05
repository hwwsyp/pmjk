/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.servlet.http.HttpServletRequest
 *  org.aspectj.lang.ProceedingJoinPoint
 *  org.aspectj.lang.annotation.Around
 *  org.aspectj.lang.annotation.Aspect
 *  org.aspectj.lang.annotation.Pointcut
 *  org.aspectj.lang.reflect.MethodSignature
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.stereotype.Component
 *  org.springframework.web.multipart.MultipartFile
 */
package com.tpfh.fintech.common.aspect;

import com.tpfh.fintech.common.annotation.SysLog;
import com.tpfh.fintech.common.utils.HttpContextUtils;
import com.tpfh.fintech.common.utils.IPUtils;
import com.tpfh.fintech.common.utils.JsonUtil;
import com.tpfh.fintech.common.utils.ShiroUtils;
import com.tpfh.fintech.modules.sys.entity.SysLogEntity;
import com.tpfh.fintech.modules.sys.service.SysLogService;
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

@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private SysLogService sysLogService;

    @Pointcut(value="@annotation(com.tpfh.fintech.common.annotation.SysLog)")
    public void logPointCut() {
    }

    @Around(value="logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        Object result = point.proceed();
        long time = System.currentTimeMillis() - beginTime;
        this.saveSysLog(point, time);
        return result;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLogEntity sysLog = new SysLogEntity();
        SysLog syslog = method.getAnnotation(SysLog.class);
        if (syslog != null) {
            sysLog.setOperation(syslog.value());
        }
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(String.valueOf(className) + "." + methodName + "()");
        Object[] args = joinPoint.getArgs();
        try {
            if (args.length > 0 && !(args[0] instanceof MultipartFile)) {
                String params = JsonUtil.getJsonByObj(args[0]);
                sysLog.setParams(params);
            }
        }
        catch (Exception params) {
            // empty catch block
        }
        try {
            HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
            sysLog.setIp(IPUtils.getIpAddr(request));
        }
        catch (Exception request) {
            // empty catch block
        }
        String username = ShiroUtils.getUserNameEvenError();
        sysLog.setUsername(username);
        sysLog.setTime(time);
        sysLog.setCreateDate(new Date());
        this.sysLogService.insert(sysLog);
    }
}

