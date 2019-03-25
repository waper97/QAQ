package com.yj.hqbz.util.tmthreadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.yj.hqbz.services.order.OrderLogService;
import com.yj.hqbz.services.system.OperationLogService;

public class TmThreadPool {// 用于管理线程和提供线程服务的类	
	private static final int POOL_SIZE = 40;// 线程池的容量
	private static ExecutorService exe = Executors.newFixedThreadPool(POOL_SIZE);// 创建线程池
	/** 线程添加操作日志*/
	public static void saveOperationLog(OperationLogService service,String userId,String ip,String content,String remark){
		exe.execute(new SaveOperationLog(service,userId, ip, content,remark));
	}
	/**发送短信*/
	public static void sendSms(String url,String phone,String model,String[] args){
        exe.execute(new SmsThread(url,phone, model, args));
    }
	/**写订单日志*/
	public static void saveOrderLog(OrderLogService logService,String orderid,String operatorid,String operator,String content){
	    exe.execute(new SaveOrderLog(logService,orderid,operatorid,operator,content));
	}

		
}