package com.waper.shoppingcenter.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.waper.shoppingcenter.model.User;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName TokenService
 * @Description TODO
 * @Author wangpeng
 * @Date 2020/4/9 16:45
 */
@Service
public class TokenService {
    public String getToken(User user) {
        Date start = new Date();
        // 有效时间一小时
        long currentTime = System.currentTimeMillis()+60*60*1000;
        Date end = new Date(currentTime);
        String token = "";
        token = JWT.create().withAudience(user.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;

    }


}

