/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.common.utils;

import com.tpfh.fintech.common.utils.DateUtil;

public class UpbaaDate {
    public int year;
    public int month;
    public int day;
    public double payment;

    public UpbaaDate(int year, int month, int day, double payment) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.payment = payment;
    }

    public UpbaaDate(String stringTime, double payment) {
        try {
            this.year = Integer.parseInt(stringTime.substring(0, 4));
            this.month = Integer.parseInt(stringTime.substring(5, 7));
            this.day = Integer.parseInt(stringTime.substring(8, 10));
        }
        catch (Exception exception) {
            // empty catch block
        }
        this.payment = payment;
    }

    public UpbaaDate() {
        String stringTime = DateUtil.getNowStringDate("/");
        this.setStringDate(stringTime);
    }

    public void setStringDate(String stringTime) {
        try {
            this.year = Integer.parseInt(stringTime.substring(0, 4));
            this.month = Integer.parseInt(stringTime.substring(5, 7));
            this.day = Integer.parseInt(stringTime.substring(8, 10));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public long getDaysFrom1970() {
        return DateUtil.getDaysFrom1970(this.year, this.month, this.day);
    }

    public String getStringDate(String split) {
        if (split == null) {
            split = "-";
        }
        StringBuffer stringDate = new StringBuffer();
        stringDate.append(this.year).append(split);
        if (this.month < 10) {
            stringDate.append(0).append(this.month);
        } else {
            stringDate.append(this.month);
        }
        stringDate.append(split);
        if (this.day < 10) {
            stringDate.append(0).append(this.day);
        } else {
            stringDate.append(this.day);
        }
        return stringDate.toString();
    }
}

