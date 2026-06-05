/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.poi.ss.formula.functions.T
 *  org.springframework.context.ApplicationContext
 */
package com.tpfh.fintech.modules.rule.util;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.context.ApplicationContext;

public class SpringContextUtil {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    public static Object getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }
}

