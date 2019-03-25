package com.yj.hqbz.util.tmthreadpool;

import java.util.Date;

import com.yj.hqbz.model.order.OrderLog;
import com.yj.hqbz.services.order.OrderLogService;

public class SaveOrderLog extends Thread {

    private String operatorid;
    private String orderid;
    private String content;
    private String operator;
    private OrderLogService logService;
    
    public SaveOrderLog(OrderLogService logService,String orderid,String operatorid,String operator,String content){
        this.content = content;
        this.operator = operator;
        this.operatorid = operatorid;
        this.orderid = orderid;
        this.logService = logService;
    }
   
    public void run() {
        OrderLog log = new OrderLog();
        log.setContent(content);
        log.setLogDate(new Date());
        log.setOperator(operator);
        log.setOperatorid(operatorid);
        log.setOrderid(orderid);
        logService.addOrderLog(log);
    }

}
