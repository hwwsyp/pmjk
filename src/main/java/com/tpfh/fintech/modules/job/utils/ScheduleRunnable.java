/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 *  org.springframework.util.ReflectionUtils
 */
package com.tpfh.fintech.modules.job.utils;

import com.tpfh.fintech.common.exception.TpfhException;
import com.tpfh.fintech.common.utils.SpringContextUtils;
import java.lang.reflect.Method;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

public class ScheduleRunnable
implements Runnable {
    private Object target;
    private Method method;
    private String params;

    public ScheduleRunnable(String beanName, String methodName, String params) throws NoSuchMethodException, SecurityException {
        this.target = SpringContextUtils.getBean(beanName);
        this.params = params;
        this.method = StringUtils.isNotBlank((String)params) ? this.target.getClass().getDeclaredMethod(methodName, String.class) : this.target.getClass().getDeclaredMethod(methodName, new Class[0]);
    }

    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible((Method)this.method);
            if (StringUtils.isNotBlank((String)this.params)) {
                this.method.invoke(this.target, this.params);
            } else {
                this.method.invoke(this.target, new Object[0]);
            }
        }
        catch (Exception e) {
            throw new TpfhException("\u6267\u884c\u5b9a\u65f6\u4efb\u52a1\u5931\u8d25", e);
        }
    }
}

