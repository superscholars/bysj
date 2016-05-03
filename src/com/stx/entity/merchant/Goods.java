package com.stx.entity.merchant;

import java.io.Serializable;

/**
 * 商品
 * @author gzzdsg
 * 2016年3月12日
 */
public class Goods implements Serializable{

	private static final long serialVersionUID = 3006540492579280149L;
	
	/**
	 * 主键id
	 */
	private Long id;
	
	/**
	 * 商品名称
	 */
	private String name;
	
	/**
	 * 商品价格
	 */
	private Float price;
	
	/**
	 * 商品描述
	 */
	private String goodsDesc;
	
	/**
	 * 商品图片
	 */
	private String goodsPic;
	
	/**
	 * 分类id
	 */
	private Long categoryId;
	
	/**
	 * 商品月销量
	 */
	private Integer monthCount;
	
	/**
	 * 商品点赞量
	 */
	private Integer buzz;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getGoodsDesc() {
		return goodsDesc;
	}

	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}

	public String getGoodsPic() {
		return goodsPic;
	}

	public void setGoodsPic(String goodsPic) {
		this.goodsPic = goodsPic;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getMonthCount() {
		return monthCount;
	}

	public void setMonthCount(Integer monthCount) {
		this.monthCount = monthCount;
	}

	public Integer getBuzz() {
		return buzz;
	}

	public void setBuzz(Integer buzz) {
		this.buzz = buzz;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price
				+ ", goodsDesc=" + goodsDesc + ", goodsPic=" + goodsPic
				+ ", categoryId=" + categoryId + ", monthCount=" + monthCount
				+ ", buzz=" + buzz + "]";
	}
	
}
