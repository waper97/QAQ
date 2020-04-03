package com.waper.shoppingcenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waper.shoppingcenter.dao.goodstype.GoodsTypeMapper;
import com.waper.shoppingcenter.model.GoodsType;
import com.waper.shoppingcenter.service.GoodsTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 * 商品类型服务
 * @ClassName GoodsTypeServiceImpl
 * @Description TODO
 * @Author wangpeng
 * @Date 2020/4/3 14:32
 */
@Service
public class GoodsTypeServiceImpl implements GoodsTypeService {

    @Autowired
    private GoodsTypeMapper goodsTypeMapper;


    @Override
    public PageInfo<GoodsType> listGoodsTypeList(Map<String, Object> paramMap,int pageNum, int pageLimit) {
        PageHelper.startPage(pageNum, pageLimit);
        List<GoodsType> goodsTypeList = goodsTypeMapper.listGoodsTypes(paramMap);
        PageInfo<GoodsType> pageInfo = new PageInfo<>(goodsTypeList);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int delGoodsType(String id) {
        return goodsTypeMapper.delGoodsType(id);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int insertGoodsType(GoodsType goodsType) {
        return goodsTypeMapper.insertGoodsType(goodsType);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public GoodsType getGoodsTypeById(String goodsId) {
        return goodsTypeMapper.getGoodsTypeById(goodsId);
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public int updateGoodsType(GoodsType goodsType) {
        return goodsTypeMapper.updateGoodsType(goodsType);
    }
}
