/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.rule.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StringUtil {
    public static boolean listIsNotNull(List list) {
        return list != null && list.size() > 0;
    }

    public static boolean strIsNull(String src) {
        return src == null || "null".equals(src) || src.trim().length() <= 0 || "".equals(src);
    }

    public static boolean strIsNotNull(String src) {
        return !StringUtil.strIsNull(src);
    }

    public static <X> List<X> parseStrToList(String str, Class<X> clazz) {
        String[] a = str.split(",");
        ArrayList<Long> b = new ArrayList<Long>();
        String[] stringArray = a;
        int n = a.length;
        int n2 = 0;
        while (n2 < n) {
            String anA = stringArray[n2];
            Long c = Long.parseLong(anA);
            b.add(c);
            ++n2;
        }
        return b;
    }

    public static String getCurrentYear(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        return sdf.format(date);
    }
}

