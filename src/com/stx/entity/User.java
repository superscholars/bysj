package com.stx.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 功能：用于登陆的账号
 * 
 * @author gzzdsg
 * 2016年3月8日
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = -9174213559821294817L;

	/**
	 * 主键id
	 */
	private Long id ;
	
	/**
	 * 登陆名
	 */
	private String loginName;
	
	/**
	 * 登陆密码
	 */
	private String password;
	
	/**
	 * 昵称
	 */
	private String nickName;
	
	/**
	 * 用户类型：1.管理。2.用户。3.商家
	 */
	private Integer userType;
	
	/**
	 * 登陆开关：1.能够登陆。2.不能登陆。3.待审核
	 * 待审核状态只有商家注册时会用到
	 */
	private Integer loginSwitch;
	
	/**
	 * 超级管理标识：1.是超级管理。2.不是超级管理
	 * 超级管理可以管理普通管理员的账号
	 */
	private Integer adminFlag;
	
	/**
	 * 注册时间
	 */
	private Date createTime;
	
	/**
	 * 头像存放地址
	 */
	private String headPath;
	
	
	public String getHeadPath() {
		return headPath;
	}
	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getLoginSwitch() {
		return loginSwitch;
	}
	public void setLoginSwitch(Integer loginSwitch) {
		this.loginSwitch = loginSwitch;
	}
	public Integer getAdminFlag() {
		return adminFlag;
	}
	public void setAdminFlag(Integer adminFlag) {
		this.adminFlag = adminFlag;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", loginName=" + loginName + ", password="
				+ password + ", nickName=" + nickName + ", userType="
				+ userType + ", loginSwitch=" + loginSwitch + ", adminFlag="
				+ adminFlag + ", createTime=" + createTime + ", headAddr="
				+ headPath + "]";
	}
}
