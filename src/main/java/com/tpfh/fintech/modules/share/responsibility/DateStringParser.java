/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.share.responsibility;

import java.util.Date;

public abstract class DateStringParser {
    private DateStringParser next;

    public DateStringParser getNext() {
        return this.next;
    }

    public void setNext(DateStringParser next) {
        this.next = next;
    }

    public abstract Date kernelHandle(String var1);

    public Date doHandle(String dateString) throws Exception {
        Date date = this.kernelHandle(dateString);
        if (date == null && this.next != null) {
            return this.next.doHandle(dateString);
        }
        return date;
    }
}

