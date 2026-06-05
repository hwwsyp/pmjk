/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  com.alibaba.fastjson.serializer.ObjectSerializer
 *  com.alibaba.fastjson.serializer.SerializeConfig
 *  com.alibaba.fastjson.serializer.SerializeFilter
 *  com.alibaba.fastjson.serializer.SerializerFeature
 *  com.alibaba.fastjson.serializer.SimpleDateFormatSerializer
 *  com.alibaba.fastjson.serializer.ValueFilter
 */
package com.tpfh.fintech.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.ValueFilter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public final class JsonUtil {
    private static ValueFilter filter = new ValueFilter(){

        public Object process(Object obj, String s, Object v) {
            if (v == null) {
                return "";
            }
            return v;
        }
    };
    private static SerializerFeature[] feature = new SerializerFeature[]{SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue};
    private static SerializeConfig mapping = new SerializeConfig();

    static {
        mapping.put(Date.class, (ObjectSerializer)new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
        mapping.put(Timestamp.class, (ObjectSerializer)new SimpleDateFormatSerializer("yyyy-MM-dd HH:mm:ss"));
    }

    private JsonUtil() {
    }

    public static String getJsonByObj(Object bean) {
        return JsonUtil.getJsonByObj(bean, mapping);
    }

    public static String getJsonByObj(Object bean, String dateType) {
        SerializeConfig zdymapping = new SerializeConfig();
        zdymapping.put(Date.class, (ObjectSerializer)new SimpleDateFormatSerializer(dateType));
        return JsonUtil.getJsonByObj(bean, zdymapping);
    }

    public static String getJsonDefaultByObj(Object bean) {
        return JSON.toJSONString((Object)bean);
    }

    public static <T> List<T> getListBean(String json, Class<T> calzz) {
        return JSON.parseArray((String)json, calzz);
    }

    public static List getList(String json, Class calzz) {
        return JsonUtil.getListBean(json, calzz);
    }

    public static <T> T getObjet(String json, Class<T> calzz) {
        return (T)JSON.parseObject((String)json, calzz);
    }

    private static String getJsonByObj(Object bean, SerializeConfig mappingx) {
        String json = JSON.toJSONString((Object)bean, (SerializeConfig)mappingx, (SerializeFilter)filter, (SerializerFeature[])feature);
        json = JsonUtil.stringToJson(json);
        return json;
    }

    public static String stringToJson(String s) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i < s.length()) {
            char c = s.charAt(i);
            switch (c) {
                case '\\': {
                    sb.append("\\\\");
                    break;
                }
                case '/': {
                    sb.append("\\/");
                    break;
                }
                case '\b': {
                    sb.append("\\b");
                    break;
                }
                case '\f': {
                    sb.append("\\f");
                    break;
                }
                case '\n': {
                    sb.append("\\n");
                    break;
                }
                case '\r': {
                    sb.append("\\r");
                    break;
                }
                case '\t': {
                    sb.append("\\t");
                    break;
                }
                default: {
                    sb.append(c);
                }
            }
            ++i;
        }
        return sb.toString();
    }
}

