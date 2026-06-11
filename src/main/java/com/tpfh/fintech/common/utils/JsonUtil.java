package com.tpfh.fintech.common.utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.alibaba.fastjson2.filter.Filter;
import com.alibaba.fastjson2.filter.ValueFilter;

import java.util.List;

/**
 * JSON对象工具类
 */
public final class JsonUtil {
	private JsonUtil() {}
	/***
	 * 值过滤器
	 */
	private static ValueFilter filter = new ValueFilter() {
		@Override
		public Object apply(Object obj, String name, Object value) {
			if (value == null)
				return "";
			return value;
		}
	};
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static JSONWriter.Feature[] feature = {
		JSONWriter.Feature.WriteMapNullValue
	};

	/**
	 * 将对象转换成JSON字符串 --固定时间格式"yyyy-MM-dd HH:mm:ss"
	 * @param BO/VO,map,数组,list 对象
	 * @return JSON字符串
	 * @说明：对bean中有Date类型的数据可以成功转换成yyyy-MM-dd HH:mm:ss格式的时间类型,例如："barDate":yyyy-MM-dd HH:mm:ss
	 */
	public static String getJsonByObj(Object bean) {
		return getJsonByObj(bean, DEFAULT_DATE_FORMAT);
	}
	/**
	 * 将对象转换成JSON字符串 --特定时间格式--所有Key为小写
	 * @param BO/VO,map,数组,list 对象
	 * @dateType 时间格式转换后的字符串格式，例如yyyy-MM-dd HH:mm:ss
	 * @return JSON字符串
	 * @说明：对bean中有Date类型的数据可以成功转换成yyyy-MM-dd HH:mm:ss格式的时间类型,例如："barDate":yyyy-MM-dd HH:mm:ss
	 */
	public static String getJsonByObj(Object bean,String dateType) {
		String json = JSON.toJSONString(bean, dateType, new Filter[]{filter}, feature);
		json = stringToJson(json);
		return json;
	}
	/**
	 * 将对象转换成JSON字符串 ---效率高一些--不处理key 也不处理循环引用的问题--也不处理时间格式
	 * @param BO/VO,map,数组,list 对象
	 * @return JSON字符串
	 * @说明：对bean中有Date类型的数据可以成功转换成long格式的时间类型,例如："barDate":1458268099098
	 */
	public static String getJsonDefaultByObj(Object bean) {
		return JSON.toJSONString(bean);
	}
	/**
	 * 将JSON数据转换为ListBean集合
	 * @param <T>
	 * @param json JSON数组数据
	 * @param calzz 待转换的Bean类型 --LinkedCaseInsensitiveMap
	 * @return 
	 */
	public static <T> List<T> getListBean(String json, Class<T> calzz) {
		return JSON.parseArray(json, calzz);
	}
	/**
	 * 将JSON数据转换为List集合
	 * @param <T>
	 * @param json JSON数组数据
	 * @param calzz 待转换的Bean类型 --LinkedCaseInsensitiveMap
	 * @return 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getList(String json, Class calzz) {
		return getListBean(json, calzz);
	}
	/**
	 * 将JSON数据转换为 Java Bea n对象 
	 * @param json JSON字符串
	 * @param calzz 待转换的Bean类型--LinkedCaseInsensitiveMap
	 * @return
	 */
	public static <T> T getObjet(String json,Class<T> calzz) {
		return JSON.parseObject(json, calzz) ;
	}

	/**
	 * 当文本中含有如下特殊字符时，此方法可以成功处理，让其在前台被正确解析，注意：此法不能处理单引号
	 * @param s
	 * @return
	 */
	public static String stringToJson(String s) {
		StringBuffer sb = new StringBuffer ();
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
               case '\\':
                  sb.append("\\\\");
                  break;
				case '/':
					sb.append("\\/");
					break;
				case '\b':
					sb.append("\\b");
					break;
				case '\f':
					sb.append("\\f");
					break;
				case '\n':
					sb.append("\\n");
					break;
				case '\r':
					sb.append("\\r");
					break;
				case '\t':
					sb.append("\\t");
					break;
				default:
					sb.append(c);
			}}
		return sb.toString();
	}


}
