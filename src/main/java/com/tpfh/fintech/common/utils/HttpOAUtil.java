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
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class HttpOAUtil {
	private static final Logger logger = LoggerFactory.getLogger(HttpOAUtil.class);
	
	
	static public String doGet(String url,Map<String, String> params,Map<String,String> headers) throws IOException {
		
		// parse parameters
		String body = "";
		if (params != null) {
			boolean first = true;
			for (String param : params.keySet()) {
				if (first) {
					first = false;
				}
				else {
					body += "&";
				}
				String value = params.get(param);
				body += URLEncoder.encode(param, "UTF-8") + "=";
				body += URLEncoder.encode(value, "UTF-8");
			}
		}
		
		if(url.contains("?")){
			url = url+body;
		}else{
			url = url+"?"+body;
		}
		String retStr = get(url,headers);
		return retStr;
	}
	
	
	
	
	/**
	 * Send a get request
	 * @param url
	 * @return response
	 * @throws IOException 
	 */
	static public String get(String url) throws IOException {
		return get(url, null);
	}

	/**
	 * Send a get request
	 * @param url         Url as string
	 * @param headers     Optional map with headers
	 * @return response   Response as string
	 * @throws IOException 
	 */
	static public String get(String url,
			Map<String, String> headers) throws IOException {
		return fetch("GET", url, null, headers);
	}

	/**
	 * Send a post request
	 * @param url         Url as string
	 * @param body        Request body as string
	 * @param headers     Optional map with headers
	 * @return response   Response as string
	 * @throws IOException 
	 */
	static public String post(String url, String body,
			Map<String, String> headers) throws IOException {
		return fetch("POST", url, body, headers);
	}

	/**
	 * Send a post request
	 * @param url         Url as string
	 * @param body        Request body as string
	 * @return response   Response as string
	 * @throws IOException 
	 */
	static public String post(String url, String body) throws IOException {
		return post(url, body, null);
	}

	/**
	 * Post a form with parameters
	 * @param url         Url as string
	 * @param params      map with parameters/values
	 * @return response   Response as string
	 * @throws IOException 
	 */
	static public String doPost(String url, Map<String, Object> params) 
			throws IOException {
		return doPost(url, params, null);
	}

	/**
	 * Post a form with parameters
	 * @param url         Url as string
	 * @param params      Map with parameters/values
	 * @param headers     Optional map with headers
	 * @return response   Response as string
	 * @throws IOException 
	 */
	static public String doPost(String url, Map<String, Object> params,
			Map<String, String> headers) throws IOException {
		// set content type
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		headers.put("Content-Type", "application/x-www-form-urlencoded");

		// parse parameters
		String body = "";
		if (params != null) {
			boolean first = true;
			for (String param : params.keySet()) {
				if (first) {
					first = false;
				}
				else {
					body += "&";
				}
				String value = (String) params.get(param);
				body += URLEncoder.encode(param, "UTF-8") + "=";
				body += URLEncoder.encode(value, "UTF-8");
			}
		}

		return post(url, body, headers);
	}

	/**
	 * Send a put request
	 * @param url         Url as string
	 * @param body        Request body as string
	 * @param headers     Optional map with headers
	 * @return response   Response as string
	 * @throws IOException 
	 */
	static public String put(String url, String body,
			Map<String, String> headers) throws IOException {
		return fetch("PUT", url, body, headers);
	}

	/**
	 * Send a put request
	 * @param url         Url as string
	 * @return response   Response as string
	 * @throws IOException 
	 */
	static public String put(String url, String body) throws IOException {
		return put(url, body, null);
	}
	
	/**
	 * Send a delete request
	 * @param url         Url as string
	 * @param headers     Optional map with headers
	 * @return response   Response as string
	 * @throws IOException 
	 */
	static public String delete(String url,
			Map<String, String> headers) throws IOException {
		return fetch("DELETE", url, null, headers);
	}
	
	/**
	 * Send a delete request
	 * @param url         Url as string
	 * @return response   Response as string
	 * @throws IOException 
	 */
	static public String delete(String url) throws IOException {
		return delete(url, null);
	}
	
	/**
	 * Append query parameters to given url
	 * @param url         Url as string
	 * @param params      Map with query parameters
	 * @return url        Url with query parameters appended
	 * @throws IOException 
	 */
	static public String appendQueryParams(String url, 
			Map<String, String> params) throws IOException {
		String fullUrl = url;
		if (params != null) {
			boolean first = (fullUrl.indexOf('?') == -1);
			for (String param : params.keySet()) {
				if (first) {
					fullUrl += '?';
					first = false;
				}
				else {
					fullUrl += '&';
				}
				String value = params.get(param);
				fullUrl += URLEncoder.encode(param, "UTF-8") + '=';
				fullUrl += URLEncoder.encode(value, "UTF-8");
			}
		}
		
		return fullUrl;
	}
	
	/**
	 * Retrieve the query parameters from given url
	 * @param url         Url containing query parameters
	 * @return params     Map with query parameters
	 * @throws IOException 
	 */
	static public Map<String, String> getQueryParams(String url) 
			throws IOException {
		Map<String, String> params = new HashMap<String, String>();
	
		int start = url.indexOf('?');
		while (start != -1) {
			// read parameter name
			int equals = url.indexOf('=', start);
			String param = "";
			if (equals != -1) {
				param = url.substring(start + 1, equals);
			}
			else {
				param = url.substring(start + 1);
			}
			
			// read parameter value
			String value = "";
			if (equals != -1) {
				start = url.indexOf('&', equals);
				if (start != -1) {
					value = url.substring(equals + 1, start);
				}
				else {
					value = url.substring(equals + 1);
				}
			}
			
			params.put(URLDecoder.decode(param, "UTF-8"), 
				URLDecoder.decode(value, "UTF-8"));
		}
		
		return params;
	}

	/**
	 * Returns the url without query parameters
	 * @param url         Url containing query parameters
	 * @return url        Url without query parameters
	 * @throws IOException 
	 */
	static public String removeQueryParams(String url) 
			throws IOException {
		int q = url.indexOf('?');
		if (q != -1) {
			return url.substring(0, q);
		}
		else {
			return url;
		}
	}
	
	/**
	 * Send a request
	 * @param method      HTTP method, for example "GET" or "POST"
	 * @param url         Url as string
	 * @param body        Request body as string
	 * @param headers     Optional map with headers
	 * @return response   Response as string
	 * @throws IOException 
	 */
	static public String fetch(String method, String url, String body,
			Map<String, String> headers) throws IOException {
		logger.info("http请求url为"+url);
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection)u.openConnection();
		conn.setConnectTimeout(180000);
		conn.setReadTimeout(180000);

		// method
		if (method != null) {
			conn.setRequestMethod(method);
		}

		// headers
		if (headers != null) {
			for(String key : headers.keySet()) {
				conn.addRequestProperty(key, headers.get(key));
			}
		}

		// body
		if (body != null) {
			conn.setDoOutput(true);
			OutputStream os = conn.getOutputStream();
			os.write(body.getBytes());
			os.flush();
			os.close();
		}
		
		// response
		InputStream is = conn.getInputStream();
		String response = streamToString(is);
		is.close();
		
		// handle redirects
		if (conn.getResponseCode() == 301) {
			String location = conn.getHeaderField("Location");
			return fetch(method, location, body, headers);
		}
		logger.info("http请求响应字符串为："+response);
		return response;
	}
	
	/**
	 * Read an input stream into a string
	 * @param in
	 * @return
	 * @throws IOException
	 */
	static public String streamToString(InputStream in) throws IOException {
		StringBuffer out = new StringBuffer();
		byte[] b = new byte[4096];
		for (int n; (n = in.read(b)) != -1;) {
			out.append(new String(b, 0, n));
		}
		return out.toString();
	}
	
	
	 public static String doPostJson(String url, String json) throws Exception {
			CloseableHttpClient httpClient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost(url);
			String responseMsg = null;
			
			try {
				HttpEntity re = new StringEntity(json, "utf-8");
				httppost.setHeader("Content-Type", "application/json;charset=utf-8");
				httppost.setHeader("Authorization", "UZHP9w/S3jbPXekBdrOXtw==");
				httppost.setHeader("name", "test");
				httppost.setHeader("sign", "123456");
//				httppost.setHeader("os", "Android");
//				httppost.setHeader("ip", "211.147.90.94");
				httppost.setEntity(re);
				HttpResponse response = httpClient.execute(httppost);
				HttpEntity entity = response.getEntity();
				responseMsg = EntityUtils.toString(entity, "utf-8");
			} catch (Exception e) {
				throw new Exception("webservice请求异常", e);
			} finally {
				httpClient.getConnectionManager().shutdown();
			}
			return responseMsg;
		}
	
	
	
	public static void main(String[] args) throws IOException {
		
		Map<String,Object> params = new HashMap<>();
		
//		params.put("appid", "ssoIpAddress");
//		params.put("loginid", "01484176");
//		
//		String res = HttpUtil.doPost("http://10.133.52.26/ssologin/getToken", params);
		
		params.put("token", "301296D089C62EF8AE6C89D80CDAC574D14A3F6E396DD940D1322C99B3665B441824BF6559A2218B630B3EDA7DB06DAA");
		String res = HttpOAUtil.doPost("http://10.133.52.26/ssologin/checkToken", params);
		System.out.println(res);
		
		
		
	}
	
	
	
}
