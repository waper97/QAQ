package com.waper.shoppingcenter.service.impl;

import com.waper.shoppingcenter.dao.GoodsDao;
import com.waper.shoppingcenter.model.Goods;
import com.waper.shoppingcenter.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * create by  on 2019/5/21
 * *
 **/
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;
    @Override
    public Page<Goods> getGoodsList(Integer pageNum, int pageLimit) {
        Pageable pageable = PageRequest.of(pageNum - 1,pageLimit);
        return goodsDao.findAll(pageable);
    }
}
