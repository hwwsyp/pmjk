/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.alibaba.fastjson.JSON
 *  com.alibaba.fastjson.JSONObject
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.beans.factory.annotation.Value
 *  org.springframework.stereotype.Component
 */
package com.tpfh.fintech.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tpfh.fintech.common.utils.Base64Util;
import com.tpfh.fintech.common.utils.WlzsHttpUtil;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WlzsApiUtil {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value(value="${tpfh.wlzs.url}")
    private String API_URL;
    @Value(value="${tpfh.wlzs.uid}")
    private String API_UID;

    public Map<String, Object> getGsListByName(String companyName, Integer pageSize, Integer pageIndex) {
        try {
            long start = System.currentTimeMillis();
            HashMap<String, Object> requestMap = new HashMap<String, Object>();
            requestMap.put("uid", this.API_UID);
            requestMap.put("keyword", companyName);
            if (pageSize == null) {
                pageSize = 20;
            }
            if (pageIndex == null) {
                pageIndex = 0;
            }
            long skip = pageSize * pageIndex;
            requestMap.put("skip", skip);
            String requestData = JSON.toJSONString(requestMap);
            String tempTime = String.valueOf(start);
            String token = String.valueOf(this.API_UID.substring(this.API_UID.length() - 8, this.API_UID.length())) + tempTime.substring(tempTime.length() - 8, tempTime.length());
            token = Base64Util.encrypt(token);
            requestData = Base64Util.encrypt(requestData);
            token = URLEncoder.encode(token, "utf-8");
            HashMap<String, Object> arg = new HashMap<String, Object>();
            arg.put("uid", this.API_UID);
            arg.put("securityData", requestData);
            arg.put("time", start);
            arg.put("token", token);
            String url = String.valueOf(this.API_URL) + "/v2/enterprise/searchListPaging";
            String result = WlzsHttpUtil.sendJsonReq(url, arg);
            JSONObject resultMap = JSONObject.parseObject((String)result);
            List data = null;
            HashMap<String, Object> map = new HashMap<String, Object>();
            if (resultMap != null) {
                String dataStr;
                JSONObject dataMap;
                if (resultMap.get("status") != null && "200".equals(String.valueOf(resultMap.get("status"))) && (dataMap = JSONObject.parseObject((String)(dataStr = resultMap.get("data").toString()))) != null) {
                    data = (List)dataMap.get("items");
                }
                map.put("requestUrl", url);
                map.put("requestParams", arg);
                map.put("responseParams", resultMap);
                map.put("responseCode", resultMap.get("status"));
                map.put("responseMsg", resultMap.get("message"));
                map.put("data", data);
            }
            return map;
        }
        catch (Exception e) {
            this.logger.error("\u4e07\u94fe\u6307\u6570API\u63a5\u53e3\u8c03\u7528\u5931\u8d25\uff1a" + e.toString());
            return null;
        }
    }

    public Map<String, Object> getGsxxByName(String companyName) {
        try {
            long start = System.currentTimeMillis();
            HashMap<String, String> requestMap = new HashMap<String, String>();
            requestMap.put("uid", this.API_UID);
            requestMap.put("keyword", companyName);
            String requestData = JSON.toJSONString(requestMap);
            String tempTime = String.valueOf(start);
            String token = String.valueOf(this.API_UID.substring(this.API_UID.length() - 8, this.API_UID.length())) + tempTime.substring(tempTime.length() - 8, tempTime.length());
            token = Base64Util.encrypt(token);
            requestData = Base64Util.encrypt(requestData);
            token = URLEncoder.encode(token, "utf-8");
            HashMap<String, Object> arg = new HashMap<String, Object>();
            arg.put("uid", this.API_UID);
            arg.put("securityData", requestData);
            arg.put("time", start);
            arg.put("token", token);
            String url = String.valueOf(this.API_URL) + "/enterprise/getDetailAndContactByName";
            String result = WlzsHttpUtil.sendJsonReq(url, arg);
            JSONObject resultMap = JSONObject.parseObject((String)result);
            String id = null;
            String creditNo = null;
            String operName = null;
            String status = null;
            String registCapi = null;
            String address = null;
            String termStart = null;
            String domains = null;
            String belongOrg = null;
            HashMap<String, Object> map = new HashMap<String, Object>();
            if (resultMap != null) {
                String data;
                JSONObject dataMap;
                if (resultMap.get("status") != null && "200".equals(String.valueOf(resultMap.get("status"))) && (dataMap = JSONObject.parseObject((String)(data = resultMap.get("data").toString()))) != null) {
                    id = dataMap.get("id") == null ? null : dataMap.get("id").toString();
                    creditNo = dataMap.get("credit_no") == null ? null : dataMap.get("credit_no").toString();
                    operName = dataMap.get("oper_name") == null ? null : dataMap.get("oper_name").toString();
                    status = dataMap.get("status") == null ? null : dataMap.get("status").toString();
                    registCapi = dataMap.get("regist_capi") == null ? null : dataMap.get("regist_capi").toString();
                    address = dataMap.get("address") == null ? null : dataMap.get("address").toString();
                    termStart = dataMap.get("term_start") == null ? null : dataMap.get("term_start").toString();
                    domains = dataMap.get("domains") == null ? null : dataMap.get("domains").toString();
                    belongOrg = dataMap.get("belong_org") == null ? null : dataMap.get("belong_org").toString();
                }
                map.put("requestUrl", url);
                map.put("requestParams", arg);
                map.put("responseParams", resultMap);
                map.put("responseCode", resultMap.get("status"));
                map.put("responseMsg", resultMap.get("message"));
                map.put("id", id);
                map.put("companyName", companyName);
                map.put("creditNo", creditNo);
                map.put("operName", operName);
                map.put("status", status);
                map.put("registCapi", registCapi);
                map.put("address", address);
                map.put("termStart", termStart);
                map.put("belongOrg", belongOrg);
                map.put("domains", domains);
            }
            return map;
        }
        catch (Exception e) {
            this.logger.error("\u4e07\u94fe\u6307\u6570API\u63a5\u53e3\u8c03\u7528\u5931\u8d25\uff1a" + e.toString());
            return null;
        }
    }
}

