package com.yj.hqbz.util.tmthreadpool;

import java.util.Date;

import com.yj.hqbz.model.system.OperationLog;
import com.yj.hqbz.services.system.OperationLogService;
import com.yj.hqbz.util.StringUtil;

public class SaveOperationLog extends Thread{
	private String userId;
	private String ip;
	private String content;
	private String remark;
	private OperationLogService service;
	public SaveOperationLog(OperationLogService service,String userId,  String ip,String content,String remark) {
		this.service=service;
		this.userId=userId;
		this.ip=ip;
		this.content=content;
		this.remark=remark;
	}
	@Override
	public void run() {
		OperationLog log=new OperationLog();
		log.setUserid(userId);
		log.setOpTime(new Date());
		log.setIp(ip);
		log.setContent(content.length()>333?content.substring(0,333):content);
		if(StringUtil.isNotBlank(remark)) {
			log.setRemark(remark);
		}
		service.addLog(log);
	}
}
