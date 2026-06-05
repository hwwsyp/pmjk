/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 */
package com.tpfh.fintech.common.xss;

import com.tpfh.fintech.common.exception.TpfhException;
import org.apache.commons.lang.StringUtils;

public class SQLFilter {
    public static String sqlInject(String str) {
        String[] keywords;
        if (StringUtils.isBlank((String)str)) {
            return null;
        }
        str = StringUtils.replace((String)str, (String)"'", (String)"");
        str = StringUtils.replace((String)str, (String)"\"", (String)"");
        str = StringUtils.replace((String)str, (String)";", (String)"");
        str = StringUtils.replace((String)str, (String)"\\", (String)"");
        str = str.toLowerCase();
        String[] stringArray = keywords = new String[]{"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};
        int n = keywords.length;
        int n2 = 0;
        while (n2 < n) {
            String keyword = stringArray[n2];
            if (str.indexOf(keyword) != -1) {
                throw new TpfhException("\u5305\u542b\u975e\u6cd5\u5b57\u7b26");
            }
            ++n2;
        }
        return str;
    }
}

