package com.stx.entity.order;

import java.io.Serializable;
import java.util.Date;

/**
 * 主订单表
 * @author gzzdsg
 * 2016年3月14日
 */
public class Order implements Serializable{

	private static final long serialVersionUID = -6408607335443698666L;

	/**
	 * 主键id
	 */
	private Long id ;
	
	/**
	 * 订单号码
	 */
	private String orderNumber;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 商户id
	 */
	private Long merchantId;
	
	/**
	 * 下单时间
	 */
	private Date createTime;
	
	/**
	 * 商户名称
	 */
	private String merchantName;
	
	/**
	 * 用户地址
	 */
	private String userAddr;
	
	/**
	 * 订单状态 0.未支付。 10.待接单。20.已接单，准备中。30.已备好。40.在路上。50.完成订单。60.取消订单。
	 */
	private Integer status;
	
	/**
	 * 支付方式 1.在线支付 。2.货到付款
	 */
	private Integer payType;
	
	/**
	 * 食用方式 1.外卖。2.堂吃
	 */
	private Integer eatType;
	
	/**
	 * 预计配送时间。单位：分钟
	 */
	private Integer deliveryTime;
	
	/**
	 * 完成时间 
	 */
	private Date finishTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}

	public Integer getEatType() {
		return eatType;
	}

	public void setEatType(Integer eatType) {
		this.eatType = eatType;
	}
	
	public Integer getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Integer deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderNumber=" + orderNumber + ", userId="
				+ userId + ", merchantId=" + merchantId + ", createTime="
				+ createTime + ", merchantName=" + merchantName + ", userAddr="
				+ userAddr + ", status=" + status + ", payType=" + payType
				+ ", eatType=" + eatType + ", finishTime=" + finishTime + "]";
	}
	
}
