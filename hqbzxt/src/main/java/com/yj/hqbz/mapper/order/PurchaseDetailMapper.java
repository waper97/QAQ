package com.yj.hqbz.mapper.order;

import java.util.List;

import com.yj.hqbz.model.order.PurchaseDetail;

public interface PurchaseDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(PurchaseDetail record);
    
    List<PurchaseDetail> selectListByIndexid(String indexid);

    int update(PurchaseDetail record);
}