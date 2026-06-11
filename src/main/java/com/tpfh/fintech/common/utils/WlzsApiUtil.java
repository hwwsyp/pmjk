package com.tpfh.fintech.common.utils;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

/**
 * 万链指数API接口
 */
@Component
public class WlzsApiUtil {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Value("${tpfh.wlzs.url}")
	private String API_URL;
	
	@Value("${tpfh.wlzs.uid}")
	private String API_UID;

    /**
     * 获取企业信息
     * @param companyName
     * @param pageSize（默认20）
     * @param pageIndex（默认0）
     * @return
     */
    public Map<String, Object> getGsListByName(String companyName, Integer pageSize, Integer pageIndex){
    	try {
	        long start = System.currentTimeMillis();
	
	        Map requestMap = new HashMap();
	        requestMap.put("uid", API_UID);
	        requestMap.put("keyword", companyName);
	        
	        if(pageSize == null) pageSize = 20;
	        if(pageIndex == null) pageIndex = 0;
	        long skip = pageSize * pageIndex;
	        
	        requestMap.put("skip", skip);
	        String requestData = JSON.toJSONString(requestMap);
	
	        String tempTime = start + "";
	        String token = (API_UID.substring(API_UID.length()-8, API_UID.length())) + (tempTime.substring(tempTime.length()-8, tempTime.length()));

			token = Base64Util.encrypt(token);
		
	        requestData = Base64Util.encrypt(requestData);
	
	        token = URLEncoder.encode(token,"utf-8");
	        Map arg = new HashMap();
	        arg.put("uid", API_UID);
	        arg.put("securityData", requestData);
	        arg.put("time", start);
	        arg.put("token", token);
	
	        String url = API_URL + "/v2/enterprise/searchListPaging";
	
	        String result = WlzsHttpUtil.sendJsonReq(url, arg);
	        // 解析json,并返回结果
	        Map<String, Object> resultMap = (Map<String, Object>)JSONObject.parseObject(result); 
	        
	        List<Map<String, Object>> data = null;
	        Map<String, Object> map = new HashMap<String, Object>();
	        if (resultMap != null) {
	        	//响应code码。200：成功，其他失败
	            if (resultMap.get("status") != null && "200".equals(String.valueOf(resultMap.get("status")))) {
	            	String dataStr = resultMap.get("data").toString();
	        		Map<String, Object> dataMap = (Map<String, Object>)JSONObject.parseObject(dataStr);
	                if(dataMap != null){
	                	data = (List<Map<String, Object>>) dataMap.get("items");
	                }
	            }
	        	map.put("requestUrl", url);
	        	map.put("requestParams", arg);
	        	map.put("responseParams", resultMap);
	        	map.put("responseCode", resultMap.get("status"));
	        	map.put("responseMsg", resultMap.get("message"));
	        	map.put("data", data);
	        }
	        return map;
    	} catch (Exception e) {
    		logger.error("万链指数API接口调用失败：" + e.toString());
    		return null;
		}    
    }
    
    /**
     * 根据企业全名或注册号精确获取企业工商详细信息及联系方式
     * @param companyName
     * @return
     */
    public Map<String, Object> getGsxxByName(String companyName){
    	try {
	        long start = System.currentTimeMillis();
	
	        Map requestMap = new HashMap();
	        requestMap.put("uid", API_UID);
	        requestMap.put("keyword", companyName);
	        String requestData = JSON.toJSONString(requestMap);
	
	        String tempTime = start + "";
	        String token = (API_UID.substring(API_UID.length()-8, API_UID.length())) + (tempTime.substring(tempTime.length()-8, tempTime.length()));

			token = Base64Util.encrypt(token);
		
	        requestData = Base64Util.encrypt(requestData);
	
	        token = URLEncoder.encode(token,"utf-8");
	        Map arg = new HashMap();
	        arg.put("uid", API_UID);
	        arg.put("securityData", requestData);
	        arg.put("time", start);
	        arg.put("token", token);
	
	        String url = API_URL + "/enterprise/getDetailAndContactByName";
	
	        String result = WlzsHttpUtil.sendJsonReq(url, arg);
	        // 解析json,并返回结果
	        Map<String, Object> resultMap = (Map<String, Object>)JSONObject.parseObject(result); 
	        
	        String id= null,creditNo = null, operName = null, status = null, registCapi = null, address = null, termStart = null,domains = null,belongOrg=null ;
	        Map<String, Object> map = new HashMap<String, Object>();
	        if (resultMap != null) {
	        	//响应code码。200：成功，其他失败
	            if (resultMap.get("status") != null && "200".equals(String.valueOf(resultMap.get("status")))) {
	        		String data = resultMap.get("data").toString();
	        		Map<String, Object> dataMap = (Map<String, Object>)JSONObject.parseObject(data);
	                if(dataMap != null){
	                	id = dataMap.get("id")==null?null:dataMap.get("id").toString();
	            		creditNo = dataMap.get("credit_no")==null?null:dataMap.get("credit_no").toString();
	            		operName = dataMap.get("oper_name")==null?null:dataMap.get("oper_name").toString();
	            		status = dataMap.get("status")==null?null:dataMap.get("status").toString();
	            		registCapi = dataMap.get("regist_capi")==null?null:dataMap.get("regist_capi").toString();
	            		address = dataMap.get("address")==null?null:dataMap.get("address").toString();
	            		termStart = dataMap.get("term_start")==null?null:dataMap.get("term_start").toString();
	            		domains = dataMap.get("domains")==null?null:dataMap.get("domains").toString();
	            		belongOrg= dataMap.get("belong_org")==null?null:dataMap.get("belong_org").toString();
	                }
	            }
	        	map.put("requestUrl", url);
	        	map.put("requestParams", arg);
	        	map.put("responseParams", resultMap);
	        	map.put("responseCode", resultMap.get("status"));
	        	map.put("responseMsg", resultMap.get("message"));
	        	map.put("id", id);//统一信用代码
	        	map.put("companyName", companyName);//企业名称
	        	map.put("creditNo", creditNo);//统一信用代码
	        	map.put("operName", operName);//法人
	        	map.put("status", status);//存续状态
	        	map.put("registCapi", registCapi);//注册资本
	        	map.put("address", address);//注册地
	        	map.put("termStart", termStart);//开业时间
	        	map.put("belongOrg", belongOrg);//登记机关
	        	map.put("domains", domains);//经营范围
	        }
	        return map;
    	} catch (Exception e) {
    		logger.error("万链指数API接口调用失败：" + e.toString());
    		return null;
		}    
    }
    
}
