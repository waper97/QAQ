package com.waper.shoppingcenter.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName DownloadController
 * @Description TODO
 * @Author wangpeng
 * @Date 2020/4/13 10:16
 */
@RestController
@RequestMapping("shop/file")
public class FileController extends BaseController {


    /**
     *   设置下载根路径
     */
    private String filePath = "F:\\";

    @RequestMapping("uploadFile")
    public Object upload(@RequestParam(value = "file") MultipartFile file){

        try {
            if (file.isEmpty()){
                return new BaseResponse("文件不能为空！");
            }
            // 文件名称
            String fileName = file.getOriginalFilename();
            // 后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));

            String path = filePath + fileName;

            File f = new File(path);
            // 文件写入
            file.transferTo(f);
            return new BaseResponse("上传成功!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BaseResponse("上传失败!");

    }



    /**
     * home.jsp
     * @param model
     * @param name
     * @return
     */
    @GetMapping(value = "/test")
    public String  helloWorld(Model model, String name){
        model.addAttribute("name",name);
        return "home";
    }

    @GetMapping(value = "/login")
    public String  login(Model model, String name){
        model.addAttribute("fuck","fuck");
        return "login";
    }

    @GetMapping(value = "/fuck")
    public String  fuck(Model model, String name){
        model.addAttribute("fuck","fuck");
        return "fuck";
    }


}
