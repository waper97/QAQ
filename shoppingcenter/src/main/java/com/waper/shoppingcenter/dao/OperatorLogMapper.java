package com.waper.shoppingcenter.dao;

import com.waper.shoppingcenter.model.OperatorLog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * create by  on 2019/5/22
 * 操作日志服务
 * *
 **/
public interface OperatorLogMapper extends JpaRepository<OperatorLog,String> {
}
