package com.waper.shoppingcenter.dao;

import com.waper.shoppingcenter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao  extends JpaRepository<User,String> {
    // 用户登陆
    User getUserByUsernameAndPassword(String username , String password);
    // 添加用户
    User save(User user);
}
