package com.stx.entity.merchant;

import java.io.Serializable;


/**
 * 功能：商户类型表
 * @author gzzdsg
 * 2016年3月8日
 */
public class MerchantType implements Serializable{
	
	private static final long serialVersionUID = 4070617203331282888L;
	
	/**
	 * 商户类型主键id
	 */
	private Long id;
	
	/**
	 * 商户类型名称
	 */
	private String merchantType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	@Override
	public String toString() {
		return "MerchantType [id=" + id + ", merchantType=" + merchantType
				+ "]";
	}
}
