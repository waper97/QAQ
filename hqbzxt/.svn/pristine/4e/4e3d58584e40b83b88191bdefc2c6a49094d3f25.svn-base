package com.yj.common.tools;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yj.common.model.YJOrgDB;
import com.yj.hqbz.util.StaticUtils;
import com.yj.hqbz.util.StringUtil;
import com.yj.hqbz.web.Global;

public class YJOrgDBUtil {
	/**
	 * 请求用户库获取数据
	 */
	public static YJOrgDB getBaseUserDB(String paramJson,String url){
		//加密参数
		String aec = StringUtil.encryptAES(paramJson);
		//生成参数
		String params="signature="+aec;
		//获取请求url
		String httpUrl = Global.getSystemParamValueByKey(StaticUtils.USERDB_URL_CODE)+url;
		//获取返回值
		String returnStr = HttpURLConnectionUtil.doPostRetuen(httpUrl, params);
		JSONObject json=(JSONObject) JSON.parse(returnStr);
		return JSONObject.toJavaObject(json, YJOrgDB.class);
	}
}
