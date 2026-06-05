/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang.StringUtils
 *  org.joda.time.DateTime
 *  org.joda.time.LocalDate
 *  org.joda.time.format.DateTimeFormat
 *  org.joda.time.format.DateTimeFormatter
 */
package com.tpfh.fintech.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class DateUtils {
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date) {
        return DateUtils.format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank((String)strDate)) {
            return null;
        }
        DateTimeFormatter fmt = DateTimeFormat.forPattern((String)pattern);
        return fmt.parseLocalDateTime(strDate).toDate();
    }

    public static Date[] getWeekStartAndEnd(int week) {
        DateTime dateTime = new DateTime();
        LocalDate date = new LocalDate((Object)dateTime.plusWeeks(week));
        date = date.dayOfWeek().withMinimumValue();
        Date beginDate = date.toDate();
        Date endDate = date.plusDays(6).toDate();
        return new Date[]{beginDate, endDate};
    }

    public static Date addDateSeconds(Date date, int seconds) {
        DateTime dateTime = new DateTime((Object)date);
        return dateTime.plusSeconds(seconds).toDate();
    }

    public static Date addDateMinutes(Date date, int minutes) {
        DateTime dateTime = new DateTime((Object)date);
        return dateTime.plusMinutes(minutes).toDate();
    }

    public static Date addDateHours(Date date, int hours) {
        DateTime dateTime = new DateTime((Object)date);
        return dateTime.plusHours(hours).toDate();
    }

    public static Date addDateDays(Date date, int days) {
        DateTime dateTime = new DateTime((Object)date);
        return dateTime.plusDays(days).toDate();
    }

    public static Date addDateWeeks(Date date, int weeks) {
        DateTime dateTime = new DateTime((Object)date);
        return dateTime.plusWeeks(weeks).toDate();
    }

    public static Date addDateMonths(Date date, int months) {
        DateTime dateTime = new DateTime((Object)date);
        return dateTime.plusMonths(months).toDate();
    }

    public static Date addDateYears(Date date, int years) {
        DateTime dateTime = new DateTime((Object)date);
        return dateTime.plusYears(years).toDate();
    }

    public static String getNowTime() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_PATTERN);
        return df.format(date);
    }
}

