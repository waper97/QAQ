package com.yj.common.tools;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

public class TokenUtil {
	/**私钥*/
	private final static String TOKEN_PRIVATE_KEY ="YJ_CLOUD_EDU_20181231";

	//验证Token
	public static void verifyToken(String token) throws Exception{  
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_PRIVATE_KEY)).build();   
        verifier.verify(token); 
    } 
	
	//获取访问Token
	public static String createAccsessToken(String userid,String ip,long sessionTime){  
		try {
			long time=System.currentTimeMillis();
			Map<String, Object> map = new HashMap<String, Object>();  
			map.put("alg", "HS256");//声明算法
			map.put("typ", "JWT");  //声明类型
			String token;
			token = JWT.create()  
					.withHeader(map)//header
					.withClaim("signer", "signer")//签发者
					        .withClaim("iat", new Date(time-sessionTime))//签发时间
					        .withClaim("exp", new Date(time+86400000L))//过期时间  
					        .withClaim("userid", userid) 
					        .withClaim("scope", "{*}")
					        .withClaim("ip", ip)
					        .sign(Algorithm.HMAC256(TOKEN_PRIVATE_KEY));
					return token;
				} catch (Exception e) {
					return null;
				} 
		}
	
	
	//获取int类自定义参数值
	public static Integer getClaimIntValue(String token,String paramKey){
		try{
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_PRIVATE_KEY)).build();   
		    DecodedJWT jwt = verifier.verify(token);  
			Map<String, Claim> claims = jwt.getClaims();  
	        Claim claim = claims.get(paramKey);
	        return claim.asInt();  
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	//获取String类自定义参数值
	public static String getClaimStringValue(String token,String paramKey){
		try{
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TOKEN_PRIVATE_KEY)).build();   
			DecodedJWT jwt = verifier.verify(token);  
			Map<String, Claim> claims = jwt.getClaims();  
			Claim claim = claims.get(paramKey);
			return claim.asString();  
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
}
