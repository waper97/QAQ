package com.waper.shoppingcenter.service.impl;

import com.waper.shoppingcenter.dao.OperatorLogDao;
import com.waper.shoppingcenter.model.OperatorLog;
import com.waper.shoppingcenter.service.OperatorLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * create by  on 2019/5/22
 * *
 **/
@Service
public class OperatorLogServiceImpl implements OperatorLogService {

    @Autowired
    private OperatorLogDao operatorLogDao;


    @Override
    @Transactional
    public void insertOperatorLog(OperatorLog log) {
         operatorLogDao.save(log);
    }
}
