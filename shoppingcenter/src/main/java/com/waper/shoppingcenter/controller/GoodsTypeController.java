package com.waper.shoppingcenter.controller;

import com.waper.shoppingcenter.common.UUIDUtil;
import com.waper.shoppingcenter.dao.GoodsTypeMapper;
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
    GoodsTypeMapper goodsTypeMapper;

//    @RequestMapping("shop/goodsType/getGoodsTypeList")
//    public Object getGoodsTypeList(Integer pageNum,Integer pageSize){
//        Pageable pageable = PageRequest.of(1-1,10);
//        return  new BaseResponse(true, goodsTypeMapper.findAll(pageable));
//    }
//
//
//    @RequestMapping("shop/goodsType/goodsTypeList")
//    public Object goodsTypeList(){
//       return  goodsTypeMapper.findAll();
//    }
//    @RequestMapping("shop/goodsType/deleteGoodTypeById")
//    @Transactional
//    public Object deleteGoodTypeById(GoodsType goodsType){
//        if(goodsType.getTypeId() == null ){
//            return new BaseResponse("商品分类id不能为空");
//        }
//        goodsType.setStatus(1);
//        goodsTypeMapper.delete(goodsType);
//        return new BaseResponse(true,"删除成功");
//    }
//    @RequestMapping("shop/goodsType/updateGoodsTypeById")
//    @Transactional
//    public Object updateGoodsTypeById(GoodsType goodsType){
//        if(goodsType.getTypeId() == null ){
//            return new BaseResponse("商品分类id不能为空");
//        }
//        goodsTypeMapper.save(goodsType);
//        return new BaseResponse(true,"修改成功");
//    }
//    @RequestMapping("shop/goodsType/insertOrUpdateGoodsTypeById")
//    @Transactional
//    public Object insertOrUpdateGoodsTypeById(GoodsType goodsType){
//        if(goodsType.getName() == null ){
//            return new BaseResponse("商品分类名称不能为空");
//        }
//        if(goodsType.getId() == null){
//
//            if(goodsType.getParentId() == null){
//                goodsType.setParentId(0);
//            }
//            goodsType.setId(UUIDUtil.getUUID());
//            goodsType.setStatus(0);
//            goodsType.setAddTime(new Date());
//            goodsType.setUpdateTime(new Date());
//            goodsType.setCreator("");
//            goodsType.setCreatorId("");
//            goodsTypeMapper.save(goodsType);
//            return new BaseResponse(true,"添加成功");
//        }else {
//            GoodsType type = goodsTypeMapper.findById(goodsType.getId()).get();
//            type.setName(goodsType.getName());
//            goodsTypeMapper.save(type);
//            return new BaseResponse(true,"修改成功");
//        }
//    }
}
