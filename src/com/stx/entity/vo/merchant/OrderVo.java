package com.stx.entity.vo.merchant;

import java.io.Serializable;
import java.util.List;

import com.stx.entity.order.GoodsInfo;
import com.stx.entity.order.Order;

/**
 * 商家订单中心展示
 * @author gzzdsg
 * 2016年3月25日
 */
public class OrderVo implements Serializable{

	private static final long serialVersionUID = 3420051147522029048L;

	private Order order;
	
	private List<GoodsInfo> goodsInfoList;

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

	@Override
	public String toString() {
		return "OrderVo [order=" + order + ", goodsInfoList=" + goodsInfoList
				+ "]";
	}
	
}
