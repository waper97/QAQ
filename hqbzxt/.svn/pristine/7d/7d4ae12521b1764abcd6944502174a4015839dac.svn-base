package com.yj.hqbz.controller.address;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.yj.common.controller.BaseController;
import com.yj.common.model.BaseRes;
import com.yj.common.model.DataGridModel;
import com.yj.hqbz.model.user.UserAddress;
import com.yj.hqbz.model.user.UserInfo;
import com.yj.hqbz.services.address.UserAddressService;
import com.yj.hqbz.util.CommUtil;

@RestController
public class UserAddressController extends BaseController{

    @Resource
    UserAddressService addrService;
    
    @GetMapping("/user/address/getAddressList")
    public Object getAddressList(DataGridModel grid){
        UserInfo user = getTokenUser();
        PageInfo<UserAddress> pageList = addrService.getAddressListByUser(user.getUserid(), grid.getPage(), grid.getRows());
        for(UserAddress addr:pageList.getList()){
            addr.setAreaName(addrService.getAreaNameByAreaId(addr.getAreaid()));
        }
        return new BaseRes("获取用户地址列表成功!",pageList.getTotal(),pageList.getPages(),pageList.getList());
    }
    
    @GetMapping("/user/address/getAddressInfoById")
    public Object getAddressById(String id){
        if(StringUtils.isBlank(id)){
            return fail("参数错误！");
        }
        UserAddress addr = addrService.getAddrInfoById(CommUtil.null2Int(id));
        addr.setAreaName(addrService.getAreaNameByAreaId(addr.getAreaid()));
        return success(addr);       
    }
    
    @PostMapping("/user/address/delete")
    public Object deleteAddressById(String ids){
        if(StringUtils.isBlank(ids)){
            return fail("参数错误！");
        }
        addrService.deleteAddress(ids);
        return success("删除地址成功！");
    }
    
    @PostMapping("/user/address/saveAddr")
    public Object saveAddress(UserAddress addr){
        UserInfo user = getTokenUser();
        if(addr.getId()==null){
            addr.setUserid(user.getUserid());
            addr.setStatus(0);
            addrService.createAddr(addr);
        }else{
            addrService.updateAddr(addr);
        }
        return success("保存地址成功！");
    }
    @PostMapping("/user/address/setCommon")
    public Object setCommonAddress(String id){
        UserInfo user = getTokenUser();
        if(StringUtils.isBlank(id)){
            return fail("参数错误");
        }
        addrService.setCommonAddress(user.getUserid(), CommUtil.null2Int(id));
        return success("设置默认地址成功！");
    }
}
