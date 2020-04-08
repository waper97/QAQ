package com.waper.shoppingcenter.dao.user;

import com.waper.shoppingcenter.model.User;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    /***
     * 获取用户列表
     * @param paramMap
     * @return
     */
    List<User> listUser(Map<String,Object> paramMap);

    User getLogin(@Param("userName")String userName, @Param("password") String password);

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    /**
     * 根据用户名查询用户是否存在
     * @param userName
     * @return
     */
    User getUserByUserName(String userName);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}