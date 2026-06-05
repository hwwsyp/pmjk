/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.aspectj.lang.ProceedingJoinPoint
 *  org.aspectj.lang.annotation.Around
 *  org.aspectj.lang.annotation.Aspect
 *  org.aspectj.lang.annotation.Pointcut
 *  org.aspectj.lang.reflect.MethodSignature
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.core.Ordered
 *  org.springframework.stereotype.Component
 */
package com.tpfh.fintech.datasources.aspect;

import com.tpfh.fintech.datasources.DynamicDataSource;
import com.tpfh.fintech.datasources.annotation.DataSource;
import java.lang.reflect.Method;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect
implements Ordered {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut(value="@annotation(com.tpfh.fintech.datasources.annotation.DataSource)")
    public void dataSourcePointCut() {
    }

    @Around(value="dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();
        DataSource ds = method.getAnnotation(DataSource.class);
        if (ds == null) {
            DynamicDataSource.setDataSource("bbg");
            this.logger.debug("set datasource is bbg");
        } else {
            DynamicDataSource.setDataSource(ds.name());
            this.logger.debug("set datasource is " + ds.name());
        }
        try {
            Object object = point.proceed();
            return object;
        }
        finally {
            DynamicDataSource.clearDataSource();
            this.logger.debug("clean datasource");
        }
    }

    public int getOrder() {
        return 1;
    }
}

