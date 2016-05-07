package com.stx.entity.merchant;

import java.io.Serializable;

/**
 * 功能：商户信息表
 * @author gzzdsg
 * 2016年3月8日
 */
public class Merchant implements Serializable{

	private static final long serialVersionUID = -4591955901024672553L;

	/**
	 * 商户主键id
	 */
	private Long id;
	
	/**
	 * 登陆账号
	 */
	private Long userId;
	
	/**
	 * logo存放位置
	 */
	private String logoAddr;
	
	/**
	 * 商户联系电话
	 */
	private String mobile;
	
	/**
	 * 商户名称
	 */
	private String name;
	
	/**
	 * 商户地址
	 */
	private String merchantAddr;
	
	/**
	 * 商户类型
	 */
	private String merchantType;
	
	/**
	 * 广告标语
	 */
	private String slogen;
	
	/**
	 * 月销量
	 */
	private Integer monthCount;
	
	/**
	 * 起送费
	 */
	private Float deliveryStart;
	
	/**
	 * 配送费
	 */
	private Float deliveryPrice;
	
	/**
	 * 餐具费
	 */
	private Float boxPrice;
	
	/**
	 * 配送时间
	 */
	private Integer deliveryTime;
	
	/**
	 * 营业时间
	 */
	private String workTime;
	
	/**
	 * 是否支持货到付款
	 */
	private Integer codFlag;
	
	/**
	 * 上架标识
	 */
	private Integer openFlag;

	
	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public Integer getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(Integer openFlag) {
		this.openFlag = openFlag;
	}

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

	public String getLogoAddr() {
		return logoAddr;
	}

	public void setLogoAddr(String logoAddr) {
		this.logoAddr = logoAddr;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMerchantAddr() {
		return merchantAddr;
	}

	public void setMerchantAddr(String merchantAddr) {
		this.merchantAddr = merchantAddr;
	}

	public String getSlogen() {
		return slogen;
	}

	public void setSlogen(String slogen) {
		this.slogen = slogen;
	}

	public Integer getMonthCount() {
		return monthCount;
	}

	public void setMonthCount(Integer monthCount) {
		this.monthCount = monthCount;
	}

	public Float getDeliveryStart() {
		return deliveryStart;
	}

	public void setDeliveryStart(Float deliveryStart) {
		this.deliveryStart = deliveryStart;
	}

	public Float getDeliveryPrice() {
		return deliveryPrice;
	}

	public void setDeliveryPrice(Float deliveryPrice) {
		this.deliveryPrice = deliveryPrice;
	}

	public Float getBoxPrice() {
		return boxPrice;
	}

	public void setBoxPrice(Float boxPrice) {
		this.boxPrice = boxPrice;
	}

	public Integer getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Integer deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public Integer getCodFlag() {
		return codFlag;
	}

	public void setCodFlag(Integer codFlag) {
		this.codFlag = codFlag;
	}

	@Override
	public String toString() {
		return "Merchant [id=" + id + ", userId=" + userId + ", logoAddr="
				+ logoAddr + ", mobile=" + mobile + ", name=" + name
				+ ", merchantAddr=" + merchantAddr + ", merchantType="
				+ merchantType + ", slogen=" + slogen + ", monthCount="
				+ monthCount + ", deliveryStart=" + deliveryStart
				+ ", deliveryPrice=" + deliveryPrice + ", boxPrice=" + boxPrice
				+ ", deliveryTime=" + deliveryTime + ", workTime=" + workTime
				+ ", codFlag=" + codFlag + ", openFlag=" + openFlag + "]";
	}
	
}
