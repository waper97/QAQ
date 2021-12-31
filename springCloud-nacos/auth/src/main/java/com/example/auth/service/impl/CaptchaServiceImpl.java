package com.example.auth.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.ICaptcha;
import cn.hutool.captcha.LineCaptcha;
import com.example.auth.service.CaptchaService;
import org.springframework.stereotype.Service;

import java.io.*;

/**
 * @ClassName CaptchaServiceImpl
 * @Description TODO
 * @Author wangpeng
 * @Date 2021/12/24 18:02
 */
@Service
public class CaptchaServiceImpl  implements CaptchaService ,ICaptcha{


    public static void main(String[] args) {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(50, 60);
        System.out.println(circleCaptcha.getCode());
    }

    @Override
    public void createCode(){
        //定义图形验证码的长和宽
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        try {
            OutputStream outputStream = new ByteArrayOutputStream();
            String code = lineCaptcha.getCode();
            System.out.println("当前code:"+code);
            lineCaptcha.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String getCode() {
        return null;
    }

    @Override
    public boolean verify(String s) {
        return false;
    }

    @Override
    public void write(OutputStream outputStream) {

    }

    @Override
    public Object createCaptcha() {
        return null;
    }
}
