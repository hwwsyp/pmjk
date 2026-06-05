/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.baomidou.mybatisplus.exceptions.MybatisPlusException
 *  com.baomidou.mybatisplus.toolkit.CollectionUtils
 *  com.baomidou.mybatisplus.toolkit.MapUtils
 *  com.baomidou.mybatisplus.toolkit.StringUtils
 */
package com.tpfh.fintech.common.base;

import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.MapUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MyReflectionKit {
    public static String getMethodCapitalize(Field field, String str) {
        Class<?> fieldType = field.getType();
        return StringUtils.concatCapitalize((String)"set", (String)str);
    }

    public static Map<String, Field> getFieldMap(Class<?> clazz) {
        List<Field> fieldList = MyReflectionKit.getFieldList(clazz);
        Map<String, Field> fieldMap = Collections.emptyMap();
        if (CollectionUtils.isNotEmpty(fieldList)) {
            fieldMap = new LinkedHashMap<String, Field>();
            for (Field field : fieldList) {
                fieldMap.put(field.getName(), field);
            }
        }
        return fieldMap;
    }

    public static List<Field> getFieldList(Class<?> clazz) {
        Field[] fields;
        if (clazz == null) {
            return null;
        }
        LinkedList<Field> fieldList = new LinkedList<Field>();
        Field[] fieldArray = fields = clazz.getDeclaredFields();
        int n = fields.length;
        int n2 = 0;
        while (n2 < n) {
            Field field = fieldArray[n2];
            if (!Modifier.isStatic(field.getModifiers()) && !Modifier.isTransient(field.getModifiers())) {
                fieldList.add(field);
            }
            ++n2;
        }
        Class<?> superClass = clazz.getSuperclass();
        if (superClass.equals(Object.class)) {
            return fieldList;
        }
        return MyReflectionKit.excludeOverrideSuperField(fieldList, MyReflectionKit.getFieldList(superClass));
    }

    public static List<Field> excludeOverrideSuperField(List<Field> fieldList, List<Field> superFieldList) {
        HashMap<String, Field> fieldMap = new HashMap<String, Field>();
        for (Field field : fieldList) {
            fieldMap.put(field.getName(), field);
        }
        for (Field superField : superFieldList) {
            if (fieldMap.get(superField.getName()) != null) continue;
            fieldList.add(superField);
        }
        return fieldList;
    }

    public static Object setFiledValue(Class<?> cls, Object entity, String str, Class<?> parameterType, Object value) {
        Map<String, Field> fieldMaps = MyReflectionKit.getFieldMap(cls);
        try {
            if (MapUtils.isEmpty(fieldMaps)) {
                throw new MybatisPlusException(String.format("Error: NoSuchField in %s for %s.  Cause:", cls.getSimpleName(), str));
            }
            Method method = cls.getMethod(MyReflectionKit.getMethodCapitalize(fieldMaps.get(str), str), parameterType);
            return method.invoke(entity, value);
        }
        catch (NoSuchMethodException e) {
            throw new MybatisPlusException(String.valueOf(String.format("Error: NoSuchMethod in %s.  Cause:", cls.getSimpleName())) + e);
        }
        catch (IllegalAccessException e) {
            throw new MybatisPlusException(String.valueOf(String.format("Error: Cannot execute a private method. in %s.  Cause:", cls.getSimpleName())) + e);
        }
        catch (InvocationTargetException e) {
            throw new MybatisPlusException("Error: InvocationTargetException on getMethodValue.  Cause:" + e);
        }
    }
}

