package com.waper.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

/**
 * @Title
 * @Description:    生成图片验证码
 * @Author:         wangpeng
 * @CreateDate:     2019/3/22 11:22
 */
@RestController
public class PictureController {
    HttpServletRequest request = null;
    @RequestMapping("/createPictrue")
    public void createPictrue(HttpServletRequest request, HttpServletResponse response){

        try {
            //响应头信息
            response.setHeader("Pragma", "No-Cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expries", 0);
            String abc ="0123456789";
            int length = abc.length();
            //定义图片长宽
            int width = 100;
            int height = 40;
            //生成随机数类
            Random random = new Random();
            //定义图像buffer
            BufferedImage image = new BufferedImage(width,height,
                    BufferedImage.TYPE_INT_RGB);

            OutputStream os = response.getOutputStream();
            //设置图片上下文
            Graphics g = image.getGraphics();
            //设置背景颜色
            g.setColor(Color.WHITE);
            g.fillRect(0,0,width,height);

            g.setFont(new Font("黑体",Font.BOLD,30));
            //生成干扰线
            for(int i = 0;i< 8;i++ ){
            //干扰线颜色
                g.setColor(new Color(
                         random.nextInt(256),random.nextInt(256)
                        ,random.nextInt(256)));
            //画线
                g.drawLine(
                        random.nextInt(150),random.nextInt(30),
                        random.nextInt(150),random.nextInt(30));
            }
            //定义变量保存生成的验证码
            String code = "";
            for(int i = 0; i < 4; i++){
                int no = random.nextInt(length);
                String rand = abc.substring(no,no+1);
                code+= rand;
                g.setColor(new Color(random.nextInt(150),random.nextInt(200)
                        ,random.nextInt(250)));
                g.drawString(rand,(i * 20)+10,30+random.nextInt(5));
            }
            System.out.println(code);
            //验证码存入session
            request.getSession().setAttribute("code",code);
            //图像生效
            g.dispose();
            //输出到页面
            ImageIO.write(image,"JPEG",os);
            os.flush();
            os.close();
            os = null;
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/checkCode")
    public boolean checkCode( HttpSession session,String param){
        if(param == null){
            throw new NullPointerException("验证码不能为空");
        }
        String code = session.getAttribute("code").toString();
        if(param.equals(code))
            return true;
        return false;
    }
}
