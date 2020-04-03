package com.waper.shoppingcenter.controller;

import com.github.pagehelper.PageInfo;
import com.waper.shoppingcenter.common.UUIDUtil;
import com.waper.shoppingcenter.model.GoodsType;
import com.waper.shoppingcenter.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * create by  on 2019/5/21
 * *
 **/
@RestController
public class GoodsTypeController {
    @Autowired
    GoodsTypeService goodsTypeService;

    /**
     * 获取商品类型列表
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("shop/goodsType/getGoodsTypeList")
    public Object getGoodsTypeList(Integer pageNum,Integer pageSize){
        Map<String,Object> paramMap = new HashMap<>();
        PageInfo<GoodsType> pageInfo = goodsTypeService.listGoodsTypeList(paramMap, 1,10);
        return  new BaseResponse(true, pageInfo.getList());
    }


    /**
     * 删除商品类型
     * @param goodsTypeId
     * @return
     */
    @RequestMapping("shop/goodsType/deleteGoodTypeById")
    public Object deleteGoodTypeById(String goodsTypeId){
        if(goodsTypeId == null ){
            return new BaseResponse("商品分类id不能为空");
        }
        goodsTypeService.delGoodsType(goodsTypeId);
        return new BaseResponse(true,"删除成功");
    }

    /**
     * 修改商品类型
     * @param goodsType
     * @return
     */
    @RequestMapping("shop/goodsType/updateGoodsTypeById")
    @Transactional
    public Object updateGoodsTypeById(GoodsType goodsType){
        if(goodsType.getTypeId() == null ){
            return new BaseResponse("商品分类id不能为空");
        }
        goodsTypeService.insertGoodsType(goodsType);
        return new BaseResponse(true,"修改成功");
    }

    /**
     * 添加商品类型
     * @param goodsType
     * @return
     */
    @RequestMapping("shop/goodsType/insertOrUpdateGoodsTypeById")
    @Transactional
    public Object insertGoodsType(GoodsType goodsType){
        if(goodsType.getName() == null ){
            return new BaseResponse("商品分类名称不能为空");
        }

        goodsType.setId(UUIDUtil.getUUID());
        goodsType.setAddTime(new Date());
        goodsType.setUpdateTime(new Date());
        goodsType.setStatus(0);
        goodsTypeService.insertGoodsType(goodsType);
        return new BaseResponse("添加成功");
    }
}
