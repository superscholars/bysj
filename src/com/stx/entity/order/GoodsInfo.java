package com.stx.entity.order;

import java.io.Serializable;

/**
 * 订单中商品信息表
 * @author gzzdsg
 * 2016年3月14日
 */
public class GoodsInfo implements Serializable{

	private static final long serialVersionUID = 2961816988719813576L;

	/**
	 * 主键id
	 */
	private Long id ;
	
	/**
	 * 订单id
	 */
	private Long orderId;
	
	/**
	 * 商品id
	 */
	private Long goodsId;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 商品数量
	 */
	private Integer goodsCount;
	
	/**
	 * 商品价格
	 */
	private Float goodsPrice;

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

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public Integer getGoodsCount() {
		return goodsCount;
	}

	public void setGoodsCount(Integer goodsCount) {
		this.goodsCount = goodsCount;
	}

	public Float getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(Float goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	@Override
	public String toString() {
		return "GoodsInfo [id=" + id + ", orderId=" + orderId + ", goodsName="
				+ goodsName + ", goodsCount=" + goodsCount + ", goodsPrice="
				+ goodsPrice + "]";
	}
	
}
