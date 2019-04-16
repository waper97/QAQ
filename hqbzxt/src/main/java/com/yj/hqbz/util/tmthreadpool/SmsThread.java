package com.yj.hqbz.util.tmthreadpool;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yj.hqbz.util.HttpUtil;
import com.yj.hqbz.util.StringUtil;
import com.yj.hqbz.web.Global;

public class SmsThread extends Thread {

    private String url;
    private String model;
    private String phone;
    private String[] args;
    
    private static final Logger logger = Logger.getLogger(SmsThread.class);
    
    public SmsThread(String url,String phone,String model,String[] args){
        this.url = url;
        this.phone = phone;
        this.model = model;
        this.args = args;
    }
    
    public void run() {
        if (Global.getSystemParamIntValueByKey("is_debug") == 1) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("smsType", model);
            params.put("params", args);
            params.put("phone", phone);
            String encryptString = "signature="
                    + StringUtil.encryptAES(JSON.toJSONString(params));
            String str = HttpUtil.sendHttpRequest(url + "/api/sms/sendSms",
                    encryptString);
            if (str != null) {
                JSONObject obj = (JSONObject) JSONObject.parse(str);
                if (obj.getBoolean("success")) {
                    return;
                } else {
                    logger.error("短信服务器返回：" + str);
                }
            } else {
                logger.error("发送短信发生错误！");
            }
        }
    }

}