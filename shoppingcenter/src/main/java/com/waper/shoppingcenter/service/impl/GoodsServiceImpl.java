package com.waper.shoppingcenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.waper.shoppingcenter.dao.goods.GoodsMapper;
import com.waper.shoppingcenter.model.Goods;
import com.waper.shoppingcenter.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * create by  on 2019/5/21
 * *
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public PageInfo<Goods> getGoodsList(Integer pageNum, Integer pageLimit, Map<String, Object> paramMap) {
        PageHelper.startPage(pageNum,pageLimit);
        List<Goods> list = goodsMapper.listGoods(paramMap);
        PageInfo<Goods> page = new PageInfo<>(list);
        return page;
    }

    @Override
    public int deleteGoodsByPrimaryKey(String id) {
        return goodsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insertGoods(Goods goods) {
        return goodsMapper.insert(goods);
    }

    @Override
    public Goods selectGoodsById(String goodsId) {
        return goodsMapper.selectByPrimaryKey(goodsId);
    }

    @Override
    public int updateById(Goods goods) {
        return goodsMapper.updateByPrimaryKeySelective(goods);
    }
}
