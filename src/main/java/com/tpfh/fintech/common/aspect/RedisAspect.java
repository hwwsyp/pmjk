/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.aspectj.lang.ProceedingJoinPoint
 *  org.aspectj.lang.annotation.Around
 *  org.aspectj.lang.annotation.Aspect
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Value
 *  org.springframework.context.annotation.Configuration
 */
package com.tpfh.fintech.common.aspect;

import com.tpfh.fintech.common.exception.TpfhException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class RedisAspect {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value(value="${spring.redis.open: false}")
    private boolean open;

    @Around(value="execution(* com.tpfh.fintech.common.utils.RedisUtils.*(..))")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        Object result = null;
        if (this.open) {
            try {
                result = point.proceed();
            }
            catch (Exception e) {
                this.logger.error("redis error", (Throwable)e);
                throw new TpfhException("Redis\u670d\u52a1\u5f02\u5e38");
            }
        }
        return result;
    }
}

