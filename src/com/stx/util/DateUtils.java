package com.stx.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	/**
	 * 增加天数
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date addDate(Date date , Integer days){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
	
}
