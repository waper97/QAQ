package com.waper.shoppingcenter.service;

import com.waper.shoppingcenter.model.User;
import org.springframework.data.domain.Page;

/**
 * create by  on 2019/6/3
 * *
 **/
public interface UserService {
    Page<User> getUseList(Integer pageNumber,Integer pageSize);
}
