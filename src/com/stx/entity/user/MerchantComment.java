package com.stx.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户对商家的评论表
 * @author gzzdsg
 * 2016年3月19日
 */
public class MerchantComment implements Serializable{

	private static final long serialVersionUID = 4524278230954842899L;
	
	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 被评论的商户
	 */
	private Long merchantId;
	
	/**
	 * 评论人
	 */
	private Long createBy;
	
	/**
	 * 评论人名称
	 */
	private String createName;
	
	/**
	 * 回复内容
	 */
	private String commentContent;
	
	/**
	 * 评论时间
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

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "MerchantComment [id=" + id + ", merchantId=" + merchantId
				+ ", createBy=" + createBy + ", createName=" + createName
				+ ", commentContent=" + commentContent + ", createTime="
				+ createTime + "]";
	}

}
