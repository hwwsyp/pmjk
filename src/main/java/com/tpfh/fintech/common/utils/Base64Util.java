/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.springframework.util.StringUtils
 */
package com.tpfh.fintech.common.utils;

import java.math.BigInteger;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import org.springframework.util.StringUtils;

public class Base64Util {
    private static final String ALGORITHMSTR = "AES/ECB/PKCS5Padding";

    public static String decrypt(String encrypt) throws Exception {
        return Base64Util.decrypt(encrypt, "abcdefgabcdefg12");
    }

    public static String encrypt(String content) throws Exception {
        return Base64Util.encrypt(content, "abcdefgabcdefg12");
    }

    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);
    }

    public static byte[] encryptToBytes(String content, String encryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(1, new SecretKeySpec(encryptKey.getBytes(), "AES"));
        return cipher.doFinal(content.getBytes("utf-8"));
    }

    public static String encrypt(String content, String encryptKey) throws Exception {
        return Base64Util.parseByte2HexStr(Base64Util.encryptToBytes(content, encryptKey));
    }

    public static String decryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        Cipher cipher = Cipher.getInstance(ALGORITHMSTR);
        cipher.init(2, new SecretKeySpec(decryptKey.getBytes(), "AES"));
        byte[] decryptBytes = cipher.doFinal(encryptBytes);
        return new String(decryptBytes);
    }

    public static String decrypt(String encryptStr, String decryptKey) throws Exception {
        return StringUtils.isEmpty((Object)encryptStr) ? null : Base64Util.decryptByBytes(Base64Util.parseHexStr2Byte(encryptStr), decryptKey);
    }

    private static String parseByte2HexStr(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        int i = 0;
        while (i < buf.length) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = String.valueOf('0') + hex;
            }
            sb.append(hex.toUpperCase());
            ++i;
        }
        return sb.toString();
    }

    private static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1) {
            return null;
        }
        byte[] result = new byte[hexStr.length() / 2];
        int i = 0;
        while (i < hexStr.length() / 2) {
            int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte)(high * 16 + low);
            ++i;
        }
        return result;
    }
}

