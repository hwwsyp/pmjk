/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.util.StringUtils
 */
package com.tpfh.fintech.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.springframework.util.StringUtils;

public final class DateConverUtil {
    public static Date getDbyST(String sdate, String ... toTypes) {
        Date date = null;
        if (StringUtils.hasText((String)sdate)) {
            String toType;
            int n;
            int n2;
            Object[] objectArray;
            boolean a = true;
            if (a) {
                objectArray = toTypes;
                n2 = toTypes.length;
                n = 0;
                while (n < n2) {
                    toType = objectArray[n];
                    try {
                        date = DateConverUtil.getDateFormat(toType).parse(sdate);
                        a = false;
                        break;
                    }
                    catch (Exception exception) {
                        ++n;
                    }
                }
            }
            if (a) {
                objectArray = TimeType.values();
                n2 = objectArray.length;
                n = 0;
                while (n < n2) {
                    toType = objectArray[n];
                    try {
                        date = DateConverUtil.getDateFormat(((TimeType)((Object)toType)).getValue()).parse(sdate);
                        a = false;
                        break;
                    }
                    catch (Exception exception) {
                        ++n;
                    }
                }
            }
        }
        return date;
    }

    public static String getSbyDT(Date date, String type) {
        try {
            return DateConverUtil.getDateFormat(type).format(date);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static String getSbySST(String sdate, String targetType, String ... sourceType) {
        try {
            return DateConverUtil.getSbyDT(DateConverUtil.getDbyST(sdate, sourceType), targetType);
        }
        catch (Exception e) {
            return null;
        }
    }

    public static Date getNowTime() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime();
    }

    public static Date getTimeByYMD(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day);
        return calendar.getTime();
    }

    public static DateFormat getDateFormat(String type) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(type);
        dateFormat.setLenient(false);
        return dateFormat;
    }

    public static Date TimeCalculate(Date date, int field, int amount) {
        try {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(field, amount);
            return cal.getTime();
        }
        catch (Exception e) {
            return null;
        }
    }

    public static int getCurrentMonthLastDay() {
        Calendar a = Calendar.getInstance();
        a.set(5, 1);
        a.roll(5, -1);
        int maxDate = a.get(5);
        return maxDate;
    }

    public static int getMonthLastDay(int year, int month) {
        Calendar a = Calendar.getInstance();
        a.set(1, year);
        a.set(2, month - 1);
        a.set(5, 1);
        a.roll(5, -1);
        int maxDate = a.get(5);
        return maxDate;
    }

    public static enum TimeType {
        type1("yyyy-MM-dd HH:mm:ss"),
        type2("yyyy/MM/dd HH:mm:ss"),
        type3("yyyy.MM.dd HH:mm:ss"),
        type4("yyyyMMdd HH:mm:ss"),
        type5("yyyy\u5e74MM\u6708dd\u65e5 HH:mm:ss"),
        type11("yyyy-MM-dd HH:mm"),
        type21("yyyy/MM/dd HH:mm"),
        type31("yyyy.MM.dd HH:mm"),
        type41("yyyyMMdd HH:mm"),
        type51("yyyy\u5e74MM\u6708dd\u65e5 HH:mm"),
        type111("yyyy-MM-dd"),
        type211("yyyy/MM/dd"),
        type311("yyyy.MM.dd"),
        type411("yyyyMMdd"),
        type511("yyyy\u5e74MM\u6708dd\u65e5"),
        type1111("yyyy-MM"),
        type2111("yyyy/MM"),
        type3111("yyyy.MM"),
        type4111("yyyyMM"),
        type5111("yyyy\u5e74MM\u6708"),
        type11111("yyyy"),
        type6("HH:mm:ss");

        private final String value;

        public String getValue() {
            return this.value;
        }

        private TimeType(String value) {
            this.value = value;
        }
    }
}

