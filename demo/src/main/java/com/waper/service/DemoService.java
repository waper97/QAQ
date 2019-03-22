package com.waper.service;


import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * create by ${user} on 2019/3/18
 * *
 **/
public interface DemoService {
    PageInfo<Map<String,Object>> selectDemo(int pageSize, int pageNum);

}
