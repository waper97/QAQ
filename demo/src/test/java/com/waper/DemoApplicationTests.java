package com.waper;

import com.waper.service.DemoService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Autowired
    DemoService demoService;
    @Test
    public void contextLoads() {
    }

    @Test
    public void test(){
        PageInfo<Map<String,Object>> map = demoService.selectDemo(1,10);
        System.out.println(map);
    }
}
