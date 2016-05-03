package com.stx.util;

import java.util.Collection;

public class CollectionUtils {

	/**
	 * 判断集合是否为空
	 * @param collection
	 * @return
	 */
	public static Boolean isEmpty(Collection<?> collection){
		return collection==null||collection.size()<1;
	}
	
	/**
	 * 判断集合不为空
	 * @param collection
	 * @return
	 */
	public static Boolean isNotEmpty(Collection<?> collection){
		return !isEmpty(collection);
	}
	
}
