package com.yj.hqbz.mapper.user;

import java.util.List;

import com.yj.hqbz.model.user.UserAddress;

public interface UserAddressMapper {
    //根据ID获取地址信息
    UserAddress getAddressById(Integer id);
    //根据用户ID获取地址列表
    List<UserAddress> getAddressByUserId(String userid);
    //删除地址
    void deleteAddress(Integer id);
    //添加地址
    void insertAddress(UserAddress address);
    //更新地址
    void updateAddress(UserAddress address);
    //清空默认地址
    void clearALLCommonAddress(String userid);
    //设置默认地址
    void setCommonAddress(Integer id);
}