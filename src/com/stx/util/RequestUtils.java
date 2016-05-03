package com.stx.util;

import javax.servlet.http.HttpServletRequest;

import com.stx.entity.Constants;
import com.stx.entity.User;

public class RequestUtils {
	
	/**
	 * 获取id
	 * @param request
	 * @return
	 */
	public static Long getId(HttpServletRequest request){
		try {
			return Long.valueOf(request.getParameter("id"));
		}catch (Exception e){
			System.out.println("跳转编辑类型参数错误");
			e.printStackTrace();
			return 0L;
		}
	}
	
	/**
	 * 获取当前登录用户
	 * @param request
	 * @return
	 */
	public static User getUser(HttpServletRequest request){
		return (User)request.getSession().getAttribute(Constants.USER);
	}
	
	/**
	 * 获取商户id
	 * @param request
	 * @return
	 */
	public static Long getMerchantId(HttpServletRequest request){
		try {
			return Long.valueOf(request.getParameter("merchantId"));
		}catch (Exception e){
			System.out.println("跳转编辑类型参数错误");
			e.printStackTrace();
			return 0L;
		}
	}
}
