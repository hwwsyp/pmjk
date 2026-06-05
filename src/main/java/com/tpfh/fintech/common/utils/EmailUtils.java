/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.stereotype.Component
 */
package com.tpfh.fintech.common.utils;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public Map<String, Object> sendEmail(HashMap<String, Object> params) {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        this.logger.info(((Object)resultMap).toString());
        return resultMap;
    }
}

