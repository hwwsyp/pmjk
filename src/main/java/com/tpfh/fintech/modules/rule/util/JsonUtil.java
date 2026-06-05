/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  com.alibaba.fastjson.JSONObject
 *  com.alibaba.fastjson.parser.Feature
 *  com.alibaba.fastjson.serializer.SerializerFeature
 */
package com.tpfh.fintech.modules.rule.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

public final class JsonUtil {
    private JsonUtil() {
    }

    public static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static <T> T fromJson(JSON json, Class<T> classOfT) {
        return (T)JSON.toJavaObject((JSON)json, classOfT);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return JsonUtil.fromJson(json, classOfT);
    }

    public static <T> T fromJson(String json, Type typeOfT) {
        if (JsonUtil.isEmpty(json)) {
            return null;
        }
        return (T)JSON.parseObject((String)json, (Type)typeOfT, (Feature[])new Feature[0]);
    }

    public static final <T> T fromJson(InputStream is, Class<T> classOfT) throws IOException {
        return JsonUtil.fromJson(is, classOfT);
    }

    public static <T> T fromJson(InputStream is, Type typeOfT) throws IOException {
        return (T)JSON.parseObject((InputStream)is, (Type)typeOfT, (Feature[])new Feature[0]);
    }

    public static <T> List<T> fromJsonList(String json, Class<T> classOfT) {
        if (JsonUtil.isEmpty(json)) {
            return null;
        }
        return JSON.parseArray((String)json, classOfT);
    }

    public static String toJson(Object obj) {
        if (obj == null) {
            return null;
        }
        return JSON.toJSONString((Object)obj);
    }

    public static String toJson(Object obj, SerializerFeature ... features) {
        if (obj == null) {
            return null;
        }
        return JSON.toJSONString((Object)obj, (SerializerFeature[])features);
    }

    public static String toJsonNoRef(Object obj) {
        return JsonUtil.toJson(obj, SerializerFeature.DisableCircularReferenceDetect);
    }

    public static String toJsonWriteNull(Object obj) {
        return JsonUtil.toJson(obj, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullBooleanAsFalse);
    }

    public static JSONObject toJsonObj(String json) {
        return JSON.parseObject((String)json);
    }
}

