package com.waper.controller;

import com.waper.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * create by ${user} on 2019/3/18
 * *
 **/
@RestController
public class DemoContorller  {

    @Autowired
    DemoService demoService;

    private static Logger log = LoggerFactory.getLogger(DemoContorller.class);
    @GetMapping("/getDemoList")
    @ResponseBody
    public Object getDemoList(){
        int page = 1 ;
        int size = 10;
        log.info("hahhahhhhhhhhhhhhhhh");
        System.out.println(12);
        return demoService.selectDemo(page,size);
    }
    @RequestMapping(value = "/test/{name}/{age}",method = RequestMethod.GET)
    public String getPathVeriable(ModelMap map, @PathVariable String name, @PathVariable String age){
        map.put("name",name);
        map.put("age",age);
        return "index";
    }
    /**
     * @Title
     * @Description:    文件上传
     * @Author:         wangpeng
     * @CreateDate:     2019/3/20 15:33
     */
    @RequestMapping("/upload")
    public String updateLoad(@RequestParam("file")MultipartFile file){
        if(file.isEmpty()){
            return "文件不能为空";
        }
        String fileName = file.getOriginalFilename();
        String path ="D:/photo";
        File f  = new File(path,fileName);
        try {
            file.transferTo(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传成功";
    }

}
