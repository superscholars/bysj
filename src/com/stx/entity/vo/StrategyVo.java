package com.stx.entity.vo;

import java.io.Serializable;

/**
 * 用于显示优惠
 * @author gzzdsg
 * 2016年3月23日
 */
public class StrategyVo implements Serializable{

	private static final long serialVersionUID = 5267055622852037313L;
	
	/**
	 * 策略信息
	 */
	private String strategy;
	
	/**
	 * 商户id
	 */
	private Long merchantId;

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	@Override
	public String toString() {
		return "StrategyVo [strategy=" + strategy + ", merchantId="
				+ merchantId + "]";
	}
	
}
