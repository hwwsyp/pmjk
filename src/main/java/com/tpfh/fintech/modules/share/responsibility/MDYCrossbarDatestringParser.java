/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.share.responsibility;

import com.tpfh.fintech.modules.share.responsibility.DateStringParser;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MDYCrossbarDatestringParser
extends DateStringParser {
    private SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

    @Override
    public Date kernelHandle(String dateString) {
        this.sdf.setLenient(false);
        try {
            return this.sdf.parse(dateString);
        }
        catch (Exception e) {
            return null;
        }
    }
}

