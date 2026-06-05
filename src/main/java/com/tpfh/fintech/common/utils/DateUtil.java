/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static final long Minute_MilliSecond = 60000L;
    public static final long Hour_MilliSecond = 3600000L;
    public static final long Day_MilliSecond = 86400000L;
    public static final long Week_MilliSecond = 604800000L;
    public static final long Month_MilliSecond = 18144000000L;
    public static final String Date_Default_Formate = "yyyyMMdd";
    public static final String Date_Formate_All = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMATE_TRANSACTION = "dd/MM/yyyy, hh:mm";
    public static final String DATE_FORMATE_DAY_HOUR_MINUTE = "MM/dd HH:mm";
    public static final String DATE_FORMATE_HOUR_MINUTE = "HH:mm";
    public static final String DATE_FORMATE_HOUR_MINUTE_SECOND = "HH:mm:ss";
    public static SimpleDateFormat dateFormate = new SimpleDateFormat();

    public static String getNowStringDate(String splite) {
        StringBuffer format = new StringBuffer();
        if (splite == null) {
            format.append(Date_Default_Formate);
        } else {
            format.append("yyyy").append(splite).append("MM").append(splite).append("dd");
        }
        return new SimpleDateFormat(format.toString()).format(new Date());
    }

    public static String getNowStringDateTime(String splite) {
        StringBuffer format = new StringBuffer();
        if (splite == null) {
            format.append(Date_Default_Formate);
        } else {
            format.append("yyyy").append(splite).append("MM").append(splite).append("dd").append(" HH:mm:ss");
        }
        return new SimpleDateFormat(format.toString()).format(new Date());
    }

    public static String getNowStringTime() {
        StringBuffer format = new StringBuffer(DATE_FORMATE_HOUR_MINUTE_SECOND);
        return new SimpleDateFormat(format.toString()).format(new Date());
    }

    public static Date getYesterdayDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, -1);
        return calendar.getTime();
    }

    public static Date getForwardDate(String stringDate) {
        int year = Integer.parseInt(stringDate.substring(0, 4));
        int month = Integer.parseInt(stringDate.substring(5, 7));
        int day = Integer.parseInt(stringDate.substring(8, 10));
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, day);
        c.add(5, 1);
        return c.getTime();
    }

    public static String getForwardStringDate(String stringDate, String splite) {
        int year = Integer.parseInt(stringDate.substring(0, 4));
        int month = Integer.parseInt(stringDate.substring(5, 7));
        int day = Integer.parseInt(stringDate.substring(8, 10));
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, day);
        c.add(5, 1);
        return DateUtil.getStringDateByDate(c.getTime(), splite);
    }

    public static String getStringDateByDate(Date date, String splite) {
        StringBuffer format = new StringBuffer();
        if (splite == null) {
            format.append(Date_Default_Formate);
        } else {
            format.append("yyyy").append(splite).append("MM").append(splite).append("dd");
        }
        if (date == null) {
            date = new Date();
        }
        return new SimpleDateFormat(format.toString()).format(date);
    }

    public static String getStringDateByStringDate(String stringDate, String splite) {
        Date date = DateUtil.getDateByStringTime(stringDate);
        return DateUtil.getStringDateByDate(date, splite);
    }

    public static Date getDateByIntegerDate(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, day);
        return c.getTime();
    }

    public static Date getDateByStringTime(String stringTime) {
        int year = Integer.parseInt(stringTime.substring(0, 4));
        int month = Integer.parseInt(stringTime.substring(5, 7));
        int day = Integer.parseInt(stringTime.substring(8, 10));
        return DateUtil.getDateByIntegerDate(year, month, day);
    }

    public static long getDaysFrom1970(Date date) {
        String stringTime = DateUtil.getStringDateByDate(date, "-");
        int year = Integer.parseInt(stringTime.substring(0, 4));
        int month = Integer.parseInt(stringTime.substring(5, 7));
        int day = Integer.parseInt(stringTime.substring(8, 10));
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, day);
        return (int)(c.getTimeInMillis() / 86400000L);
    }

    public static long getDaysFrom1970(int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, day);
        return (int)(c.getTimeInMillis() / 86400000L);
    }

    public static long getDaysFrom1970(String stringTime, boolean hasSplite) {
        int day;
        int month;
        int year;
        if (hasSplite) {
            year = Integer.parseInt(stringTime.substring(0, 4));
            month = Integer.parseInt(stringTime.substring(5, 7));
            day = Integer.parseInt(stringTime.substring(8, 10));
        } else {
            year = Integer.parseInt(stringTime.substring(0, 4));
            month = Integer.parseInt(stringTime.substring(4, 6));
            day = Integer.parseInt(stringTime.substring(6, 8));
        }
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, day);
        return (int)(c.getTimeInMillis() / 86400000L);
    }

    public static String getYearStartString(String splite) {
        String sDate = DateUtil.getNowStringDate(splite);
        int year = Integer.parseInt(sDate.substring(0, 4));
        String yearStart = new StringBuffer().append(year).append(splite).append("01").append(splite).append("01").toString();
        return yearStart;
    }

    public static Date getYearStartDate() {
        String sDate = DateUtil.getNowStringDate("-");
        int year = Integer.parseInt(sDate.substring(0, 4));
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, 0);
        c.set(5, 1);
        return c.getTime();
    }

    public static String getLastWeekStringDate(String splite) {
        String stringDate = DateUtil.getNowStringDate("-");
        int year = Integer.parseInt(stringDate.substring(0, 4));
        int month = Integer.parseInt(stringDate.substring(5, 7));
        int day = Integer.parseInt(stringDate.substring(8, 10));
        int sundayPlus = DateUtil.getSundayPlus();
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, day);
        c.add(5, -sundayPlus);
        return DateUtil.getStringDateByDate(c.getTime(), splite);
    }

    public static Date getLastWeekDate() {
        String stringDate = DateUtil.getNowStringDate("-");
        int year = Integer.parseInt(stringDate.substring(0, 4));
        int month = Integer.parseInt(stringDate.substring(5, 7));
        int day = Integer.parseInt(stringDate.substring(8, 10));
        int sundayPlus = DateUtil.getSundayPlus();
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, day);
        c.add(5, -sundayPlus);
        return c.getTime();
    }

    public static String getLastMonthStringDate(String splite) {
        String stringDate = DateUtil.getNowStringDate("-");
        int year = Integer.parseInt(stringDate.substring(0, 4));
        int month = Integer.parseInt(stringDate.substring(5, 7));
        int day = Integer.parseInt(stringDate.substring(8, 10));
        int monthPlus = DateUtil.getMonthPlus();
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, day);
        c.add(5, -monthPlus);
        return DateUtil.getStringDateByDate(c.getTime(), splite);
    }

    public static Date getLastMonthDate() {
        String stringDate = DateUtil.getNowStringDate("-");
        int year = Integer.parseInt(stringDate.substring(0, 4));
        int month = Integer.parseInt(stringDate.substring(5, 7));
        int day = Integer.parseInt(stringDate.substring(8, 10));
        int monthPlus = DateUtil.getMonthPlus();
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, day);
        c.add(5, -monthPlus);
        return c.getTime();
    }

    public static String getLastQuarterStringDate(String splite) {
        String stringDate = DateUtil.getNowStringDate("-");
        int year = Integer.parseInt(stringDate.substring(0, 4));
        int month = Integer.parseInt(stringDate.substring(5, 7));
        switch (month) {
            case 1: 
            case 2: 
            case 3: {
                month = 12;
                --year;
                break;
            }
            case 4: 
            case 5: 
            case 6: {
                month = 3;
                break;
            }
            case 7: 
            case 8: 
            case 9: {
                month = 6;
                break;
            }
            case 10: 
            case 11: 
            case 12: {
                month = 9;
                break;
            }
        }
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month);
        c.set(5, 1);
        c.add(5, -1);
        return DateUtil.getStringDateByDate(c.getTime(), splite);
    }

    public static Date getLastQuarterDate() {
        String stringDate = DateUtil.getNowStringDate("-");
        int year = Integer.parseInt(stringDate.substring(0, 4));
        int month = Integer.parseInt(stringDate.substring(5, 7));
        switch (month) {
            case 1: 
            case 2: 
            case 3: {
                month = 12;
                --year;
                break;
            }
            case 4: 
            case 5: 
            case 6: {
                month = 3;
                break;
            }
            case 7: 
            case 8: 
            case 9: {
                month = 6;
                break;
            }
            case 10: 
            case 11: 
            case 12: {
                month = 9;
                break;
            }
        }
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month);
        c.set(5, 1);
        c.add(5, -1);
        return c.getTime();
    }

    public static String getMonthEndStringDateByMonth(int month, String splite) {
        String stringDate = DateUtil.getNowStringDate("-");
        int year = Integer.parseInt(stringDate.substring(0, 4));
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month);
        c.set(5, 1);
        c.add(5, -1);
        return DateUtil.getStringDateByDate(c.getTime(), splite);
    }

    public static int getSundayPlus() {
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(7) - 1;
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }
        return dayOfWeek;
    }

    public static int getMonthPlus() {
        Calendar c = Calendar.getInstance();
        int dayOfMonth = c.get(5);
        return dayOfMonth;
    }

    public static long getTwoTimeInterval(String time01, String time02) {
        long t01 = DateUtil.stringTimeToMilliseconds(time01);
        long t02 = DateUtil.stringTimeToMilliseconds(time02);
        return Math.abs((t01 - t02) / 60000L);
    }

    public static long stringTimeToMilliseconds(String time) {
        int year = Integer.parseInt(time.substring(0, 4));
        int month = Integer.parseInt(time.substring(5, 7));
        int day = Integer.parseInt(time.substring(8, 10));
        int hour = Integer.parseInt(time.substring(11, 13));
        int minus = Integer.parseInt(time.substring(14, 16));
        int second = Integer.parseInt(time.substring(17, 19));
        Calendar c = Calendar.getInstance();
        c.set(1, year);
        c.set(2, month - 1);
        c.set(5, day);
        c.add(11, hour);
        c.add(12, minus);
        c.add(13, second);
        return c.getTimeInMillis();
    }
}

