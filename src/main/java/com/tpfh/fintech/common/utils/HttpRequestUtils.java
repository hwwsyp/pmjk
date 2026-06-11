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

/**
 * Http请求，激励项目，接口数据请求
 * @author lzj
 *
 */
public class HttpRequestUtils {
	private  Logger logger = LoggerFactory.getLogger(getClass());
	/**
	 * 获取接口数据
	 * @return
	 */
	public static String getData(String reqUrl){   //获取基础数据
		System.out.println("===============http请求正在被执行===================");
		HttpURLConnection connection = null;
		OutputStream os = null;
		int responseCode = 0;
		StringBuilder sb = new StringBuilder();

		//第一步：创建服务地址，不是WSDL地址  
		URL url = null;
		try {
			url = new URL(reqUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		//第二步：打开一个通向服务地址的连接  
		try {
			connection = (HttpURLConnection) url.openConnection();
			//第三步：设置参数  
			//3.1发送方式设置：POST必须大写  
			connection.setRequestMethod("POST");
			//3.2设置数据格式：content-type  
			connection.setRequestProperty("content-type", "text/xml;charset=utf-8");  
			//3.3设置输入输出，因为默认新创建的connection没有读写权限，  
			connection.setDoInput(true);  
			connection.setDoOutput(true);  
			os = connection.getOutputStream();  
			//第五步：接收服务端响应，打印  
			responseCode = connection.getResponseCode(); 

			String temp = null; 
			if(200 == responseCode){//表示服务端响应成功  
				InputStream is = connection.getInputStream();  
				InputStreamReader isr = new InputStreamReader(is);  
				BufferedReader br = new BufferedReader(isr);  
				while(null != (temp = br.readLine())){  
					sb.append(temp);  
				}  
				System.out.println(sb.toString());  
				is.close();  
				isr.close();  
				br.close();  
			}
			os.close(); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		System.out.println("===============http请求执行结束===================");
		return sb.toString();
	}
	/**
	 * 获取缴付记录
	 * @param createDate
	 * @param reqUrl
	 * @return
	 */
	public static String  getDataByParams(String createDate,String reqUrl){//获取基金缴付记录
		System.out.println("===============http请求基金缴付记录开始执行=================");
		// Post请求的url，与get不同的是不需要带参数
		URL postUrl = null;
		try {
			postUrl = new URL(reqUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 打开连接
		HttpURLConnection connection = null;
		try {
			connection = (HttpURLConnection) postUrl.openConnection();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}     
		// 设置是否向connection输出，因为这个是post请求，参数要放在
		// http正文内，因此需要设为true
		connection.setDoOutput(true);
		// Read from the connection. Default is true.
		connection.setDoInput(true);
		// 默认是 GET方式
		try {
			connection.setRequestMethod("POST");
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}      
		// Post 请求不能使用缓存
		connection.setUseCaches(false);  
		//设置本次连接是否自动重定向 
		connection.setInstanceFollowRedirects(true);      
		// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		// 意思是正文是urlencoded编码过的form参数
		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
		// 要注意的是connection.getOutputStream会隐含的进行connect。
		try {
			connection.connect();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataOutputStream out = null;
		try {
			out = new DataOutputStream(connection
					.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
		String content = null;
		try {
			content = "createDate=" + URLEncoder.encode(createDate, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
		try {
			out.writeBytes(content);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//流用完记得关
		try {
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//获取响应
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line = null;
		//		while ((line = reader.readLine()) != null){
		//			System.out.println("line---?"+line);
		//			
		//		}
		try {
			line = reader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("line---?"+line);

		try {
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//该干的都干完了,记得把连接断了
		connection.disconnect();
		System.out.println("===============http请求基金缴付记录执行结束=================");
		return line;
	}

}
