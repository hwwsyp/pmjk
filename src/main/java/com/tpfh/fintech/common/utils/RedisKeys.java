/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.common.utils;

public class RedisKeys {
    public static String getSysConfigKey(String key) {
        return "sys:config:" + key;
    }
}

