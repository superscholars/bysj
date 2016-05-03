package com.stx.entity.admin;

import java.io.Serializable;
import java.util.Date;

/**
 * 处罚信息
 * @author gzzdsg
 * 2016年3月19日
 */
public class Punish implements Serializable{

	private static final long serialVersionUID = -9041057545729295404L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 商户id
	 */
	private Long merchantId;
	
	/**
	 * 商户名称
	 */
	private String merchantName;
	
	/**
	 * 操作人id
	 */
	private Long userId;
	
	/**
	 * 操作人名称
	 */
	private String userName;
	
	/**
	 * 处罚原因
	 */
	private String punishReason;
	
	/**
	 * 处罚日期       -1为永久封闭
	 */
	private Integer punishDays;
	
	/**
	 * 处罚时间
	 */
	private Date createTime;

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

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
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

	public String getPunishReason() {
		return punishReason;
	}

	public void setPunishReason(String punishReason) {
		this.punishReason = punishReason;
	}

	public Integer getPunishDays() {
		return punishDays;
	}

	public void setPunishDays(Integer punishDays) {
		this.punishDays = punishDays;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "Punish [id=" + id + ", merchantId=" + merchantId
				+ ", merchantName=" + merchantName + ", userId=" + userId
				+ ", userName=" + userName + ", punishReason=" + punishReason
				+ ", punishDays=" + punishDays + ", createTime=" + createTime
				+ "]";
	}

}
