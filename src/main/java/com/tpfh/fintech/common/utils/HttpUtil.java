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
import java.util.Map.Entry;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class HttpUtil {
      public static String sendPost(String url,String param){
          OutputStreamWriter out =null;
          BufferedReader reader = null;
          String response = "";

          //创建连接
          try {
              URL httpUrl = null; //HTTP URL类 用这个类来创建连接
              //创建URL
              httpUrl = new URL(url);
              //建立连接
              HttpURLConnection conn = (HttpURLConnection) httpUrl.openConnection();
              conn.setRequestMethod("POST");
              conn.setRequestProperty("Content-Type", "application/json");
              conn.setRequestProperty("connection", "keep-alive");
              conn.setUseCaches(false);//设置不要缓存
              conn.setInstanceFollowRedirects(true);
              conn.setDoOutput(true);
              conn.setDoInput(true);
              conn.connect();
              //POST请求
              out = new OutputStreamWriter(
                      conn.getOutputStream());
              out.write(param);
              out.flush();
              //读取响应
              reader = new BufferedReader(new InputStreamReader(
                      conn.getInputStream()));
              String lines;
              while ((lines = reader.readLine()) != null) {
                  lines = new String(lines.getBytes(), "utf-8");
                  response+=lines;
              }
              reader.close();
              // 断开连接
              conn.disconnect();

          } catch (Exception e) {
              System.out.println("发送 POST 请求出现异常！"+e);
              e.printStackTrace();
          }
          //使用finally块来关闭输出流、输入流
          finally{
              try{
                  if(out!=null){
                      out.close();
                  }
                  if(reader!=null){
                      reader.close();
                  }
              }
              catch(IOException ex){
                  ex.printStackTrace();
              }
          }
          return response;
      }


    public static String sendPost(String url) throws Exception {
        String response = null;
//        String data = JSON.toJSON(map).toString();
        try {
            CloseableHttpClient httpclient = null;
            CloseableHttpResponse httpresponse = null;
            try {
                httpclient = HttpClients.createDefault();
                HttpPost httppost = new HttpPost(url);
//                StringEntity stringentity = new StringEntity(data,
//                        ContentType.create("text/xml", "UTF-8"));
//                httppost.setEntity(stringentity);
                httpresponse = httpclient.execute(httppost);
                response = EntityUtils
                        .toString(httpresponse.getEntity());

            } finally {
                if (httpclient != null) {
                    httpclient.close();
                }
                if (httpresponse != null) {
                    httpresponse.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
    @Async
    public void sendGet(String url,Map<String,Object> paramsMap){
    	// 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
			// 定义请求的参数
			URIBuilder uriBuilder = new URIBuilder(url);
			for (Entry<String, Object> params:paramsMap.entrySet()) {
				uriBuilder.setParameter(params.getKey(), String.valueOf(params.getValue()));
			}
			URI uri = uriBuilder.build();
			// 创建http GET请求
			HttpGet httpGet = new HttpGet(uri);
			//response 对象
			CloseableHttpResponse response = null;
			try {
			    // 执行http get请求
			    response = httpclient.execute(httpGet);
			    // 判断返回状态是否为200
			    if (response.getStatusLine().getStatusCode() == 200) {
			        //String content = EntityUtils.toString(response.getEntity(), "UTF-8");
			    	System.out.println("请求成功");
			    }
			} finally {
			    if (response != null) {
			        response.close();
			    }
			    httpclient.close();
			}
		} catch (ParseException | URISyntaxException | IOException e) {
			e.printStackTrace();
		}
    }
    
}