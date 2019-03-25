package com.yj.common.config;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.yj.hqbz.services.system.OperationLogService;
import com.yj.hqbz.util.DateUtil;
import com.yj.hqbz.util.StaticUtils;
import com.yj.hqbz.web.Global;

@Component
public class TimedTask {

	@Resource
	private OperationLogService operationLogService;
	
	/**cron="* * * * * * *":
	 *依次表示秒，分，时，日，月，星期（1~7 1=SUN 或 SUN，MON，TUE，WED，THU，FRI，SAT），年（1970－2099，可省略）
	 *0 0 10,14,16 * * ?表示每天 10点，14点，16点
	 *
	 *每天凌晨1点删除3个月钱的日志
	 */
	@Scheduled(cron="0 0 1 * * ?")
	public void deleteLog() {
		Date time = DateUtil.getDateByBeforeOrAfter(new Date(), 0-Global.getSystemParamIntValueByKey(StaticUtils.OPLOG_HOLD_DAY));
		time=DateUtil.getDateByDate(time, "yyyy-MM-dd");
		//删除日志
		operationLogService.deleteLogByLessTime(time);
	}
	
}
