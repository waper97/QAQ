package com.waper.shoppingcenter.service;

import com.waper.shoppingcenter.model.Goods;
import org.springframework.data.domain.Page;

/**
 * create by  on 2019/5/21
 * *
 **/

public interface GoodsService {
    Page<Goods> getGoodsList(Integer pageNum,int pageLimit);

}
