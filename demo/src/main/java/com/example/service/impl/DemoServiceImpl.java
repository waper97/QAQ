package com.example.service.impl;

import com.example.mapper.DemoMapper;
import com.example.service.DemoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * create by ${user} on 2019/3/18
 * *
 **/
@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    DemoMapper demoMapper;

    @Override
    public PageInfo<Map<String, Object>> selectDemo(int pageSize, int pageNum) {
        List<Map<String,Object>> map = demoMapper.selectDemo();
        PageInfo<Map<String,Object>> page = new PageInfo<>(map);
        return page;
    }
}
