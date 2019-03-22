package com.example.mapper;

import com.example.model.User;
import com.example.model.UserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {



    List<User> getUserList(Map<String,Object> paramMap);


//    Map<String,Object> getUserInfo(@Param("username")String userName, @Param("password")String password);

    User getUserInfo(@Param("username")String userName, @Param("password")String password);



    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}