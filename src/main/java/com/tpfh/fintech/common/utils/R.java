/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.http.HttpStatus
 */
package com.tpfh.fintech.common.utils;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;

public class R
extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public R() {
        this.put("code", (Object)0);
        this.put("msg", (Object)"success");
    }

    public static R error() {
        return R.error(HttpStatus.SERVICE_UNAVAILABLE.value(), "\u672a\u77e5\u5f02\u5e38\uff0c\u8bf7\u8054\u7cfb\u7ba1\u7406\u5458");
    }

    public static R error(String msg) {
        return R.error(HttpStatus.SERVICE_UNAVAILABLE.value(), msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", (Object)code);
        r.put("msg", (Object)msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", (Object)msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}

