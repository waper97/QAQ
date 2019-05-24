package com.waper.shoppingcenter.controller;

import com.waper.shoppingcenter.common.UUIDUtil;
import com.waper.shoppingcenter.dao.GoodsTypeDao;
import com.waper.shoppingcenter.model.GoodsType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Date;


/**
 * create by  on 2019/5/21
 * *
 **/
@RestController
public class GoodsTypeController {
    @Autowired
    GoodsTypeDao goodsTypeDao;

    @RequestMapping("shop/goodsType/getGoodsTypeList")
    public Object getGoodsTypeList(Integer pageNum,Integer pageSize){
        Pageable pageable = PageRequest.of(1-1,10);
        return  new BaseResponse(true,goodsTypeDao.findAll(pageable));
    }


    @RequestMapping("shop/goodsType/goodsTypeList")
    public Object goodsTypeList(){
       return  goodsTypeDao.findAll();
    }
    @RequestMapping("shop/goodsType/deleteGoodTypeById")
    @Transactional
    public Object deleteGoodTypeById(GoodsType goodsType){
        if(goodsType.getTypeId() == null ){
            return new BaseResponse("商品分类id不能为空");
        }
        goodsType.setStatus(1);
        goodsTypeDao.delete(goodsType);
        return new BaseResponse(true,"删除成功");
    }
    @RequestMapping("shop/goodsType/updateGoodsTypeById")
    @Transactional
    public Object updateGoodsTypeById(GoodsType goodsType){
        if(goodsType.getTypeId() == null ){
            return new BaseResponse("商品分类id不能为空");
        }
        goodsTypeDao.save(goodsType);
        return new BaseResponse(true,"修改成功");
    }
    @RequestMapping("shop/goodsType/insertOrUpdateGoodsTypeById")
    @Transactional
    public Object insertOrUpdateGoodsTypeById(GoodsType goodsType){
        if(goodsType.getName() == null ){
            return new BaseResponse("商品分类名称不能为空");
        }
        if(goodsType.getId() == null){

            if(goodsType.getParentId() == null){
                goodsType.setParentId(0);
            }
            goodsType.setId(UUIDUtil.getUUID());
            goodsType.setStatus(0);
            goodsType.setAddTime(new Date());
            goodsType.setCreator("");
            goodsType.setCreatorId("");
            goodsTypeDao.save(goodsType);
            return new BaseResponse(true,"添加成功");
        }else {
            GoodsType type = goodsTypeDao.findById(goodsType.getId()).get();
            type.setName(goodsType.getName());
            goodsTypeDao.save(type);
            return new BaseResponse(true,"修改成功");
        }
    }
}
