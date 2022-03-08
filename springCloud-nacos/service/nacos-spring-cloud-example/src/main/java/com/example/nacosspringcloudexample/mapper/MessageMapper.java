package com.example.nacosspringcloudexample.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.nacosspringexampleapi.entity.Student;

/**
 * @ClassName MessageMapper
 * @Description TODO
 * @Author wangpeng
 * @Date 2022/3/5 13:38
 */
public interface MessageMapper extends BaseMapper<Student> {
    Student selectStudent ();
}
