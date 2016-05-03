package com.stx.service.merchant;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.dao.GoodsDao;
import com.stx.entity.merchant.Goods;
import com.stx.entity.merchant.GoodsCategory;
import com.stx.entity.merchant.Merchant;
import com.stx.entity.vo.GoodsVo;
import com.stx.util.CollectionUtils;
import com.stx.util.StringUtils;

/**
 * 商品管理
 * @author gzzdsg
 * 2016年3月12日
 */
@Service
public class GoodsService {

	@Autowired
	private GoodsCategoryService goodsCategoryService;
	@Autowired
	private GoodsDao goodsDao;
	@Autowired
	private MerchantService merchantService;
	
	/**
	 * 商户是否已有分类
	 * @param merchantId
	 * @return
	 */
	public Boolean categoryExists(Long merchantId){
		return goodsCategoryService.queryExists(merchantId);
	}
	
	/**
	 * 查询热门商品
	 * @return
	 */
	public List<Goods> queryHotGoods(){
		return goodsDao.findHotGoods();
	}
	
	/**
	 * 查询商户的所有商品
	 * @param merchantId
	 * @return
	 */
	public List<GoodsVo> queryGoods(Long merchantId){
		List<GoodsCategory> categorys = goodsCategoryService.queryCategoryById(merchantId);
		if(CollectionUtils.isEmpty(categorys)){
			return null;
		}
		List<GoodsVo> reList = new ArrayList<GoodsVo>();
		for(GoodsCategory category : categorys){
			List<Goods> goodsList = goodsDao.findGoods(category.getId());
			if(CollectionUtils.isEmpty(goodsList)){
				continue;
			}
			GoodsVo goodsVo;
			for(Goods goods : goodsList){
				goodsVo = new GoodsVo();
				goodsVo.setId(goods.getId());
				goodsVo.setBuzz(goods.getBuzz());
				goodsVo.setCategory(category.getCategoryName());
				goodsVo.setGoodsDesc(goods.getGoodsDesc());
				goodsVo.setGoodsPic(goods.getGoodsPic());
				goodsVo.setMonthCount(goods.getMonthCount());
				goodsVo.setName(goods.getName());
				goodsVo.setPrice(goods.getPrice());
				reList.add(goodsVo);
			}
		}
		return reList;
	}
	
	/**
	 * 验证添加
	 * @param goods
	 * @param file
	 * @param fileName
	 * @param path
	 * @return
	 */
	public String validateAdd(Goods goods, String fileName){
		if(StringUtils.isEmpty(goods.getName())){
			return "请填写商品名称";
		}
		if(StringUtils.isEmpty(fileName)){
			return "照片为空";
		}
		String picEnd = fileName.substring(fileName.length() - 4,
				fileName.length());
		if (!picEnd.equals(".jpg") && !picEnd.equals(".png")) {
			return "头像只支持jpg或png格式。";
		}
		if(goods.getPrice()==null){
			return "请填写价格";
		}
		if(StringUtils.isEmpty(goods.getGoodsDesc())){
			return "请输入介绍";
		}
		if(goods.getCategoryId()==null){
			return "请选择分类";
		}
		return null;
	}
	
	/**
	 * 保存商品
	 * @param goods
	 * @return
	 */
	public Long saveGoods(Goods goods){
		return goodsDao.addGoods(goods);
	}
	
	/**
	 * 获取一个商品信息
	 * @param goodsId
	 * @return
	 */
	public Goods getOne(Long goodsId){
		return goodsDao.getEntiry(goodsId);
	}
	
	/**
	 * 验证修改
	 * @param goods
	 * @param file
	 * @param fileName
	 * @param path
	 * @return
	 */
	public String validateUpdate(Goods goods){
		if(StringUtils.isEmpty(goods.getName())){
			return "请填写商品名称";
		}
		if(goods.getPrice()==null){
			return "请填写价格";
		}
		if(StringUtils.isEmpty(goods.getGoodsDesc())){
			return "请输入介绍";
		}
		if(goods.getCategoryId()==null){
			return "请选择分类";
		}
		return "null";
	}
	
	public void updateGoods(Goods goods){
		goodsDao.updateGoods(goods);
	}
	
	/**
	 * 根据商品ids删除商品
	 * @param goodsId
	 */
	public void removeGoods(Long goodsId){
		Goods goods = new Goods();
		goods.setId(goodsId);
		goodsDao.removeGoods(goods);
	}
	
	/**
	 * 根据商品名称查询商家们
	 * @param goodsName
	 * @return
	 */
	public List<Merchant> findMerchantsByGoodsName(String goodsName){
		List<Goods> goodsList = goodsDao.findCategoryIdByGoodsName(goodsName);
		if(goodsList==null||goodsList.size()<1){
			return null;
		}
		Set<Long> categoryIds = new HashSet<Long>();
		for(Goods goods : goodsList){
			categoryIds.add(goods.getCategoryId());
		}
		List<Long> categoryList = new ArrayList<Long>();  
		categoryList.addAll(categoryIds);
		List<GoodsCategory> categorys =goodsCategoryService.queryGoodsCategory(categoryList);
		if(categorys==null||categorys.size()<1){
			return null;
		}
		Set<Long> merchantIds = new HashSet<Long>();
		for(GoodsCategory category : categorys){
			merchantIds.add(category.getMerchantId());
		}
		List<Long> merchantIdList = new ArrayList<Long>();
		merchantIdList.addAll(merchantIds);
		return merchantService.queryMerchantList(merchantIdList);
	}
	
	/**
	 * 增加商品销量
	 * @param goodsId
	 * @param count
	 */
	public void addGoodsMonthCount(Long goodsId, Integer count, Integer buzz){
		Goods goods = goodsDao.getEntiry(goodsId);
		goods.setMonthCount(goods.getMonthCount() + count);
		if(buzz==1){
			goods.setBuzz(goods.getBuzz() + 1);
		}
		goodsDao.updateGoods(goods);
	}
	
}
