/*
 * Decompiled with CFR 0.152.
 */
package com.tpfh.fintech.modules.sys.oauth2;

import com.tpfh.fintech.common.exception.TpfhException;
import java.security.MessageDigest;
import java.util.UUID;

public class TokenGenerator {
    private static final char[] hexCode = "0123456789abcdef".toCharArray();

    public static String generateValue() {
        return TokenGenerator.generateValue(UUID.randomUUID().toString());
    }

    public static String toHexString(byte[] data) {
        if (data == null) {
            return null;
        }
        StringBuilder r = new StringBuilder(data.length * 2);
        byte[] byArray = data;
        int n = data.length;
        int n2 = 0;
        while (n2 < n) {
            byte b = byArray[n2];
            r.append(hexCode[b >> 4 & 0xF]);
            r.append(hexCode[b & 0xF]);
            ++n2;
        }
        return r.toString();
    }

    public static String generateValue(String param) {
        try {
            MessageDigest algorithm = MessageDigest.getInstance("MD5");
            algorithm.reset();
            algorithm.update(param.getBytes());
            byte[] messageDigest = algorithm.digest();
            return TokenGenerator.toHexString(messageDigest);
        }
        catch (Exception e) {
            throw new TpfhException("\u751f\u6210Token\u5931\u8d25", e);
        }
    }
}

