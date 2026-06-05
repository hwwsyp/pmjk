/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.common.utils;

import com.tpfh.fintech.common.utils.UpbaaDate;
import java.util.ArrayList;

public class XirrData {
    private static final double Max_Rate = 99999.9;
    private static final double Min_Rate = -0.99999999;
    private static final double Critical = 1.0E-8;
    public static final String Error_Null_List = "NO_CASH";
    public static final String Error_Less_Cash = "NEED_MORE_CASH";
    public static final String Error_Date = "DATE_ORDER_ERROR";
    private long startDays = 0L;
    private ArrayList<UpbaaDate> listUpbaa;

    public XirrData(ArrayList<UpbaaDate> listUpbaa) {
        this.listUpbaa = listUpbaa;
        if (listUpbaa != null) {
            try {
                this.startDays = listUpbaa.get(0).getDaysFrom1970();
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
    }

    public double getPal() {
        if (this.listUpbaa == null) {
            return 0.0;
        }
        double pal = 0.0;
        int count = this.listUpbaa.size();
        int i = 0;
        while (i < count) {
            pal += this.listUpbaa.get((int)i).payment;
            ++i;
        }
        return pal;
    }

    public Object getXirr() {
        if (this.listUpbaa == null) {
            return Error_Null_List;
        }
        int count = this.listUpbaa.size();
        if (count <= 1) {
            return Error_Less_Cash;
        }
        int i = 0;
        while (i < count) {
            if (this.listUpbaa.get(1).getDaysFrom1970() < this.startDays) {
                return Error_Date;
            }
            ++i;
        }
        boolean isEarn = this.getXNPVByRate(0.0) > 0.0;
        double XIRR = 0.0;
        double tempMax = 0.0;
        double tempMin = 0.0;
        int calculateCount = 50;
        if (isEarn) {
            tempMax = 99999.9;
            tempMin = 0.0;
            while (calculateCount > 0) {
                XIRR = (tempMin + tempMax) / 2.0;
                double xnvp = this.getXNPVByRate(XIRR);
                if (xnvp > 0.0) {
                    tempMin = XIRR;
                } else {
                    tempMax = XIRR;
                }
                if (!(Math.abs(XIRR) < 1.0E-8)) {
                    --calculateCount;
                    continue;
                }
                break;
            }
        } else {
            tempMax = 0.0;
            tempMin = -0.99999999;
            while (calculateCount > 0) {
                XIRR = (tempMin + tempMax) / 2.0;
                double xnvp = this.getXNPVByRate(XIRR);
                if (xnvp > 0.0) {
                    tempMin = XIRR;
                } else {
                    tempMax = XIRR;
                }
                if (!(Math.abs(XIRR) < 1.0E-8)) {
                    --calculateCount;
                    continue;
                }
                break;
            }
        }
        return XIRR;
    }

    private double getXNPVByRate(double rate) {
        double result = 0.0;
        int size = this.listUpbaa.size();
        int i = 0;
        while (i < size) {
            UpbaaDate date = this.listUpbaa.get(i);
            result += this.getOneValue(date.payment, rate, (int)date.getDaysFrom1970() - (int)this.startDays);
            ++i;
        }
        return result;
    }

    private double getOneValue(double payment, double rate, int dateDistance) {
        return payment / Math.pow(1.0 + rate, (float)dateDistance / 365.0f);
    }
}

