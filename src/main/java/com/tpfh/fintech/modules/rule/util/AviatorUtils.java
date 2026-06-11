package com.tpfh.fintech.modules.rule.util;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.googlecode.aviator.AviatorEvaluator;

public class AviatorUtils {
	
	public static BigDecimal  bigNumberReckon() {
		return null;
	}
	
	public static Object reckon(String expression,Map<String,Object> param) {
		
		 return AviatorEvaluator.execute(expression, param);
	}
	
	public static void main(String[] args) {
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("a", 0.1);
		param.put("b", 2L);
		String expression = " (1 +a ) * 2 +b";
		System.out.println(reckon(expression,param));
	}
}
