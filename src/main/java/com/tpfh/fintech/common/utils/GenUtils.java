/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 *  org.apache.commons.lang.WordUtils
 */
package com.tpfh.fintech.common.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

public class GenUtils {
    public static String columnToJava(String columnName) {
        return WordUtils.capitalizeFully((String)columnName, (char[])new char[]{'_'}).replace("_", "");
    }

    public static String tableToJava(String tableName, String tablePrefix) {
        if (StringUtils.isNotBlank((String)tablePrefix)) {
            tableName = tableName.replace(tablePrefix, "");
        }
        return GenUtils.columnToJava(tableName);
    }
}

