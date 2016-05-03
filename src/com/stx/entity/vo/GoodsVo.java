package com.stx.entity.vo;

import java.io.Serializable;

/**
 * 用于展示商品
 * @author gzzdsg
 * 2016年3月12日
 */
public class GoodsVo implements Serializable{

	private static final long serialVersionUID = 6845527628150861959L;

	/**
	 * 商品id
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
	 * 商品分类
	 */
	private String category;
	
	/**
	 * 月销量
	 */
	private Integer monthCount;
	
	/**
	 * 点赞量
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
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
		return "GoodsVo [id=" + id + ", name=" + name + ", price=" + price
				+ ", goodsDesc=" + goodsDesc + ", goodsPic=" + goodsPic
				+ ", category=" + category + ", monthCount=" + monthCount
				+ ", buzz=" + buzz + "]";
	}
	
}
