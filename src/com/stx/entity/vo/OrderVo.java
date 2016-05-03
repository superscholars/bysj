package com.stx.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单展示
 * @author gzzdsg
 * 2016年3月15日
 */
public class OrderVo implements Serializable{

	private static final long serialVersionUID = 5819077995654059455L;

	/**
	 * 订单主键id
	 */
	private Long orderId;
	
	/**
	 * 订单号码
	 */
	private String orderNumber;
	
	/**
	 * 用户名称
	 */
	private String userName;
	
	/**
	 * 创建时间
	 */
	private Date createTime;
	
	/**
	 * 用户住址
	 */
	private String userAddr;
	
	/**
	 * 状态名称
	 */
	private String statusAttr;
	
	/**
	 * 支付方式
	 */
	private String payType;
	
	/**
	 * 食用方式
	 */
	private String eatType;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getStatusAttr() {
		return statusAttr;
	}

	public void setStatusAttr(String statusAttr) {
		this.statusAttr = statusAttr;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getEatType() {
		return eatType;
	}

	public void setEatType(String eatType) {
		this.eatType = eatType;
	}

	@Override
	public String toString() {
		return "OrderVo [orderId=" + orderId + ", orderNumber=" + orderNumber
				+ ", userName=" + userName + ", createTime=" + createTime
				+ ", userAddr=" + userAddr + ", statusAttr=" + statusAttr
				+ ", payType=" + payType + ", eatType=" + eatType + "]";
	}
	
}
