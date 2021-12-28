package com.example.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.captcha.ICaptcha;
import com.example.service.CaptchaService;
import org.springframework.stereotype.Service;

import java.io.OutputStream;

/**
 * @ClassName CaptchaServiceImpl
 * @Description TODO
 * @Author wangpeng
 * @Date 2021/12/24 18:02
 */
@Service
public class CaptchaServiceImpl  implements CaptchaService ,ICaptcha{
    public Object createCaptcha() {

        return null;
    }

    public void createCode() {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(50, 60);
    }

    public String getCode() {
        return null;
    }

    public boolean verify(String s) {
        return false;
    }

    public void write(OutputStream outputStream) {

    }

    public static void main(String[] args) {
        CircleCaptcha circleCaptcha = CaptchaUtil.createCircleCaptcha(50, 60);
        System.out.println(circleCaptcha.getCode());
    }
}
