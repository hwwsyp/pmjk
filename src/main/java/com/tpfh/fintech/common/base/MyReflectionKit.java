package com.tpfh.fintech.common.base;

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

import com.baomidou.mybatisplus.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.toolkit.MapUtils;
import com.baomidou.mybatisplus.toolkit.StringUtils;

public class MyReflectionKit {

	/**
	 * <p>
	 * 鍙嶅皠 method 鏂规硶鍚嶏紝渚嬪 getId
	 * </p>
	 *
	 * @param field
	 * @param str   灞炴�у瓧绗︿覆鍐呭
	 * @return
	 */
	public static String getMethodCapitalize(Field field, final String str) {
		Class<?> fieldType = field.getType();
		// fix #176
		return StringUtils.concatCapitalize("set", str);
	}


	public static Map<String, Field> getFieldMap(Class<?> clazz) {
		List<Field> fieldList = getFieldList(clazz);
		Map<String, Field> fieldMap = Collections.emptyMap();
		if (CollectionUtils.isNotEmpty(fieldList)) {
			fieldMap = new LinkedHashMap<>();
			for (Field field : fieldList) {
				fieldMap.put(field.getName(), field);
			}
		}
		return fieldMap;
	}

	/**
	 * <p>
	 * 鑾峰彇璇ョ被鐨勬墍鏈夊睘鎬у垪琛�
	 * </p>
	 *
	 * @param clazz 鍙嶅皠绫�
	 * @return
	 */
	public static List<Field> getFieldList(Class<?> clazz) {
		if (null == clazz) {
			return null;
		}
		List<Field> fieldList = new LinkedList<>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			/* 杩囨护闈欐�佸睘鎬� */
			if (Modifier.isStatic(field.getModifiers())) {
				continue;
			}
			/* 杩囨护 transient鍏抽敭瀛椾慨楗扮殑灞炴�� */
			if (Modifier.isTransient(field.getModifiers())) {
				continue;
			}
			fieldList.add(field);
		}
		/* 澶勭悊鐖剁被瀛楁 */
		Class<?> superClass = clazz.getSuperclass();
		if (superClass.equals(Object.class)) {
			return fieldList;
		}
		/* 鎺掗櫎閲嶈浇灞炴�� */
		return excludeOverrideSuperField(fieldList, getFieldList(superClass));
	}

	/**
	 * <p>
	 * 鎺掑簭閲嶇疆鐖剁被灞炴��
	 * </p>
	 *
	 * @param fieldList      瀛愮被灞炴��
	 * @param superFieldList 鐖剁被灞炴��
	 */
	public static List<Field> excludeOverrideSuperField(List<Field> fieldList, List<Field> superFieldList) {
		Map<String, Field> fieldMap = new HashMap<>();
		for (Field field : fieldList) {
			fieldMap.put(field.getName(), field);
		}
		for (Field superField : superFieldList) {
			if (null == fieldMap.get(superField.getName())) {
				fieldList.add(superField);
			}
		}
		return fieldList;
	}

	public static Object setFiledValue(Class<?> cls, Object entity, String str, Class<?> parameterType, Object value) {		
		Map<String, Field> fieldMaps = getFieldMap(cls);
		try {
			if (MapUtils.isEmpty(fieldMaps)) {
				throw new MybatisPlusException(
						String.format("Error: NoSuchField in %s for %s.  Cause:", cls.getSimpleName(), str));
			}
			Method method = cls.getMethod(getMethodCapitalize(fieldMaps.get(str), str), parameterType);
			return method.invoke(entity, value);
		} catch (NoSuchMethodException e) {
			throw new MybatisPlusException(String.format("Error: NoSuchMethod in %s.  Cause:", cls.getSimpleName()) + e);
		} catch (IllegalAccessException e) {
			throw new MybatisPlusException(String.format("Error: Cannot execute a private method. in %s.  Cause:",
					cls.getSimpleName())
					+ e);
		} catch (InvocationTargetException e) {
			throw new MybatisPlusException("Error: InvocationTargetException on getMethodValue.  Cause:" + e);
		}
	}
}
