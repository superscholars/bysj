package com.stx.entity.user;

import java.io.Serializable;

/**
 * 用户与商铺的收藏关系表
 * @author gzzdsg
 * 2016年3月19日
 */
public class Collection implements Serializable{

	private static final long serialVersionUID = 7724003291869043227L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 商户id
	 */
	private Long merchantId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "Collection [id=" + id + ", userId=" + userId + ", merchantId="
				+ merchantId + "]";
	}
	
}
