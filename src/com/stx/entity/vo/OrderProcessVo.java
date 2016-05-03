package com.stx.entity.vo;

import java.io.Serializable;
import java.util.List;

import com.stx.entity.order.Order;
import com.stx.entity.order.OrderProcess;
import com.stx.entity.order.PriceInfo;

/**
 * 订单中心的展示对象
 * @author gzzdsg
 * 2016年3月24日
 */
public class OrderProcessVo implements Serializable{

	private static final long serialVersionUID = 3577767800335381591L;

	/**
	 * 订单信息
	 */
	private Order order;
	
	/**
	 * 商家图片
	 */
	private String logoAddr;
	
	/**
	 * 消费信息
	 */
	private PriceInfo priceInfo;
	
	/**
	 * 进程信息
	 */
	private List<OrderProcess> processList;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getLogoAddr() {
		return logoAddr;
	}

	public void setLogoAddr(String logoAddr) {
		this.logoAddr = logoAddr;
	}

	public PriceInfo getPriceInfo() {
		return priceInfo;
	}

	public void setPriceInfo(PriceInfo priceInfo) {
		this.priceInfo = priceInfo;
	}

	public List<OrderProcess> getProcessList() {
		return processList;
	}

	public void setProcessList(List<OrderProcess> processList) {
		this.processList = processList;
	}

	@Override
	public String toString() {
		return "OrderProcessVo [order=" + order + ", logoAddr=" + logoAddr
				+ ", priceInfo=" + priceInfo + ", processList=" + processList
				+ "]";
	}
	
}
