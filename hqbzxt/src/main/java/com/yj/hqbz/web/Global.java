package com.yj.hqbz.web;

import java.util.List;
import java.util.Map;

import com.yj.hqbz.model.system.Resources;



/**  
 * @Title: Global.java
 * @Package com.xjt.web
 * @Description: TODO
 * @author xx
 * @date 2016-4-20
 */
public class Global {
	private static Map<String,Object> systemInfo;
	
	//资源集合
	private static List<Resources>	resourceList;
	

	
	public static String getSystemParamValueByKey(String key){
		if(systemInfo==null){
			return "";
		}else {
			Object obj = systemInfo.get(key);
			if(obj == null){
				return "";
			}else {
				return obj.toString();
			}
		}
	}
	
	public static int getSystemParamIntValueByKey(String key){
		if(systemInfo==null){
			return 0;
		}else {
			Object obj = systemInfo.get(key);
			if(obj == null){
				return 0;
			}else {
				try {
					return Integer.parseInt(obj.toString());
				} catch (Exception e) {
					return 0;
				}
			}
		}
	}
	public static void setSystemInfo(Map<String,Object> systemInfo) {
		Global.systemInfo = systemInfo;
	}

	public static Map<String, Object> getSystemInfo() {
		return systemInfo;
	}

	public static List<Resources> getResourceList() {
		return resourceList;
	}

	public static void setResourceList(List<Resources> resourceList) {
		Global.resourceList = resourceList;
	}
	
}
