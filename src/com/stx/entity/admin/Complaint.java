package com.stx.entity.admin;

import java.io.Serializable;
import java.util.Date;

/**
 * 投诉信息
 * @author gzzdsg
 * 2016年3月19日
 */
public class Complaint implements Serializable{

	private static final long serialVersionUID = 345292975335121674L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 用户昵称
	 */
	private String userName;
	
	/**
	 * 商户id
	 */
	private Long merchantId;
	
	/**
	 * 商户名称
	 */
	private String merchantName;
	
	/**
	 * 投诉的订单id
	 */
	private Long orderId;
	
	/**
	 * 投诉内容
	 */
	private String content;

	/**
	 * 投诉时间
	 */
	private Date createTime;

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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Complaint [id=" + id + ", userId=" + userId + ", userName="
				+ userName + ", merchantId=" + merchantId + ", merchantName="
				+ merchantName + ", orderId=" + orderId + ", content="
				+ content + ", createTime=" + createTime + "]";
	}

}
