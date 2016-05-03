package com.stx.entity.merchant;

import java.io.Serializable;

/**
 * 商家营销策略
 * @author gzzdsg
 * 2016年3月13日
 */
public class Strategy implements Serializable{

	private static final long serialVersionUID = -1495728748422875251L;

	/**
	 * 策略id
	 */
	private Long id ;
	
	/**
	 * 商户id
	 */
	private Long merchantId;
	
	/**
	 * 优惠类型
	 */
	private Integer strategyType;
	
	/**
	 * 前提消费
	 */
	private Float premisePrice;
	
	/**
	 * 优惠额度
	 */
	private Float balancePrice;
	
	/**
	 * 折扣
	 */
	private Integer discount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public Integer getStrategyType() {
		return strategyType;
	}

	public void setStrategyType(Integer strategyType) {
		this.strategyType = strategyType;
	}

	public Float getPremisePrice() {
		return premisePrice;
	}

	public void setPremisePrice(Float premisePrice) {
		this.premisePrice = premisePrice;
	}

	public Float getBalancePrice() {
		return balancePrice;
	}

	public void setBalancePrice(Float balancePrice) {
		this.balancePrice = balancePrice;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Strategy [id=" + id + ", merchantId=" + merchantId
				+ ", strategyType=" + strategyType + ", premisePrice="
				+ premisePrice + ", balancePrice=" + balancePrice
				+ ", discount=" + discount + "]";
	}

}
