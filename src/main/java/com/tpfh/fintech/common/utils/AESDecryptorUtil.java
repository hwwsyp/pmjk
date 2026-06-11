package com.tpfh.fintech.common.utils;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESDecryptorUtil {
    // 解密函数
    public static String decrypt(String encryptedData, String key, String iv) throws Exception {
        // 转换密钥和IV为字节数组（UTF-8编码）
        byte[] keyBytes = key.getBytes("UTF-8");
        byte[] ivBytes = iv.getBytes("UTF-8");
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData); // Base64解码

        // 初始化密钥和IV参数
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);

        // 配置Cipher实例
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec); // 绑定密钥和IV

        // 执行解密并返回UTF-8字符串
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, "UTF-8");
    }
}
