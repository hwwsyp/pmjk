/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.util.StringUtils
 */
package com.tpfh.fintech.common.utils;

import com.tpfh.fintech.common.utils.DateConverUtil;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils
extends org.springframework.util.StringUtils {
    public static Integer getRandomByQJ(int a, int b) {
        int s = 0;
        if (a == b) {
            s = a;
        } else {
            int min = a > b ? b : a;
            int fw = Math.abs(a - b);
            int sj = (int)(Math.random() * (double)fw);
            s = min + sj;
        }
        return s;
    }

    public static String toUtf8String(boolean isMSIE, String s) {
        if (isMSIE) {
            try {
                s = URLEncoder.encode(s, "UTF8");
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        } else {
            try {
                s = new String(s.getBytes("UTF-8"), "ISO-8859-1");
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    public static String chinaToUnicode(String str) {
        String result = "";
        int i = 0;
        while (i < str.length()) {
            char chr1 = str.charAt(i);
            result = chr1 >= '\u4e00' && chr1 <= '\u29fa5' ? String.valueOf(result) + "\\u" + Integer.toHexString(chr1) : String.valueOf(result) + str.charAt(i);
            ++i;
        }
        return result;
    }

    public static String unicodeToString(String unicodeStr) {
        return null;
    }

    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    public static String replaceBlank(String str) {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    public static String toStringByObject(Object obj) {
        return StringUtils.toStringByObject(obj, false, null);
    }

    public static String toStringByObject(Object obj, boolean isqdkg) {
        return StringUtils.toStringByObject(obj, isqdkg, null);
    }

    public static String toStringByObject(Object obj, boolean isqdkg, String datatype) {
        if (obj == null) {
            return "";
        }
        if (isqdkg) {
            return obj.toString().trim();
        }
        if (StringUtils.hasText((String)datatype)) {
            if (obj instanceof Timestamp) {
                return DateConverUtil.getSbyDT((Timestamp)obj, datatype);
            }
            if (obj instanceof Date) {
                return DateConverUtil.getSbyDT((Timestamp)obj, datatype);
            }
        }
        return obj.toString();
    }

    public static Integer toIntegerByObject(Object obj) {
        try {
            return Integer.valueOf(StringUtils.toStringByObject(obj, true));
        }
        catch (Exception e) {
            return 0;
        }
    }

    public static Double toDoubleByObject(Object obj) {
        try {
            return Double.valueOf(StringUtils.toStringByObject(obj, true));
        }
        catch (Exception e) {
            return 0.0;
        }
    }

    public static Float toFloatByObject(Object obj) {
        try {
            return Float.valueOf(StringUtils.toStringByObject(obj, true));
        }
        catch (Exception e) {
            return Float.valueOf(0.0f);
        }
    }

    public static String toStringBySpilt(List<String> list) {
        if (list != null && list.size() > 0) {
            StringBuffer sb = new StringBuffer();
            int i = 0;
            int b = list.size();
            while (i < b) {
                String s = list.get(i);
                if (StringUtils.hasText((String)s)) {
                    if (i < b - 1) {
                        sb.append(s).append(",");
                    } else {
                        sb.append(s);
                    }
                }
                ++i;
            }
            return sb.toString();
        }
        return "";
    }

    public static String toStringBySqlIn(List<String> list) {
        if (list != null && list.size() > 0) {
            StringBuffer sb = new StringBuffer();
            int i = 0;
            int b = list.size();
            while (i < b) {
                String s = list.get(i);
                if (StringUtils.hasText((String)s)) {
                    if (i < b - 1) {
                        sb.append("'").append(s).append("',");
                    } else {
                        sb.append("'").append(s).append("'");
                    }
                }
                ++i;
            }
            return sb.toString();
        }
        return "'-0'";
    }

    public static String toStringBySqlIn(String[] ss) {
        if (ss != null && ss.length > 0) {
            StringBuffer sb = new StringBuffer();
            int i = 0;
            int b = ss.length;
            while (i < b) {
                String s = ss[i];
                if (StringUtils.hasText((String)s)) {
                    if (i < b - 1) {
                        sb.append("'").append(s).append("',");
                    } else {
                        sb.append("'").append(s).append("'");
                    }
                }
                ++i;
            }
            return sb.toString();
        }
        return "";
    }

    public static String[] getArrayByArray(String[] objs) {
        if (objs != null && objs.length > 0) {
            ArrayList<String> list = new ArrayList<String>();
            String[] stringArray = objs;
            int n = objs.length;
            int n2 = 0;
            while (n2 < n) {
                String o = stringArray[n2];
                if (StringUtils.hasText((String)o)) {
                    list.add(o);
                }
                ++n2;
            }
            String[] ss = new String[list.size()];
            int i = 0;
            int b = list.size();
            while (i < b) {
                ss[i] = (String)list.get(i);
                ++i;
            }
            return ss;
        }
        return new String[0];
    }

    public static String getUUID32() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getUUID36() {
        return UUID.randomUUID().toString();
    }

    public static Object strToType(Object str, String type) {
        Object obj = str;
        if (str != null) {
            if ("java.lang.Integer".equals(type) || "int".equals(type)) {
                obj = str instanceof Double ? Integer.valueOf((int)Double.valueOf(str.toString()).doubleValue()) : Integer.valueOf(str.toString());
            } else if ("java.lang.Long".equals(type) || "long".equals(type)) {
                obj = Long.parseLong(str.toString());
            } else if ("java.lang.String".equals(type)) {
                obj = str.toString();
            } else if ("java.util.Date".equals(type)) {
                if (!(str instanceof Date)) {
                    obj = DateConverUtil.getDbyST(str.toString(), new String[0]);
                }
            } else if ("java.lang.Double".equals(type) || "double".equals(type)) {
                obj = Double.valueOf(str.toString());
            } else if ("java.lang.Short".equals(type) || "short".equals(type)) {
                obj = Short.valueOf(str.toString());
            } else if ("java.lang.Boolean".equals(type) || "boolean".equals(type)) {
                obj = Boolean.valueOf(str.toString());
            } else if ("java.lang.Float".equals(type) || "float".equals(type)) {
                obj = Float.valueOf(str.toString());
            }
        }
        return obj;
    }

    public static String getColumnToFiled(String word) {
        char[] chs = word.toCharArray();
        StringBuilder sb = new StringBuilder(word);
        int count = 0;
        int i = 0;
        while (i < chs.length) {
            char ch = chs[i];
            if (Character.isUpperCase(ch)) {
                sb.replace(count + i, count + i + 1, Character.toString(Character.toLowerCase(ch)));
                sb.insert(count + i, '_');
                ++count;
            }
            ++i;
        }
        return sb.toString();
    }
}

