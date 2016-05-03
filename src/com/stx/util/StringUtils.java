package com.stx.util;

/**
 * String的工具类
 * @author gzzdsg
 * 2016年3月12日
 */
public class StringUtils {
	
	/**
	 * 判断空值
	 * @param str
	 * @return
	 */
	public static Boolean isEmpty(String str){
		if(str==null||str.trim().length()<1){
			return true;
		}
		return false;
	}
	
	/**
	 * 判断不为空
	 * @param str
	 * @return
	 */
	public static Boolean isNotEmpty(String str){
		return !isEmpty(str);
	}
	
}
