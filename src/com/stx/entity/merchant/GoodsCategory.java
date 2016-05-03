package com.stx.entity.merchant;

import java.io.Serializable;

/**
 * 商品分类
 * @author gzzdsg
 * 2016年3月12日
 */
public class GoodsCategory implements Serializable{

	private static final long serialVersionUID = -465503556305542502L;

	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 分类名称
	 */
	private String categoryName;
	
	/**
	 * 商户id
	 */
	private Long merchantId;
	
	/**
	 * 排序字段
	 */
	private Integer sort;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(Long merchantId) {
		this.merchantId = merchantId;
	}

	@Override
	public String toString() {
		return "GoodsCategory [id=" + id + ", categoryName=" + categoryName
				+ ", merchantId=" + merchantId + ", sort=" + sort + "]";
	}

}
