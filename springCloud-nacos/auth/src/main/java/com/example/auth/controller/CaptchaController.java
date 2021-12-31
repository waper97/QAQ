package com.example.auth.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.wf.captcha.ChineseGifCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName CaptchaController
 * @Description TODO
 * @Author wangpeng
 * @Date 2021/12/30 15:15
 */
@RestController
@RequestMapping("/captcha")
@Api(value = "验证码控制器")
public class CaptchaController {
    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 生成中文验证码
     * @param response
     * @return
     * @throws IOException
     */
    @GetMapping("getChineseCaptcha")
    @ApiOperation(value = "获取中文验证码")
    public void getChineseCaptcha (HttpServletResponse response) throws IOException {
        ServletOutputStream outputStream = response.getOutputStream();
        ChineseGifCaptcha captcha = new ChineseGifCaptcha(120,40);
        // 验证码随机字符长度
        captcha.setLen(2);
        String result = captcha.text();
        System.out.println(result);
        captcha.out(outputStream);
    }

    @GetMapping("getNumberCaptcha")
    @ApiOperation(value = "获取数字验证码")
    public void getNumberCaptcha(HttpServletResponse response) throws IOException {

        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(120, 40);
        String code = lineCaptcha.getCode();
        lineCaptcha.write(response.getOutputStream());
        String olderCode  = (String) redisTemplate.opsForValue().get("captcha");

        if (olderCode != null) {
            redisTemplate.delete("captcha");
        }
//        redisTemplate.setKeySerializer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.opsForValue().set("captcha", code);
        System.out.println("code:"+code);
        System.out.println("redis cache code:"+redisTemplate.opsForValue().get("captcha"));
    }
}
