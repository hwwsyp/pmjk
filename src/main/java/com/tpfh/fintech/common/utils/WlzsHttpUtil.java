package com.tpfh.fintech.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import com.alibaba.fastjson2.JSON;

public class WlzsHttpUtil {
	public static String sendJsonReq(String urlStr, Map<String,Object> map) throws Exception {
        String resp = "";
        URL url;
        PrintWriter out = null;
        BufferedReader in = null;
        if (StringUtils.isEmpty(urlStr) || map.size()==0) {
            return "";
        }
        try {
            StringBuilder result = new StringBuilder();
            url = new URL(urlStr);
            URLConnection connection = url.openConnection();
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            String param = JSON.toJSONString(map);

            out = new PrintWriter(connection.getOutputStream());
            out.print(param);
            out.flush();

            in = new BufferedReader(new InputStreamReader(connection
                    .getInputStream()));
            String line = "";
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

            resp = result.toString();

        } catch (Exception e) {
            throw e;
        } finally {
            in.close();
            out.close();
        }
        return resp;
    }

}
