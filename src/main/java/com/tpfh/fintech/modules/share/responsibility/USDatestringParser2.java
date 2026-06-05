/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.share.responsibility;

import com.tpfh.fintech.modules.share.responsibility.DateStringParser;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class USDatestringParser2
extends DateStringParser {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy", Locale.US);

    @Override
    public Date kernelHandle(String dateString) {
        this.dateFormat.setLenient(false);
        Date date = null;
        try {
            date = this.dateFormat.parse(dateString);
        }
        catch (Exception e) {
            return null;
        }
        return date;
    }
}

