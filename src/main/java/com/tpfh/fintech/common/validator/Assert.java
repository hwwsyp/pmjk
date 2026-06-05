/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 */
package com.tpfh.fintech.common.validator;

import com.tpfh.fintech.common.exception.TpfhException;
import org.apache.commons.lang.StringUtils;

public abstract class Assert {
    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank((String)str)) {
            throw new TpfhException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new TpfhException(message);
        }
    }
}

