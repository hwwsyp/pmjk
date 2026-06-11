package com.tpfh.fintech.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
/*
import com.tpfh.fintech.openapi.sdk.client.OpenClient;
import com.tpfh.fintech.openapi.sdk.request.CommonRequest;
import com.tpfh.fintech.openapi.sdk.response.CommonResponse;
*/
/**  
 * <p>Title: EmailUtils</p>  
 * <p>Description: </p>  
 * @author 陆思琦  
 * @date 2019年5月5日  
 */
@Component
public class EmailUtils {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	/*@Value("${tpfh.email.url}")
    private String url;
	@Value("${tpfh.email.app_key}")
    private String appKey;
	@Value("${tpfh.email.secret}")
    private String secret;
	@Value("${tpfh.email.method}")
    private String method;
	@Value("${tpfh.email.version}")
    private String version;*/
	
	public Map<String,Object> sendEmail(HashMap<String, Object> params){
		Map<String, Object> resultMap = new HashMap<String, Object>();
		/*OpenClient client = new OpenClient(url, appKey, secret);
		CommonRequest request = new CommonRequest(method);
		request.setVersion(version);
		request.setData(params);
		CommonResponse response = client.execute(request);
		if (response.isSuccess()) {
			resultMap = response.getData();
		} else {
			resultMap.put("code", response.getCode());
			resultMap.put("msg", response.getMsg());
		}*/
		logger.info(resultMap.toString());
		return resultMap;
	}
}
