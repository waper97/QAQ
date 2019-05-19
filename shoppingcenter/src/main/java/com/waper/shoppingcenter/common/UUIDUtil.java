package com.waper.shoppingcenter.common;

import java.util.UUID;

/**
 * 生成UUID
 */
public class UUIDUtil {

    public static String getUUID(){
        // replace用于去掉 "-"符号
        String uuid = UUID.randomUUID().toString().replace("-","");
        return uuid;
    }
}
