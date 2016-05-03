package com.stx.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用于评论商品
 * @author gzzdsg
 * 2016年3月12日
 */
public class CommentVo implements Serializable{

	private static final long serialVersionUID = 6845527628150861959L;

	/**
	 * 评论id
	 */
	private Long id;
	
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
	
	/**
	 * 头像位置
	 */
	private String headPath;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getHeadPath() {
		return headPath;
	}

	public void setHeadPath(String headPath) {
		this.headPath = headPath;
	}

	@Override
	public String toString() {
		return "CommentVo [id=" + id + ", createName=" + createName
				+ ", commentContent=" + commentContent + ", createTime="
				+ createTime + ", headPath=" + headPath + "]";
	}

}
