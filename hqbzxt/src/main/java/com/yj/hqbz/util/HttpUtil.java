package com.yj.hqbz.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;



/**
 * @Title: HttpUtil.java
 * @Package com.xjt.util.tools
 * @Description: TODO
 * @author xx
 * @date 2017-8-7
 */
public class HttpUtil {
	
	public static String sendHttpRequest(String httpUrl,String params) {	
		String res = null;		
		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connet = (HttpURLConnection) url.openConnection();
			connet.setDoOutput(true); 
			connet.setDoInput(true); 
			connet.setUseCaches(false);
			
			connet.setRequestMethod("POST");
			
			connet.setConnectTimeout(1000);
			connet.setReadTimeout(30000);			
			OutputStream outputStream = connet.getOutputStream();	
			outputStream.write(params.getBytes("UTF-8"));
			
			outputStream.flush();
			outputStream.close();
			
			if (connet.getResponseCode() != 200) {
				res = null;
			} else {
				// 将返回的值存入到String中
				BufferedReader brd = new BufferedReader(new InputStreamReader(
						connet.getInputStream(),"UTF-8"));
				res = brd.readLine();
			}
			
			connet.disconnect();
		} catch (Exception e) {
			System.out.println("发送HTTP请求失败："+e.getMessage());
		}
		return res;
	}
	

}
