/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.annotation.Autowired
 *  org.springframework.data.redis.core.HashOperations
 *  org.springframework.data.redis.core.ListOperations
 *  org.springframework.data.redis.core.RedisTemplate
 *  org.springframework.data.redis.core.SetOperations
 *  org.springframework.data.redis.core.StringRedisTemplate
 *  org.springframework.data.redis.core.ValueOperations
 *  org.springframework.data.redis.core.ZSetOperations
 *  org.springframework.stereotype.Component
 */
package com.tpfh.fintech.common.utils;

import com.tpfh.fintech.common.utils.JsonUtil;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

@Component
public class RedisUtils {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;
    public static final long DEFAULT_EXPIRE = 86400L;
    public static final long NOT_EXPIRE = -1L;

    public Boolean exists(String key) {
        return this.stringRedisTemplate.hasKey((Object)key);
    }

    public void sadd(String key, List<String> values) {
        this.setOperations.add((Object)key, values.toArray());
    }

    public void saddOne(String key, String values) {
        this.setOperations.add((Object)key, new Object[]{values});
    }

    public Boolean isMember(String key, String values) {
        return this.setOperations.isMember((Object)key, (Object)values);
    }

    public void expire(String key, long expire) {
        if (expire != -1L) {
            this.stringRedisTemplate.expire((Object)key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value, long expire) {
        this.valueOperations.set((Object)key, (Object)this.toJson(value));
        if (expire != -1L) {
            this.redisTemplate.expire((Object)key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value) {
        this.set(key, value, 86400L);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = (String)this.valueOperations.get((Object)key);
        if (expire != -1L) {
            this.redisTemplate.expire((Object)key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : (T)this.fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return this.get(key, clazz, -1L);
    }

    public String get(String key, long expire) {
        String value = (String)this.valueOperations.get((Object)key);
        if (expire != -1L) {
            this.redisTemplate.expire((Object)key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return this.get(key, -1L);
    }

    public void delete(String key) {
        this.redisTemplate.delete((Object)key);
    }

    private String toJson(Object object) {
        if (object instanceof Integer || object instanceof Long || object instanceof Float || object instanceof Double || object instanceof Boolean || object instanceof String) {
            return String.valueOf(object);
        }
        return JsonUtil.getJsonByObj(object);
    }

    private <T> T fromJson(String json, Class<T> clazz) {
        return JsonUtil.getObjet(json, clazz);
    }
}

