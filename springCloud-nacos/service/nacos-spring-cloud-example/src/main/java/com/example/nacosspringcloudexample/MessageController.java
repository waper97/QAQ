package com.example.nacosspringcloudexample;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.contant.RabbitMqContant;
import com.example.nacosspringcloudexample.service.MessageService;
import com.example.nacosspringexampleapi.entity.Student;
import io.seata.spring.annotation.GlobalTransactional;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @ClassName MessageController
 * @Description TODO
 * @Author wangpeng
 * @Date 2021/12/29 18:09
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Value("${spring.redis.port}")
    private String redisPort;

    @GetMapping("getMessage")
    public Object returnMessage() {
        return "this is example message";
    }

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired

    StringRedisTemplate stringRedisTemplate;

    @Autowired
    MessageService messageService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("redisPutvalueTest")
//    @GlobalTransactional
    public Object redisPutvalueTest() {
       String redisValue = (String) redisTemplate.opsForValue().get("student");
        List<Student> studentList =  JSONObject.parseArray(redisValue,Student.class);
        if (studentList == null) {
            List<Student> list1 = messageService.list();
            redisTemplate.opsForValue().set("student",  JSON.toJSON(list1).toString(),1,TimeUnit.MINUTES);
        }else {

            return studentList;
        }
        return null;
    }

    public void simpleQueue() {
        redisTemplate.convertAndSend(RabbitMqContant.SIMPLE_QUEUE,"简单的消息!");
    }



    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(1,1,1,TimeUnit.DAYS, new ArrayBlockingQueue<Runnable>(1000));
        ThreadPoolExecutor threadPoolExecutor2 = new ThreadPoolExecutor(1,10,1,TimeUnit.DAYS, new ArrayBlockingQueue<Runnable>(1000));
        ThreadPoolExecutor threadPoolExecutor3 = new ThreadPoolExecutor(1,20,1,TimeUnit.DAYS, new ArrayBlockingQueue<Runnable>(1000));


        for (int i = 0 ; i <= 100; i++) {
            threadPoolExecutor2.execute(new MyTask(i));
        }
    }
}
