/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.context.ApplicationContext
 *  org.springframework.context.ApplicationContextAware
 *  org.springframework.stereotype.Component
 */
package com.tpfh.fintech.modules.rule.util;

import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextHolder
implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        SpringContextHolder.checkApplicationContext();
        return applicationContext;
    }

    public static <T> T getBean(String name) {
        SpringContextHolder.checkApplicationContext();
        return (T)applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        SpringContextHolder.checkApplicationContext();
        Map beanMaps = applicationContext.getBeansOfType(clazz);
        if (beanMaps != null && !beanMaps.isEmpty()) {
            return (T)beanMaps.values().iterator().next();
        }
        return null;
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext\u672a\u6ce8\u5165,\u8bf7\u5728applicationContext.xml\u4e2d\u5b9a\u4e49SpringContextHolder");
        }
    }
}

