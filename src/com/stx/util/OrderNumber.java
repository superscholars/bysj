package com.stx.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderNumber {

	/**
	 * 单号生成器
	 * @return
	 */
	public static String generator(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return "DH"+sdf.format(new Date());
	}
	
}
