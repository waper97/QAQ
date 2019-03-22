package com.waper.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * create by ${user} on 2019/3/21
 * *
 **/
public class TokenUtil {
    /**
     * 过期时间
     */
    private static  final  long EXPIRE_TIME = 15*60*1000;
    /**
     * 私钥
     */
     private static final String TOKEN_PRIVMARY_KEY ="TT_QWERDF_20190321";
    /**
     * 验证token
     * @param token
     */
    public static  boolean   verifyToken(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(token)).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 生成token
     * @param ip
     * @return
     */
    public static  String signature(String ip,String userId,long sessionTime,String username){
        try {
    //      过期时间
                Date date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
    //      设置算法
                Algorithm algorithm = Algorithm.HMAC256(TOKEN_PRIVMARY_KEY);
    //      设置头部信息
                Map<String,Object> map = new HashMap<>(2);
                map.put("type","JWT");
                map.put("alg","HMAC256");
                return JWT.create()
                        .withHeader(map)
                        .withAudience("userId",userId)
                        .withClaim("username",username)
                        .withExpiresAt(date)
                        .sign(algorithm);

            }catch (Exception e){
              e.printStackTrace();
              return null;
            }
    }
}
