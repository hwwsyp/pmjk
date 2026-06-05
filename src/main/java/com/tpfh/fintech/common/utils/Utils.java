/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSONArray
 *  com.alibaba.fastjson.JSONObject
 */
package com.tpfh.fintech.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tpfh.fintech.common.utils.StringUtils;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {
    public static final String REG_MOBILE = "1[3458]\\d{9}";
    public static final String REG_TEL = "(0\\d{2,3}-?\\d{7,8}(-\\d{3,})?)|(400\\d{7})";
    public static final String REG_MAIL = "\\w+([\\-+\\.]\\w+)*@\\w+([\\-\\.]\\w+)*\\.\\w+([\\-\\.]\\w+)*";
    public static final int MILLISECOND = 0;
    public static final int SECOND = 1;
    public static final int MINUTE = 2;
    public static final int HOUR = 3;
    public static final int DAY = 4;

    public static int getAuthYear(String apply_date, String auth_date) {
        if (!apply_date.matches("\\d{4}-\\d{2}-\\d{2}") || !auth_date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return 0;
        }
        int apply_year = Utils.parseInt(apply_date.substring(0, 4));
        int apply_month = Utils.parseInt(apply_date.substring(5, 7));
        int apply_day = Utils.parseInt(apply_date.substring(8, 10));
        int auth_year = Utils.parseInt(auth_date.substring(0, 4));
        int auth_month = Utils.parseInt(auth_date.substring(5, 7));
        int auth_day = Utils.parseInt(auth_date.substring(8, 10));
        if (apply_year == auth_year) {
            return 1;
        }
        if (auth_month > apply_month) {
            return auth_year - apply_year + 1;
        }
        if (auth_month == apply_month && auth_day >= apply_day) {
            return auth_year - apply_year + 1;
        }
        return auth_year - apply_year;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.equals("");
    }

    public static String getString(Object obj) {
        if (obj == null || obj.toString().equals("null")) {
            return "";
        }
        return obj.toString();
    }

    public static String getString(Object obj, String def) {
        if (obj == null || obj.toString().equals("null")) {
            return def;
        }
        return obj.toString().trim();
    }

    public static String getServerTime(String parrent) {
        if (parrent == null || parrent.equals("")) {
            parrent = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            Calendar c = Calendar.getInstance(Locale.CHINESE);
            SimpleDateFormat sformat = new SimpleDateFormat(parrent, Locale.CHINA);
            return sformat.format(c.getTime());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ("" + new Timestamp(System.currentTimeMillis())).substring(0, 19);
        }
    }

    public static String formatDate(Date date) {
        try {
            SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            return sformat.format(date);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getServerTime(String parrent, int field, int offset) {
        if (parrent == null || parrent.equals("")) {
            parrent = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            Calendar c = Calendar.getInstance(Locale.CHINESE);
            SimpleDateFormat sformat = new SimpleDateFormat(parrent);
            c.add(field, offset);
            return sformat.format(c.getTime());
        }
        catch (Exception e) {
            e.printStackTrace();
            return ("" + new Timestamp(System.currentTimeMillis())).substring(0, 19);
        }
    }

    public static String getDatetime(String datetime, int field, int offset) {
        String parrent = "yyyy-MM-dd HH:mm:ss";
        try {
            SimpleDateFormat sformat = new SimpleDateFormat(parrent);
            Calendar c = Calendar.getInstance(Locale.CHINESE);
            c.setTime(sformat.parse(datetime));
            c.add(field, offset);
            return sformat.format(c.getTime());
        }
        catch (Exception e) {
            e.printStackTrace();
            return datetime;
        }
    }

    public static String getDatetime(String datetime, String parrent, int field, int offset) {
        if (parrent == null || parrent.equals("")) {
            parrent = "yyyy-MM-dd HH:mm:ss";
        }
        try {
            SimpleDateFormat sformat = new SimpleDateFormat(parrent);
            Calendar c = Calendar.getInstance(Locale.CHINESE);
            c.setTime(sformat.parse(datetime));
            c.add(field, offset);
            return sformat.format(c.getTime());
        }
        catch (Exception e) {
            e.printStackTrace();
            return datetime;
        }
    }

    public static String getDate(String date, int field, int offset) {
        String parrent = "yyyy-MM-dd";
        try {
            date = date.substring(0, 10);
            SimpleDateFormat sformat = new SimpleDateFormat(parrent);
            Calendar c = Calendar.getInstance(Locale.CHINESE);
            c.setTime(sformat.parse(date));
            c.add(field, offset);
            return sformat.format(c.getTime());
        }
        catch (Exception e) {
            e.printStackTrace();
            return date;
        }
    }

    public static double parseDouble(Object str) {
        if (str == null) {
            return 0.0;
        }
        String s = str.toString().trim();
        if (!s.matches("-?\\d+(\\.\\d+)?")) {
            return 0.0;
        }
        return Double.parseDouble(s);
    }

    public static float parseFloat(Object str) {
        if (str == null) {
            return 0.0f;
        }
        String s = str.toString().trim();
        if (!s.matches("-?\\d+(\\.\\d+)?")) {
            return 0.0f;
        }
        return Float.parseFloat(s);
    }

    public static int parseInt(Object str) {
        return Utils.parseInt(str, 0);
    }

    public static int parseInt(Object str, int defaultValue) {
        if (str == null || str.equals("")) {
            return defaultValue;
        }
        String s = str.toString().trim();
        if (!s.matches("-?\\d+")) {
            return defaultValue;
        }
        return Integer.parseInt(s);
    }

    public static long parseLong(Object str) {
        if (str == null || str.equals("")) {
            return 0L;
        }
        String s = str.toString().trim();
        if (!s.matches("-?\\d+")) {
            return 0L;
        }
        return Long.parseLong(s);
    }

    public static String fill0Left(String str, int length) {
        int len;
        if (str == null) {
            str = "";
        }
        if ((len = length - str.length()) > 0) {
            int i = 0;
            while (i < len) {
                str = "0" + str;
                ++i;
            }
        }
        return str;
    }

    public static String toString(Map<?, ?> map) {
        if (map == null) {
            return "";
        }
        if (map.isEmpty()) {
            return "{}";
        }
        StringBuffer buf = new StringBuffer();
        buf.append("{");
        Iterator<Map.Entry<?, ?>> i = map.entrySet().iterator();
        boolean hasNext = i.hasNext();
        while (hasNext) {
            Map.Entry<?, ?> e = i.next();
            Object key = e.getKey();
            Object value = e.getValue();
            buf.append((Object)(key == map ? "(this Map)" : key));
            buf.append("=");
            if (value == map) {
                buf.append("(this Map)");
            } else if (value instanceof Object[]) {
                Object[] objs = (Object[])value;
                buf.append("[");
                int j = 0;
                while (j < objs.length) {
                    if (j > 0) {
                        buf.append(", ");
                    }
                    buf.append(objs[j]);
                    ++j;
                }
                buf.append("]");
            } else if (value instanceof Map) {
                buf.append(Utils.toString((Map)value));
            } else {
                buf.append(value);
            }
            hasNext = i.hasNext();
            if (!hasNext) continue;
            buf.append(", ");
        }
        buf.append("}");
        return buf.toString();
    }

    public static String toString(Object[] objs) {
        if (objs == null) {
            return "";
        }
        StringBuffer buf = new StringBuffer();
        buf.append("[");
        int j = 0;
        while (j < objs.length) {
            if (j > 0) {
                buf.append(", ");
            }
            buf.append(String.valueOf(objs[j]));
            ++j;
        }
        buf.append("]");
        return buf.toString();
    }

    public static String toString(List<HashMap<String, Object>> objs) {
        if (objs == null) {
            return "";
        }
        StringBuffer buf = new StringBuffer();
        buf.append("[");
        int j = 0;
        for (Map map : objs) {
            if (j > 0) {
                buf.append(", ");
            }
            buf.append(Utils.toString(map));
            ++j;
        }
        buf.append("]");
        return buf.toString();
    }

    public static boolean beforeToday(String date) {
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = sformat.parse(String.valueOf(date) + " 00:00:00");
            Date now = sformat.parse(String.valueOf(Utils.getServerTime("yyyy-MM-dd")) + " 00:00:00");
            return d.before(now);
        }
        catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean afterToday(String date) {
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d = sformat.parse(String.valueOf(date) + " 00:00:00");
            Date now = sformat.parse(String.valueOf(Utils.getServerTime("yyyy-MM-dd")) + " 00:00:00");
            return d.after(now);
        }
        catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isToday(String date) {
        return date.equals(Utils.getServerTime("yyyy-MM-dd"));
    }

    public static boolean isWeekday(int weekday) {
        return new Date().getDay() == weekday - 1;
    }

    public static boolean inDate(String startDate, String endDate) {
        return !Utils.afterToday(startDate) && !Utils.beforeToday(endDate);
    }

    public static boolean inTime(String startTime, String endTime) {
        int now;
        if ((startTime = startTime.replace(":", "")).length() == 4) {
            startTime = String.valueOf(startTime) + "00";
        }
        if ((endTime = endTime.replace(":", "")).length() == 4) {
            endTime = String.valueOf(endTime) + "00";
        }
        return (now = Integer.parseInt(Utils.getServerTime("HHmmss"))) >= Integer.parseInt(startTime) && now <= Integer.parseInt(endTime);
    }

    public static boolean beforeNow(String time) {
        if ((time = time.replace(":", "")).length() == 4) {
            time = String.valueOf(time) + "00";
        }
        int now = Integer.parseInt(Utils.getServerTime("HHmmss"));
        return Integer.parseInt(time) < now;
    }

    public static boolean afterNow(String time) {
        if ((time = time.replace(":", "")).length() == 4) {
            time = String.valueOf(time) + "00";
        }
        int now = Integer.parseInt(Utils.getServerTime("HHmmss"));
        return Integer.parseInt(time) > now;
    }

    public static String toString(double d) {
        BigDecimal bd = new BigDecimal(d);
        String s = bd.toPlainString();
        String[] ss = s.split("\\.");
        if (ss.length == 2 && ss[1].length() > 10) {
            return String.valueOf(ss[0]) + "." + ss[1].substring(0, 10);
        }
        return s;
    }

    public static double toDouble(double d) {
        BigDecimal bd = new BigDecimal(d);
        String s = bd.toPlainString();
        String[] ss = s.split("\\.");
        if (ss.length == 2 && ss[1].length() > 10) {
            return Double.parseDouble(String.valueOf(ss[0]) + "." + ss[1].substring(0, 10));
        }
        return Double.parseDouble(s);
    }

    public static String getWeekStr(int week) {
        switch (week) {
            case 1: {
                return "\u5468\u65e5";
            }
            case 2: {
                return "\u5468\u4e00";
            }
            case 3: {
                return "\u5468\u4e8c";
            }
            case 4: {
                return "\u5468\u4e09";
            }
            case 5: {
                return "\u5468\u56db";
            }
            case 6: {
                return "\u5468\u4e94";
            }
            case 7: {
                return "\u5468\u516d";
            }
        }
        return "\u672a\u77e5[" + week + "]";
    }

    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String decode(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    public static String simpleDatetime(String datetime) {
        if (datetime == null) {
            return "";
        }
        if (datetime.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
            return datetime.substring(5, 16);
        }
        if (datetime.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return datetime.substring(5, 10);
        }
        if (datetime.matches("\\d{2}:\\d{2}:\\d{2}")) {
            return datetime.substring(0, 5);
        }
        return datetime;
    }

    public static String formatDate(String datetime) {
        if (datetime == null) {
            return "";
        }
        if (datetime.length() > 10) {
            return datetime.substring(0, 10);
        }
        return datetime;
    }

    public static boolean matchDate(String date1, String date2) {
        if (date1 == null || date1.equals("")) {
            return true;
        }
        if (date2 == null || date2.equals("")) {
            return false;
        }
        SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date d1 = sformat.parse(String.valueOf(date1) + " 00:00:00");
            Date d2 = sformat.parse(String.valueOf(date2) + " 00:00:00");
            return d1.equals(d2) || d1.before(d2);
        }
        catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isMobile(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return str.matches(REG_MOBILE);
    }

    public static boolean isTelephone(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return str.matches(REG_TEL);
    }

    public static boolean isMail(String str) {
        if (str == null || str.equals("")) {
            return false;
        }
        return str.matches(REG_MAIL);
    }

    public static String date(int y, int m, int d) {
        String date = String.valueOf(y) + "-";
        date = m < 10 ? String.valueOf(date) + "0" + m + "-" : String.valueOf(date) + m + "-";
        date = d < 10 ? String.valueOf(date) + "0" + d : String.valueOf(date) + d;
        return date;
    }

    public static Date toDate(String datetime) {
        if (datetime == null || datetime.length() < 0) {
            return null;
        }
        if (datetime.matches("\\d{14,}")) {
            datetime = String.valueOf(datetime.substring(0, 4)) + "-" + datetime.substring(4, 6) + "-" + datetime.substring(6, 8) + " " + datetime.substring(8, 10) + ":" + datetime.substring(10, 12) + ":" + datetime.substring(12, 14);
        } else if (datetime.matches("\\d{4}-\\d{2}-\\d{2}")) {
            datetime = String.valueOf(datetime) + " 00:00:00";
        } else if (datetime.matches("\\d{2}:\\d{2}:\\d{2}(.\\d+)?")) {
            datetime = String.valueOf(Utils.getServerTime("yyyy-MM-dd")) + " " + datetime;
        } else if (!datetime.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}(.\\d+)?")) {
            try {
                return new Date(datetime);
            }
            catch (Exception e) {
                System.err.println("Unparseable date: \"" + datetime + "\"");
                return null;
            }
        }
        try {
            SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sformat.parse(datetime);
        }
        catch (Exception e) {
            System.err.println("Unparseable date: \"" + datetime + "\"");
            return null;
        }
    }

    public static long timeDiff(String time1, String time2, int unit) {
        return Utils.timeDiff(time1, time2, unit, true);
    }

    public static long timeDiff(String time1, String time2, int unit, boolean flag) {
        Date date1 = Utils.toDate(time1);
        Date date2 = Utils.toDate(time2);
        long ltime = date2.getTime() - date1.getTime();
        if (flag) {
            ltime = Math.abs(ltime);
        }
        if (unit == 1) {
            return ltime / 1000L;
        }
        if (unit == 2) {
            return ltime / 60000L;
        }
        if (unit == 3) {
            return ltime / 3600000L;
        }
        if (unit == 4) {
            return ltime / 86400000L;
        }
        return ltime;
    }

    public static String genRandomNum(int len) {
        int maxNum = 10;
        int count = 0;
        char[] str = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        StringBuffer num = new StringBuffer("");
        Random r = new Random();
        while (count < len) {
            int i = Math.abs(r.nextInt(10));
            if (i < 0 || i >= str.length) continue;
            num.append(str[i]);
            ++count;
        }
        return num.toString();
    }

    public static String getMaxSN(String currentMaxSN, String firstPart, int len) {
        int length;
        String initSN = "";
        if ("".equals(currentMaxSN) || currentMaxSN == null) {
            String initTwoPart = "";
            int i = 0;
            while (i < len) {
                initTwoPart = String.valueOf(initTwoPart) + "0";
                ++i;
            }
            initSN = String.valueOf(firstPart) + initTwoPart;
        } else {
            initSN = currentMaxSN;
        }
        int num = Integer.parseInt(initSN.replace(firstPart, ""));
        String numStr = String.valueOf(++num);
        int i = length = numStr.length();
        while (i < len) {
            numStr = "0" + numStr;
            ++i;
        }
        return String.valueOf(firstPart) + numStr;
    }

    public static String getSN(String firstPart, int len) {
        return String.valueOf(firstPart) + Utils.getServerTime("yyyyMMdd") + Utils.genRandomNum(len);
    }

    public static String getSN(String firstPart, String format, int len) {
        return String.valueOf(firstPart) + Utils.getServerTime(format) + Utils.genRandomNum(len);
    }

    public static String getFileStr(String[] files) {
        String fileStr = "";
        if (files.length > 0) {
            int i = 0;
            while (i < files.length) {
                fileStr = i == 0 ? String.valueOf(fileStr) + files[i] : String.valueOf(fileStr) + "," + files[i];
                ++i;
            }
        }
        return fileStr;
    }

    public static String getFileListStr(String[] fileList) {
        if (fileList != null) {
            JSONArray arr = new JSONArray();
            int i = 0;
            while (i < fileList.length) {
                arr.add(JSONObject.parse((String)fileList[i]));
                ++i;
            }
            return arr.toString();
        }
        return null;
    }

    public static String cent2Yuan(String cent) {
        if (cent == null || "".equals(cent)) {
            return "0";
        }
        String result = String.valueOf((float)Math.round(Double.valueOf(cent) / 100.0 * 100.0) / 100.0f);
        return result.substring(0, result.indexOf("."));
    }

    public static String yuan2Cent(String yuan) {
        if (yuan == null) {
            return "0";
        }
        return String.valueOf(Math.round(Double.valueOf(yuan) * 100.0));
    }

    public static String formatDateTime(String datetime) {
        if (datetime == null) {
            return "";
        }
        if (datetime.length() > 19) {
            return datetime.substring(0, 19);
        }
        return datetime;
    }

    public static String getDefaultPassword(int length) {
        String password = "";
        int i = 0;
        while (i < length) {
            char[] codeSeq = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7', '8', '9'};
            password = String.valueOf(password) + String.valueOf(codeSeq[new Random().nextInt(codeSeq.length)]);
            ++i;
        }
        return password;
    }

    public static boolean isEmail(String email) {
        boolean flag = false;
        try {
            Pattern regex = Pattern.compile(REG_MAIL);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        }
        catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static String[][] getDateBefore(String date, int day) {
        String[][] res = new String[2][];
        String[] real_date = new String[day];
        String[] show_date = new String[day];
        try {
            Calendar now = Calendar.getInstance();
            SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            SimpleDateFormat show_date_format = new SimpleDateFormat("d/M", Locale.CHINA);
            now.setTime(sformat.parse(date));
            now.set(5, now.get(5) - day - 1);
            int index = 0;
            int i = 1;
            while (i <= day) {
                now.set(5, now.get(5) + 1);
                real_date[index] = sformat.format(now.getTime());
                show_date[index] = show_date_format.format(now.getTime());
                ++index;
                ++i;
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        res[0] = real_date;
        res[1] = show_date;
        return res;
    }

    public static String getDateBefore(int day) {
        try {
            Calendar now = Calendar.getInstance();
            SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            now.setTime(sformat.parse(Utils.getServerTime("yyyy-MM-dd")));
            now.set(5, now.get(5) - day - 1);
            return sformat.format(now.getTime());
        }
        catch (ParseException e) {
            e.printStackTrace();
            return Utils.getServerTime("yyyy-MM-dd");
        }
    }

    public static void main(String[] args) {
        System.out.println(Utils.getAuthYear("2014-02-10", "2014-03-10"));
    }

    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static String getDecimalFormat(String amount) {
        double formatAmount = Utils.parseDouble(amount);
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.applyPattern("##,###");
        return decimalFormat.format(formatAmount);
    }

    public static String getCode(String prefix) {
        String code = "";
        if (!StringUtils.isEmpty((Object)prefix)) {
            code = Utils.getServerTime("yyyyMMddHHmmss");
        }
        return code;
    }
}

