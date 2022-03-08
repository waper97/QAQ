package com.example.pazs.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.pazs.mapper.TeacherMapper;
import com.example.pazs.service.TeacherService;
import com.example.pazsapi.entity.Teacher;
import org.springframework.stereotype.Service;

/**
 * @ClassName TeacherServiceImpl
 * @Description TODO
 * @Author wangpeng
 * @Date 2022/3/8 22:51
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
}
