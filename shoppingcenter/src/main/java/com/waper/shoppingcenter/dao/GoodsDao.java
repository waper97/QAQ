package com.waper.shoppingcenter.dao;

import com.waper.shoppingcenter.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface GoodsDao extends JpaRepository<Goods,String> {


}
