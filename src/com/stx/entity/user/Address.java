package com.stx.entity.user;

import java.io.Serializable;

/**
 * 用户地址信息
 * @author gzzdsg
 * 2016年3月19日
 */
public class Address implements Serializable{

	private static final long serialVersionUID = 3570565059994258302L;
	
	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 用户id
	 */
	private Long userId;
	
	/**
	 * 真实姓名
	 */
	private String realName;
	
	/**
	 * 联系方式
	 */
	private String mobile;
	
	/**
	 * 性别1.男2.女
	 */
	private String sex;
	
	/**
	 * 地址详情
	 */
	private String addressAttr;
	
	/**
	 * 门牌号码
	 */
	private String houseNumber;
	
	/**
	 * 地址状态 1.默认。2非默认
	 */
	private Integer status;

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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddressAttr() {
		return addressAttr;
	}

	public void setAddressAttr(String addressAttr) {
		this.addressAttr = addressAttr;
	}

	public String getHouseNumber() {
		return houseNumber;
	}

	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", userId=" + userId + ", realName="
				+ realName + ", mobile=" + mobile + ", sex=" + sex
				+ ", addressAttr=" + addressAttr + ", houseNumber="
				+ houseNumber + ", status=" + status + "]";
	}
	
}
