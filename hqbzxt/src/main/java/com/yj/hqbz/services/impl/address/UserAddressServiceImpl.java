package com.yj.hqbz.services.impl.address;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yj.hqbz.mapper.area.AreaMapper;
import com.yj.hqbz.mapper.user.UserAddressMapper;
import com.yj.hqbz.model.area.Area;
import com.yj.hqbz.model.user.UserAddress;
import com.yj.hqbz.services.address.UserAddressService;

@Service
public class UserAddressServiceImpl implements UserAddressService {

    @Resource
    UserAddressMapper addrMapper;
    @Resource
    AreaMapper areaMapper;
    
    
    public PageInfo<UserAddress> getAddressListByUser(String userid,int page,int rows) {      
        PageHelper.startPage(page,rows);          
        List<UserAddress> addrList = addrMapper.getAddressByUserId(userid);
        PageInfo<UserAddress> info=new PageInfo<UserAddress>(addrList);
        return info;
    }

    
    public UserAddress getAddrInfoById(Integer id) {
        return addrMapper.getAddressById(id);
    }

    
    @Transactional
    public void deleteAddress(String ids) {
        String[] array = ids.split(",");
        for(String id:array){
            addrMapper.deleteAddress(Integer.parseInt(id));       
        }
    }

    
    @Transactional
    public void updateAddr(UserAddress addr) {
        addrMapper.updateAddress(addr);       
    }

    
    @Transactional
    public void createAddr(UserAddress addr) {
        addrMapper.insertAddress(addr);        
    }

    
    public String getAreaNameByAreaId(Integer areaid) {
        Area threeArea = areaMapper.selectByPrimaryKey(areaid);
        String threeName = threeArea.getName();
        
        Area secondArea = areaMapper.selectByPrimaryKey(threeArea.getParentid());
        String secondName = secondArea.getName();
        
        Area firstArea = areaMapper.selectByPrimaryKey(secondArea.getParentid());
        String firstName = firstArea.getName();
        
        String areaName = firstName +" "+ secondName +" "+ threeName;
        
        return areaName;
    }

    
    @Transactional
    public void setCommonAddress(String userid, Integer id) {
        addrMapper.clearALLCommonAddress(userid);
        addrMapper.setCommonAddress(id);
        
    }

}