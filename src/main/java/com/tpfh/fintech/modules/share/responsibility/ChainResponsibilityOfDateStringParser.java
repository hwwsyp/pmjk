/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.share.responsibility;

import com.tpfh.fintech.modules.share.responsibility.DateStringParser;
import com.tpfh.fintech.modules.share.responsibility.MDYCrossbarDatestringParser;
import com.tpfh.fintech.modules.share.responsibility.MDYSlashDatestringParser;
import com.tpfh.fintech.modules.share.responsibility.USDatestringParser;
import com.tpfh.fintech.modules.share.responsibility.USDatestringParser2;
import com.tpfh.fintech.modules.share.responsibility.USDatestringParser3;
import com.tpfh.fintech.modules.share.responsibility.YMDCrossbarDatestringParser;
import com.tpfh.fintech.modules.share.responsibility.YMDDatestringParser;
import com.tpfh.fintech.modules.share.responsibility.YMDSlashDatestringParser;
import java.util.Date;

public class ChainResponsibilityOfDateStringParser {
    private static DateStringParser first;

    static {
        YMDCrossbarDatestringParser ymdCrossbarDatestringParser = new YMDCrossbarDatestringParser();
        YMDSlashDatestringParser ymdSlashDatestringParser = new YMDSlashDatestringParser();
        MDYCrossbarDatestringParser mdyCrossbarDatestringParser = new MDYCrossbarDatestringParser();
        USDatestringParser usDatestringParser = new USDatestringParser();
        YMDDatestringParser ymdDatestringParser = new YMDDatestringParser();
        USDatestringParser2 usDatestringParser2 = new USDatestringParser2();
        USDatestringParser3 usDatestringParser3 = new USDatestringParser3();
        MDYSlashDatestringParser mdySlashDatestringParser = new MDYSlashDatestringParser();
        ymdCrossbarDatestringParser.setNext(ymdSlashDatestringParser);
        ymdSlashDatestringParser.setNext(mdyCrossbarDatestringParser);
        mdyCrossbarDatestringParser.setNext(usDatestringParser);
        usDatestringParser.setNext(ymdDatestringParser);
        ymdDatestringParser.setNext(usDatestringParser3);
        usDatestringParser3.setNext(usDatestringParser2);
        usDatestringParser2.setNext(mdySlashDatestringParser);
        first = ymdCrossbarDatestringParser;
    }

    public static Date doHandle(String dateString) throws Exception {
        return first.doHandle(dateString);
    }
}

