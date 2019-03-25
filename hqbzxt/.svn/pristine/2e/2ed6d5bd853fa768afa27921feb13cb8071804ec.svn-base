package com.yj.hqbz.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * String转换为Date格式
	 * 
	 * @param datastr：时间字符串
	 * @param formatStr：转换格式
	 */
	public static Date getDateByStr(String datastr, String formatStr) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatStr);
			Date date = format.parse(datastr);
			return date;
		} catch (Exception e) {
			new RuntimeException("日期格式错误！").printStackTrace();
			return null;
		}
	}

	/**
	 * Date转换为String格式
	 * 
	 * @param date：时间
	 * @param formatStr：转换格式
	 */
	public static String getStrByDate(Date date, String formatStr) {
		if (date != null) {
			SimpleDateFormat format = new SimpleDateFormat(formatStr);
			String str = format.format(date);
			return str.trim();
		} else {
			new RuntimeException("日期格式错误！").printStackTrace();
			return null;
		}
	}

	/**
	 * Date转换为Date格式
	 * 
	 * @param date：时间
	 * @param formatStr：转换格式
	 */
	public static Date getDateByDate(Date date, String formatStr) {
		try {
			SimpleDateFormat format = new SimpleDateFormat(formatStr);
			String str = format.format(date);
			return format.parse(str);
		} catch (Exception e) {
			new RuntimeException("日期格式错误！").printStackTrace();
			return null;
		}
	}

	/**
	 * 获取两个日期相差的天数
	 * 
	 * @param beginDate:开始时间
	 * @param endDate;结束时间
	 * @return
	 */
	public static Integer getDayByTwoDate(Date beginDate, Date endDate) {
		if (beginDate != null && endDate != null) {
			beginDate = getDateByDate(beginDate, "yyyy-MM-dd");
			endDate = getDateByDate(beginDate, "yyyy-MM-dd");
			long day = (endDate.getTime() - beginDate.getTime()) / (24 * 3600 * 1000);
			return (int) day;
		} else {
			return null;
		}
	}

	/**
	 * 获取指定日期的前(后)几天
	 * 
	 * @param date:指定时间
	 * @param day:天数，前几天为负数，后几为正数
	 * @return
	 */
	public static Date getDateByBeforeOrAfter(Date date, int day) {
		if (date != null) {
			date = getDateByDate(date, "yyyy-MM-dd");
			Calendar now = Calendar.getInstance();
			now.setTime(date);
			now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
			return now.getTime();
		} else {
			return null;
		}
	}

	/**
	 * 获取两个时间戳相差的分钟数（一般用于短信验证时间是否过期）
	 * 
	 * @param beginTime:开始时间戳
	 * @param endTime:结束时间戳
	 * @return
	 */
	public static long getMinutesByTwoTimestamp(long beginTime, long endTime) {
		long timestamp = (endTime - beginTime) / 1000;
		return (timestamp / 60);
	}

	/**
     * 判断2个日期先后
     * @param args
     * 0:相等，大于0：date1大于date2，小于0：date1小于date2
     */
    public static int getCompareToByTwoDate(Date date1,Date date2){
   	 date1=getDateByDate(date1, "yyyy-MM-dd");
   	 date2=getDateByDate(date2, "yyyy-MM-dd");
   	 return date1.compareTo(date2);
   	 
    }
}
