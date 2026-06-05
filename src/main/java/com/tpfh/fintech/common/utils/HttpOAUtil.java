/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.client.methods.CloseableHttpResponse
 *  org.apache.http.client.methods.HttpPost
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.entity.StringEntity
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.HttpClients
 *  org.apache.http.util.EntityUtils
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 */
package com.tpfh.fintech.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpOAUtil {
    private static final Logger logger = LoggerFactory.getLogger(HttpOAUtil.class);

    public static String doGet(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        String body = "";
        if (params != null) {
            boolean first = true;
            for (String param : params.keySet()) {
                if (first) {
                    first = false;
                } else {
                    body = String.valueOf(body) + "&";
                }
                String value = params.get(param);
                body = String.valueOf(body) + URLEncoder.encode(param, "UTF-8") + "=";
                body = String.valueOf(body) + URLEncoder.encode(value, "UTF-8");
            }
        }
        url = url.contains("?") ? String.valueOf(url) + body : String.valueOf(url) + "?" + body;
        String retStr = HttpOAUtil.get(url, headers);
        return retStr;
    }

    public static String get(String url) throws IOException {
        return HttpOAUtil.get(url, null);
    }

    public static String get(String url, Map<String, String> headers) throws IOException {
        return HttpOAUtil.fetch("GET", url, null, headers);
    }

    public static String post(String url, String body, Map<String, String> headers) throws IOException {
        return HttpOAUtil.fetch("POST", url, body, headers);
    }

    public static String post(String url, String body) throws IOException {
        return HttpOAUtil.post(url, body, null);
    }

    public static String doPost(String url, Map<String, Object> params) throws IOException {
        return HttpOAUtil.doPost(url, params, null);
    }

    public static String doPost(String url, Map<String, Object> params, Map<String, String> headers) throws IOException {
        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put("Content-Type", "application/x-www-form-urlencoded");
        String body = "";
        if (params != null) {
            boolean first = true;
            for (String param : params.keySet()) {
                if (first) {
                    first = false;
                } else {
                    body = String.valueOf(body) + "&";
                }
                String value = (String)params.get(param);
                body = String.valueOf(body) + URLEncoder.encode(param, "UTF-8") + "=";
                body = String.valueOf(body) + URLEncoder.encode(value, "UTF-8");
            }
        }
        return HttpOAUtil.post(url, body, headers);
    }

    public static String put(String url, String body, Map<String, String> headers) throws IOException {
        return HttpOAUtil.fetch("PUT", url, body, headers);
    }

    public static String put(String url, String body) throws IOException {
        return HttpOAUtil.put(url, body, null);
    }

    public static String delete(String url, Map<String, String> headers) throws IOException {
        return HttpOAUtil.fetch("DELETE", url, null, headers);
    }

    public static String delete(String url) throws IOException {
        return HttpOAUtil.delete(url, null);
    }

    public static String appendQueryParams(String url, Map<String, String> params) throws IOException {
        String fullUrl = url;
        if (params != null) {
            boolean first = fullUrl.indexOf(63) == -1;
            for (String param : params.keySet()) {
                if (first) {
                    fullUrl = String.valueOf(fullUrl) + '?';
                    first = false;
                } else {
                    fullUrl = String.valueOf(fullUrl) + '&';
                }
                String value = params.get(param);
                fullUrl = String.valueOf(fullUrl) + URLEncoder.encode(param, "UTF-8") + '=';
                fullUrl = String.valueOf(fullUrl) + URLEncoder.encode(value, "UTF-8");
            }
        }
        return fullUrl;
    }

    public static Map<String, String> getQueryParams(String url) throws IOException {
        HashMap<String, String> params = new HashMap<String, String>();
        int start = url.indexOf(63);
        while (start != -1) {
            int equals = url.indexOf(61, start);
            String param = "";
            param = equals != -1 ? url.substring(start + 1, equals) : url.substring(start + 1);
            String value = "";
            if (equals != -1) {
                start = url.indexOf(38, equals);
                value = start != -1 ? url.substring(equals + 1, start) : url.substring(equals + 1);
            }
            params.put(URLDecoder.decode(param, "UTF-8"), URLDecoder.decode(value, "UTF-8"));
        }
        return params;
    }

    public static String removeQueryParams(String url) throws IOException {
        int q = url.indexOf(63);
        if (q != -1) {
            return url.substring(0, q);
        }
        return url;
    }

    public static String fetch(String method, String url, String body, Map<String, String> headers) throws IOException {
        logger.info("http\u8bf7\u6c42url\u4e3a" + url);
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection)u.openConnection();
        conn.setConnectTimeout(180000);
        conn.setReadTimeout(180000);
        if (method != null) {
            conn.setRequestMethod(method);
        }
        if (headers != null) {
            for (String key : headers.keySet()) {
                conn.addRequestProperty(key, headers.get(key));
            }
        }
        if (body != null) {
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(body.getBytes());
            os.flush();
            os.close();
        }
        InputStream is = conn.getInputStream();
        String response = HttpOAUtil.streamToString(is);
        is.close();
        if (conn.getResponseCode() == 301) {
            String location = conn.getHeaderField("Location");
            return HttpOAUtil.fetch(method, location, body, headers);
        }
        logger.info("http\u8bf7\u6c42\u54cd\u5e94\u5b57\u7b26\u4e32\u4e3a\uff1a" + response);
        return response;
    }

    public static String streamToString(InputStream in) throws IOException {
        int n;
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        while ((n = in.read(b)) != -1) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    public static String doPostJson(String url, String json) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(url);
        String responseMsg = null;
        try {
            try {
                StringEntity re = new StringEntity(json, "utf-8");
                httppost.setHeader("Content-Type", "application/json;charset=utf-8");
                httppost.setHeader("Authorization", "UZHP9w/S3jbPXekBdrOXtw==");
                httppost.setHeader("name", "test");
                httppost.setHeader("sign", "123456");
                httppost.setEntity((HttpEntity)re);
                CloseableHttpResponse response = httpClient.execute((HttpUriRequest)httppost);
                HttpEntity entity = response.getEntity();
                responseMsg = EntityUtils.toString((HttpEntity)entity, (String)"utf-8");
            }
            catch (Exception e) {
                throw new Exception("webservice\u8bf7\u6c42\u5f02\u5e38", e);
            }
        }
        finally {
            httpClient.getConnectionManager().shutdown();
        }
        return responseMsg;
    }

    public static void main(String[] args) throws IOException {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("token", "301296D089C62EF8AE6C89D80CDAC574D14A3F6E396DD940D1322C99B3665B441824BF6559A2218B630B3EDA7DB06DAA");
        String res = HttpOAUtil.doPost("http://10.133.52.26/ssologin/checkToken", params);
        System.out.println(res);
    }
}

