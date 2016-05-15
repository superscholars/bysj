package com.stx.entity.order;

import java.io.Serializable;

/**
 * 订单价格详情
 * @author gzzdsg
 * 2016年3月14日
 */
public class PriceInfo implements Serializable{

	private static final long serialVersionUID = 3467009154465182474L;
	
	/**
	 * 主键id
	 */
	private Long id ;
	
	/**
	 * 订单id
	 */
	private Long orderId;
	
	/**
	 * 订单总价
	 */
	private Float amount;
	
	/**
	 * 优惠金额
	 */
	private Float balance;
	
	/**
	 * 餐具价格
	 */
	private Float boxPrice;
	
	/**
	 * 配送费
	 */
	private Float deliveryPrice;
	
	/**
	 * 最后支付金额
	 */
	private Float payPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Float getBalance() {
		return balance;
	}

	public void setBalance(Float balance) {
		this.balance = balance;
	}

	public Float getBoxPrice() {
		return boxPrice;
	}

	public void setBoxPrice(Float boxPrice) {
		this.boxPrice = boxPrice;
	}

	public Float getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(Float deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public Float getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(Float payPrice) {
		this.payPrice = payPrice;
	}

	@Override
	public String toString() {
		return "PriceInfo [id=" + id + ", orderId=" + orderId + ", amount="
				+ amount + ", balance=" + balance + ", boxPrice=" + boxPrice
				+ ", deliveryPrice=" + deliveryPrice + ", payPrice=" + payPrice
				+ "]";
	}
	
}
