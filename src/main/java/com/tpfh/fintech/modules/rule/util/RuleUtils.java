/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.rule.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleUtils {
    public static boolean isEmpty(String str) {
        return str == null || "".equals(str.trim());
    }

    public static boolean isNotEmpty(String str) {
        return !RuleUtils.isEmpty(str);
    }

    public static List<String> getConditionParamBetweenChar(String str) {
        Matcher m = Pattern.compile("\\$(.*?)\\$").matcher(str);
        ArrayList<String> list = new ArrayList<String>();
        while (m.find()) {
            list.add(m.group(1));
        }
        return list;
    }

    public static List<String> getValueBetweenChar(String str) {
        Matcher m = Pattern.compile("\\@(.*?)\\@").matcher(str);
        ArrayList<String> list = new ArrayList<String>();
        while (m.find()) {
            list.add(m.group(1));
        }
        return list;
    }

    public static List<String> getActionParamBetweenChar(String str) {
        Matcher m = Pattern.compile("\\#(.*?)\\#").matcher(str);
        ArrayList<String> list = new ArrayList<String>();
        while (m.find()) {
            list.add(m.group(1));
        }
        return list;
    }

    public static String getParamBetweenChar(String str, String charStr) {
        Matcher m = Pattern.compile("\\" + charStr + "(.*?)\\" + charStr).matcher(str);
        String value = null;
        while (m.find()) {
            value = m.group(1);
        }
        return value;
    }

    public static boolean checkStyleOfString(String str) {
        return str.matches("^(\\-|\\+)?\\d+(\\.\\d+)?$");
    }

    public static String getConditionOfVariable(String str) {
        String[] arr = str.split(">(=)?|<(=)?|={1,2}|!=");
        if (arr.length < 2) {
            return "";
        }
        return arr[1];
    }

    public static boolean checkContainOfOperator(String str) {
        return str.contains("+") || str.contains("-") || str.contains("*") || str.contains("/") || str.contains("%");
    }

    public static boolean checkContainOfOperator(String str, String charStr) {
        return str.contains(charStr);
    }

    public static String getMethodByProperty(String property) {
        String tempStr = property;
        if (RuleUtils.isNotEmpty(property)) {
            tempStr = String.valueOf(tempStr.substring(0, 1).toUpperCase()) + tempStr.substring(1);
            return "get" + tempStr + "()";
        }
        return null;
    }

    public static String setMethodByProperty(String property) {
        String tempStr = property;
        if (RuleUtils.isNotEmpty(property)) {
            tempStr = String.valueOf(tempStr.substring(0, 1).toUpperCase()) + tempStr.substring(1);
            return "set" + tempStr;
        }
        return null;
    }

    public static void main(String[] args) {
        String name = "money";
        System.out.println(name);
        System.out.println("\n");
        System.out.println(name);
    }
}

