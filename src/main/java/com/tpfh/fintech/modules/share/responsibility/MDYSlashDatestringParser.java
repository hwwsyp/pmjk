/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.share.responsibility;

import com.tpfh.fintech.modules.share.responsibility.DateStringParser;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MDYSlashDatestringParser
extends DateStringParser {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

    @Override
    public Date kernelHandle(String dateString) {
        this.dateFormat.setLenient(false);
        try {
            return this.dateFormat.parse(dateString);
        }
        catch (Exception e) {
            return null;
        }
    }
}

