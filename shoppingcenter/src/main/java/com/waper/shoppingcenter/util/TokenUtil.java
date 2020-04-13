package com.waper.shoppingcenter.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token工具类
 * @ClassName TokenUtil
 * @Description TODO
 * @Author wangpeng
 * @Date 2020/4/10 11:00
 */
public class TokenUtil {
    /**
     * 私钥
     */
    private static final String TOKEN_PRIVATE_KEY = "QWERDF_WAPER_20200410";

    /**
     * 验证token
     * @param token
     */
    public static void verifyToken(String token){
        // 验证token
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_PRIVATE_KEY)).build();
        verifier.verify(token);
    }

    /**
     * 创建token
     * @param userid 用户id
     * @param ip ip地址
     * @param sessionTime 会话时间
     * @return
     */
    public static String createToken(String userid, String ip, long sessionTime){
        // 获取当前系统时间
        long time = System.currentTimeMillis();
        Map<String,Object> map = new HashMap<>(16);
        String token;
        token = JWT.create()
                .withHeader(map)
                // 签名
                .withClaim("signer","signer")
                // 签发时间
                .withClaim("iat",new Date(time-sessionTime))
                // 过期时间
                .withClaim("exp",new Date(time+86400000L))
                .withClaim("userid",userid)
                .withClaim("scope","{*}")
                .withClaim("ip",ip)
                .sign(Algorithm.HMAC256(TOKEN_PRIVATE_KEY));
        return token;
    }
}
