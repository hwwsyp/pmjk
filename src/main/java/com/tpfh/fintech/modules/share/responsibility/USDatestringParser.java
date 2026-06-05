/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.share.responsibility;

import com.tpfh.fintech.modules.share.responsibility.DateStringParser;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class USDatestringParser
extends DateStringParser {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm z", Locale.US);

    @Override
    public Date kernelHandle(String dateString) {
        this.dateFormat.setLenient(false);
        Date date = null;
        if (dateString != null && dateString.indexOf("GMT+8") > -1) {
            dateString = dateString.replace("GMT+8", "GMT+08:00");
        }
        try {
            date = this.dateFormat.parse(dateString);
        }
        catch (Exception e) {
            return null;
        }
        return date;
    }
}

