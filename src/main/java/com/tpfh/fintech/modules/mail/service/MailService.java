/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.mail.service;

import java.util.List;

public interface MailService {
    public void sendMessage(String var1, List<String> var2, List<String> var3, String var4, String var5) throws Exception;

    public void sendMessage(String var1, String var2, String var3) throws Exception;
}

