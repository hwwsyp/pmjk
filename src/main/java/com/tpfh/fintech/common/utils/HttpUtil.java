/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.http.HttpEntity
 *  org.apache.http.ParseException
 *  org.apache.http.client.methods.CloseableHttpResponse
 *  org.apache.http.client.methods.HttpGet
 *  org.apache.http.client.methods.HttpPost
 *  org.apache.http.client.methods.HttpUriRequest
 *  org.apache.http.client.utils.URIBuilder
 *  org.apache.http.impl.client.CloseableHttpClient
 *  org.apache.http.impl.client.HttpClients
 *  org.apache.http.util.EntityUtils
 *  org.springframework.scheduling.annotation.Async
 *  org.springframework.stereotype.Component
 */
package com.tpfh.fintech.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class HttpUtil {
    public static String sendPost(String url, String param) {
        OutputStreamWriter out = null;
        BufferedReader reader = null;
        String response = "";
        try {
            try {
                String lines;
                URL httpUrl = null;
                httpUrl = new URL(url);
                HttpURLConnection conn = (HttpURLConnection)httpUrl.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("connection", "keep-alive");
                conn.setUseCaches(false);
                conn.setInstanceFollowRedirects(true);
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.connect();
                out = new OutputStreamWriter(conn.getOutputStream());
                out.write(param);
                out.flush();
                reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((lines = reader.readLine()) != null) {
                    lines = new String(lines.getBytes(), "utf-8");
                    response = String.valueOf(response) + lines;
                }
                reader.close();
                conn.disconnect();
            }
            catch (Exception e) {
                System.out.println("\u53d1\u9001 POST \u8bf7\u6c42\u51fa\u73b0\u5f02\u5e38\uff01" + e);
                e.printStackTrace();
                try {
                    if (out != null) {
                        out.close();
                    }
                    if (reader != null) {
                        reader.close();
                    }
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (reader != null) {
                    reader.close();
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return response;
    }

    public static String sendPost(String url) throws Exception {
        String response = null;
        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.createDefault();
                HttpPost httppost = new HttpPost(url);
                httpresponse = httpclient.execute((HttpUriRequest)httppost);
                response = EntityUtils.toString((HttpEntity)httpresponse.getEntity());
            }
            finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @Async
    public void sendGet(String url, Map<String, Object> paramsMap) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            for (Map.Entry<String, Object> params : paramsMap.entrySet()) {
                uriBuilder.setParameter(params.getKey(), String.valueOf(params.getValue()));
            }
            URI uri = uriBuilder.build();
            HttpGet httpGet = new HttpGet(uri);
            CloseableHttpResponse response = null;
            try {
                response = httpclient.execute((HttpUriRequest)httpGet);
                if (response.getStatusLine().getStatusCode() == 200) {
                    System.out.println("\u8bf7\u6c42\u6210\u529f");
                }
            }
            finally {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            }
        }
        catch (IOException | URISyntaxException | ParseException e) {
            e.printStackTrace();
        }
    }
}

