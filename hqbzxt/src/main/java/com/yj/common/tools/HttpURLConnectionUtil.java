package com.yj.common.tools;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.yj.hqbz.util.StringUtil;

public class HttpURLConnectionUtil {

	public  void doPost(String url){  
	   try {
			URL dataUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) dataUrl.openConnection();
			int code = con.getResponseCode();
			con.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }  
	
	public static String doPostRetuen(String url, String params){
		String str=null;
		try {
			URL dataUrl = new URL(url);
			HttpURLConnection con = (HttpURLConnection) dataUrl.openConnection();
			con.setRequestMethod("GET");
			con.setUseCaches(false);//不使用缓存
//			con.setRequestProperty( "Content-Type", "application/json" );  
//			con.setRequestProperty( "Content-Type", "application/x-java-serialized-object;charset=UTF-8" );  
			con.setDoOutput(true);//输出参数
			con.setDoInput(true);//输入返回值
			con.setAllowUserInteraction(false);
			con.setConnectTimeout(30000);//设置连接主机超时
			con.setReadTimeout(30000);//设置从主机读取数据超时
			
            // 发送请求参数
//            if(!StringUtils.isEmpty(params)){
//            	// 获取URLConnection对象对应的输出流
//            	PrintWriter printWriter = new PrintWriter(con.getOutputStream());
//                printWriter.write(params);//post的参数 usernam=xx&pwd=yy
//                // flush输出流的缓冲
//                printWriter.flush(); 
//                // 关闭
//                printWriter.close();
//            }
            // 发送请求参数
            if(StringUtil.isNotBlank(params)){
            	 OutputStream out = con.getOutputStream();
                 out.write(params.getBytes("UTF-8"));
                 //流用完记得关  
                 out.flush();  
                 out.close();  

            }
            
            
			int code = con.getResponseCode();
			if(200 == code){
	            //得到输入流
				InputStream is =con.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
	            byte[] buffer = new byte[1024];
	            int len = 0;
	            while(-1 != (len = is.read(buffer))){
	                baos.write(buffer,0,len);
	                baos.flush();
	            }
	            str=baos.toString("UTF-8");
	            baos.close();
	            is.close();
	        }
			con.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
}
