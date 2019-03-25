package com.yj.hqbz.services.address;


import com.github.pagehelper.PageInfo;
import com.yj.hqbz.model.user.UserAddress;

public interface UserAddressService {

    //根据用户ID获取地址列表
    PageInfo<UserAddress> getAddressListByUser(String userid,int page,int rows);
    //根据ID获取地址详情
    UserAddress getAddrInfoById(Integer id);
    //根据ID删除地址
    void deleteAddress(String ids);
    //更新地址
    void updateAddr(UserAddress addr);
    //创建地址
    void createAddr(UserAddress addr);
    //获取区域名称
    String getAreaNameByAreaId(Integer areaid);
    //修改默认地址
    void setCommonAddress(String userid,Integer id);
}
