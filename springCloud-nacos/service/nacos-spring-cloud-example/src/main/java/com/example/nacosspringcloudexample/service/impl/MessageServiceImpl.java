package com.example.nacosspringcloudexample.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.nacosspringcloudexample.mapper.MessageMapper;
import com.example.nacosspringcloudexample.service.MessageService;
import com.example.nacosspringexampleapi.entity.Student;
import org.springframework.stereotype.Service;

/**
 * @ClassName MessageServiceImpl
 * @Description TODO
 * @Author wangpeng
 * @Date 2022/3/5 13:31
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Student> implements MessageService {
}
