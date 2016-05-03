package com.stx.entity.vo;

import java.io.Serializable;
import java.util.List;

import com.stx.entity.order.GoodsInfo;
import com.stx.entity.order.Order;
import com.stx.entity.order.OrderProcess;
import com.stx.entity.order.PriceInfo;

/**
 * 订单详情展示对象
 * @author gzzdsg
 * 2016年3月25日
 */
public class OrderDetailVo implements Serializable{

	private static final long serialVersionUID = 8399751660795513381L;

	/**
	 * 订单信息
	 */
	private Order order;
	
	/**
	 * 商品信息
	 */
	private List<GoodsInfo> goodsInfoList;
	
	/**
	 * 订单流程
	 */
	private List<OrderProcess> orderProcessList;
	
	/**
	 * 消费信息
	 */
	private PriceInfo priceInfo;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<GoodsInfo> getGoodsInfoList() {
		return goodsInfoList;
	}

	public void setGoodsInfoList(List<GoodsInfo> goodsInfoList) {
		this.goodsInfoList = goodsInfoList;
	}

	public List<OrderProcess> getOrderProcessList() {
		return orderProcessList;
	}

	public void setOrderProcessList(List<OrderProcess> orderProcessList) {
		this.orderProcessList = orderProcessList;
	}

	public PriceInfo getPriceInfo() {
		return priceInfo;
	}

	public void setPriceInfo(PriceInfo priceInfo) {
		this.priceInfo = priceInfo;
	}

	@Override
	public String toString() {
		return "OrderDetailVo [order=" + order + ", goodsInfoList="
				+ goodsInfoList + ", orderProcessList=" + orderProcessList
				+ ", priceInfo=" + priceInfo + "]";
	}

}
