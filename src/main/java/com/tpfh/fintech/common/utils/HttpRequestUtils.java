/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.tpfh.fintech.common.utils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequestUtils {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public static String getData(String reqUrl) {
        System.out.println("===============http\u8bf7\u6c42\u6b63\u5728\u88ab\u6267\u884c===================");
        HttpURLConnection connection = null;
        OutputStream os = null;
        int responseCode = 0;
        StringBuilder sb = new StringBuilder();
        URL url = null;
        try {
            url = new URL(reqUrl);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("content-type", "text/xml;charset=utf-8");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            os = connection.getOutputStream();
            responseCode = connection.getResponseCode();
            String temp = null;
            if (200 == responseCode) {
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);
                while ((temp = br.readLine()) != null) {
                    sb.append(temp);
                }
                System.out.println(sb.toString());
                is.close();
                isr.close();
                br.close();
            }
            os.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("===============http\u8bf7\u6c42\u6267\u884c\u7ed3\u675f===================");
        return sb.toString();
    }

    public static String getDataByParams(String createDate, String reqUrl) {
        System.out.println("===============http\u8bf7\u6c42\u57fa\u91d1\u7f34\u4ed8\u8bb0\u5f55\u5f00\u59cb\u6267\u884c=================");
        URL postUrl = null;
        try {
            postUrl = new URL(reqUrl);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection)postUrl.openConnection();
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        connection.setDoOutput(true);
        connection.setDoInput(true);
        try {
            connection.setRequestMethod("POST");
        }
        catch (ProtocolException e) {
            e.printStackTrace();
        }
        connection.setUseCaches(false);
        connection.setInstanceFollowRedirects(true);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        try {
            connection.connect();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(connection.getOutputStream());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String content = null;
        try {
            content = "createDate=" + URLEncoder.encode(createDate, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            out.writeBytes(content);
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        catch (IOException e1) {
            e1.printStackTrace();
        }
        String line = null;
        try {
            line = reader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("line---?" + line);
        try {
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        connection.disconnect();
        System.out.println("===============http\u8bf7\u6c42\u57fa\u91d1\u7f34\u4ed8\u8bb0\u5f55\u6267\u884c\u7ed3\u675f=================");
        return line;
    }
}

