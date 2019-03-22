package com.example.util;


import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


/**
 * @Title
 * @Description:    自定义工具类
 * @Author:         wangpeng
 * @CreateDate:     2019/3/19 11:11
 */
public class CommUtil {

    public static String getDateFormart(Date date, String formartStr){
        SimpleDateFormat sfm = new SimpleDateFormat(formartStr);
            String d = sfm.format(date);
            return d;
    }

    /**
     * 返回32位uuid
     * @return
     */
    public static String getUUid(){
        return UUID.randomUUID().toString().replace("-","").trim();
    }
}
