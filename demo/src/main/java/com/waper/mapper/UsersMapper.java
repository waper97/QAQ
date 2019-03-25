package com.waper.mapper;

import com.waper.model.Users;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UsersMapper {

    List<Users> getUserList(Map<String,Object> paramMap);


//    Map<String,Object> getUserInfo(@Param("username")String userName, @Param("password")String password);

    Users getUserInfo(@Param("username")String userName, @Param("password")String password);


    int deleteByPrimaryKey(String id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);
}