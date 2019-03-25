package com.yj.hqbz.util;

import java.math.BigDecimal;
import java.security.MessageDigest;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yj.hqbz.model.system.Resources;
import com.yj.hqbz.web.Global;

public class StringUtil {

	/**
	 * 判断字符串是否'为空'
	 * 
	 * @param str:字符串
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (str == null || "".equals(str.trim()) || "null".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断字符串是否'不为空'
	 * 
	 * @param str:字符串
	 * @return
	 */
	public static boolean isNotBlank(String str) {
		if (str != null && !"".equals(str.trim())&&!"null".equals(str.trim())) {
			return true;
		} else {
			return false;
		}
	}

	/***
	 * Object转换为String
	 * 
	 * @param obj
	 * @return
	 */
	public static String getStrByObj(Object obj) {
		if (obj != null) {
			return obj.toString();
		} else {
			return null;
		}
	}

	/**
	 * List转变为String
	 */
	public static String getStrByList(List list) {
		StringBuilder strBuilder = new StringBuilder();
		for (Object obj : list) {
			strBuilder.append(obj+",");
		}
		String str = strBuilder.toString();
		return str.substring(0,str.length()-1);
	}
	
	/***
	 * MD5加密
	 * 
	 * @param str:字符串
	 * @return
	 */
	public static String MD5(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("UTF8"));
			byte s[] = md.digest();
			String result = "";
			for (int i = 0; i < s.length; i++) {
				result = result + Integer.toHexString(0xff & s[i] | 0xffffff00).substring(6);
			}
			return result.toUpperCase();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取短信数字验证码
	 * 
	 * @return
	 */
	public static String getSmsCode() {
		int number = (int) ((Math.random() * 9 + 1) * 100000);// 随机验证码
		String randomNumber = String.valueOf(number);
		return randomNumber;
	}
	
	/**
	 * 获取随机数
	 * digit:位数，如1000表示4位随机数
	 */
	public static String getRandomNum(long digit) {
		int number = (int) ((Math.random() * 9 + 1) * digit);// 随机验证码
		String randomNumber = String.valueOf(number);
		return randomNumber;
	}
	
	/**
	 * 获取32位UUid
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "").trim();
	}
	
	public static boolean isInteger(String str) {
		if (isBlank(str)) {
			return false;
		}
		Pattern regex = Pattern.compile("\\d*");
		Matcher matcher = regex.matcher(str);
		boolean isMatched = matcher.matches();
		return isMatched;
	}
	
	
	public static String join(String[] arr,String jionStr){
		StringBuffer buff = new StringBuffer();
		for(String str:arr){
			buff.append(str).append(jionStr);
		}		
		return buff.toString();
	}
	
	/**
	 * 左填充
	 * @param str
	 * @param length
	 * @return
	 */
	public static String leftFillString(String str,int length,String fillChar,String header){
		int maxLen = length - header.length();	 //除去头部后的最大长度
		if(isBlank(str)){
			return "";
		}
		else{			
			if(str.length()>=length){
				return str.substring(0,length);
			}
			else{
				if(str.length()>maxLen){
					str = str.substring(str.length()-maxLen);
				}
				int len = length - str.length()-header.length();
				for(int i = 0 ;i<len;++i){
					str = fillChar+str;
				}
				return header+str;
			}
		}
	}
	
	/**
	 * 右填充
	 * @param str
	 * @param length
	 * @param fillChar
	 * @return
	 */
	public static String rightFillString(String str,int length,String fillChar){
		if(isBlank(str)){
			return "";
		}
		else{
			if(str.length()>=length){
				return str;
			}
			else{
				int len = length - str.length();
				for(int i = 0 ;i<len;++i){
					str += fillChar;
				}
				return str;
			}
		}
	}
	
	public static String getFileNameWithOutExt(String absoluteName){
		 String fileName = "";
		  int index = 0;
		  if(absoluteName.indexOf("\\")>0)
			  index = absoluteName.lastIndexOf("\\");
		  else
			  index = absoluteName.lastIndexOf("/");
		  fileName = absoluteName.substring(index+1);
		  if(fileName.indexOf(".")>0)
			  fileName = fileName.substring(0,fileName.indexOf("."));
		  
		  return fileName;
	}
	
	public static boolean checkInputIsNumber(String input){
		try{
			BigDecimal bg = new BigDecimal(input);
			return true;
		}
		catch(Exception ex){
			return false;
		}
	}
	
	/**
	 * 验证用户访问是否合法
	 * @param url
	 * @param userResources
	 * @return
	 */
	public static boolean checkVisitUrlIsValid(String url,List<Resources> userResources){
		for(Resources res:userResources){  //从用户权限表中进行判断
			if(url.endsWith(".html")){
				if(url.equals(res.getHtmlUrl())){
					return true;
				}
			}
			else if(isNotBlank(res.getApiUrl())){  //接口，通配符
				if(isMatching(url,res.getApiUrl())){
					return true;
				}
			}
		}
		
		//从系统资源表中判断
		List<Resources> globalResList = (List<Resources>)Global.getResourceList();
		for(Resources res : globalResList){
			if(url.endsWith(".html")){
				if(url.equals(res.getHtmlUrl())){
					return false;
				}
			}
			else if(isNotBlank(res.getApiUrl())){  //接口，通配符
				if(isMatching(url,res.getApiUrl())){
					return false;
				}
			}
		}
		return true;
	}
	
	public static boolean isMatching(String src,String des){
		String des1 = des.replace("*", "\\w*");
		des1 = des1.replace("?", "\\w{1}");
		Pattern p = Pattern.compile(des1);
		Matcher m = p.matcher(src);
		return m.matches();
	}
	
	public static String encryptAES(final String src){
		try{
			EncrypAES encryp = EncrypAES.getInstance();    	
			String outputStr = null;
			outputStr = HexTransfer.byteArr2HexStr(encryp.Encrytor(src));
			return outputStr;
		}catch(Exception ex){
			return null;
		}
	}
	
	public static String generateRandomStr(int length){
		String generateSource = "1234567890";
	    String rtnStr = "";
	    for (int i = 0; i < length; i++) {
	        //循环随机获得当次字符，并移走选出的字符
	        String nowStr = String.valueOf(generateSource.charAt((int) Math.floor(Math.random() * generateSource.length())));
	        rtnStr += nowStr;
	        generateSource = generateSource.replaceAll(nowStr, "");
	    }
	    return rtnStr.toUpperCase();
	}

	
	
}
