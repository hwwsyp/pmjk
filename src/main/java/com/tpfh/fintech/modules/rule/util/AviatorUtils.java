/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.googlecode.aviator.AviatorEvaluator
 */
package com.tpfh.fintech.modules.rule.util;

import com.googlecode.aviator.AviatorEvaluator;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class AviatorUtils {
    public static BigDecimal bigNumberReckon() {
        return null;
    }

    public static Object reckon(String expression, Map<String, Object> param) {
        return AviatorEvaluator.execute((String)expression, param);
    }

    public static void main(String[] args) {
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("a", 0.1);
        param.put("b", 2L);
        String expression = " (1 +a ) * 2 +b";
        System.out.println(AviatorUtils.reckon(expression, param));
    }
}

