package com.stx.entity;
/**
 * 系统常量
 * @author gzzdsg
 * 2016年3月10日
 */
public class Constants {
	
	/**
	 * 首页
	 */
	public static final String INDEX_PAGE = "/index.jsp";
	
	/**
	 * 登陆开关 1.允许登陆 2.禁闭  3待认证
	 */
	public static final int LOGIN_SWITCH_OPEN = 1;
	public static final int LOGIN_SWITCH_CLOSE = 2;
	public static final int LOGIN_SWITCH_WAITING =3;
	
	/**
	 * 用户类型 1.管理员 2.用户 3.商户
	 */
	public static final int ADMIN_TYPE = 1;
	public static final int USER_TYPE =2 ;
	public static final int MERCHANT_TYPE = 3;
	
	/**
	 * 商户优惠策略类型：1.新用户减免，2.满额度减免，3.折扣
	 */
	public static final int STRATEGY_XINYONGHU = 1;
	public static final int STRATEGY_MANJIAN = 2;
	public static final int STRATEGY_ZHEKOU = 3;
	
	/**
	 * 当前登录的Sessionkey
	 */
	public static final String USER = "loginContext";
	
	/**
	 * 购物车中保存的商品名称
	 */
	public static final String GOODS_LIST = "goodsList";
	
	/**
	 * 购物车中保存的商户id
	 */
	public static final String MERCHANT_ID = "merchantId";
	
	/**
	 * 当前所在页面的KEY
	 */
	public static final String PAGE = "currentPage";
	
	/**
	 * 购物车SessionKey后缀
	 */
	public static final String GOU = "shoppingCar"; 
	
	/**
	 * 订单状态 0.未支付。10.待接单。20.已接单，准备中。30.已备好。40.在路上。50.完成订单。60.取消订单
	 */
	public static final int STATUS_NOTPAY = 0;
	public static final int STATUS_WAITTING = 10;
	public static final int STATUS_READING = 20;
	public static final int STATUS_OK = 30;
	public static final int STATUS_ON_WAY = 40;
	public static final int STATUS_FINISH = 50;
	public static final int STATUS_CANCEL = 60;
	
	/**
	 * 订单状态  名称
	 */
	public static final String STATUS_NAME_NOTPAY = "未支付";
	public static final String STATUS_NAME_WAITTING = "待接单";
	public static final String STATUS_NAME_READING = "已接单，准备中";
	public static final String STATUS_NAME_OK = "已备好";
	public static final String STATUS_NAME_ON_WAY = "在路上";
	public static final String STATUS_NAME_FINISH = "完成订单";
	public static final String STATUS_NAME_CANCEL = "取消订单";
	
	/**
	 * 订单进程提示信息模板
	 */
	public static final String MSG_NOTPAY = "订单尚未支付。";
	public static final String MSG_WAITTING = "等待商家接单···";
	public static final String MSG_READING = "商家已接单准备中···";
	public static final String MSG_OK = "饭菜已经备好。";
	public static final String MSG_ON_WAY = "饭菜已在路上，请稍后···";
	public static final String MSG_FINISH = "订单已经完成。";
	public static final String MSG_CANCEL = "订单已经取消。";
	
	/**
	 * 上架标识 1.上架。2.下架
	 */
	public static final int FLAG_OPEN = 1;
	public static final int FLAG_CLOSE = 2;
}
