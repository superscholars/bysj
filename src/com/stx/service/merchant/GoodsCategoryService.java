package com.stx.service.merchant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stx.dao.GoodsCategoryDao;
import com.stx.dao.GoodsDao;
import com.stx.entity.merchant.GoodsCategory;
import com.stx.util.CollectionUtils;
import com.stx.util.StringUtils;

/**
 * 商品分类管理
 * @author gzzdsg
 * 2016年3月12日
 */
@Service
public class GoodsCategoryService {

	@Autowired
	private GoodsCategoryDao goodsCategoryDao;
	@Autowired
	private GoodsDao goodsDao;
	
	
	/**
	 * 验证商户是否有分类
	 * @param merchantId
	 * @return
	 */
	public Boolean queryExists(Long merchantId){
		List<GoodsCategory> list = goodsCategoryDao.findGoodsCategory(merchantId);
		return CollectionUtils.isNotEmpty(list);
	}
	
	/**
	 * 根据商户id查找分类信息
	 * @param merchantId
	 * @return
	 */
	public List<GoodsCategory> queryCategoryById(Long merchantId){
		List<GoodsCategory> list = goodsCategoryDao.findGoodsCategory(merchantId);
		return list;
	}
	
	/**
	 * 验证是否可以存入
	 * @param merchantId
	 * @param goodsCategory
	 * @return
	 */
	public String verifyAdd(Long merchantId,GoodsCategory goodsCategory){
		if(goodsCategoryDao.existsCategory(merchantId, goodsCategory.getCategoryName())){
			return "分类已存在";
		}
		if(goodsCategory.getSort()==null){
			return "权重不能为空";
		}
		if(StringUtils.isEmpty(goodsCategory.getCategoryName())){
			return "分类名称不能为空";
		}
		return null;
	}
	
	
	/**
	 * 保存商品分类信息
	 * @param goodsCategory
	 */
	public Boolean saveCategory(GoodsCategory goodsCategory){
		return goodsCategoryDao.addGoodsCategory(goodsCategory)!=null;
	}
	
	/**
	 * 获取一个实例
	 * @param categoryId
	 * @return
	 */
	public GoodsCategory getOne(Long categoryId){
		return goodsCategoryDao.getOne(categoryId);
	}
	
	/**
	 * 验证是否可以修改
	 * @param merchantId
	 * @param goodsCategory
	 * @return
	 */
	public String verifyUpdate(GoodsCategory goodsCategory){
		if(goodsCategory.getId()==null){
			return "id为空，请重新跳转页面";
		}
		if(goodsCategory.getMerchantId()==null){
			return "商户id为空，请重新跳转页面";
		}
		if(goodsCategory.getSort()==null){
			return "权重不能为空";
		}
		if(StringUtils.isEmpty(goodsCategory.getCategoryName())){
			return "分类名称不能为空";
		}
		return null;
	}
	
	
	/**
	 * 修改分类信息
	 * @param goodsCategory
	 */
	public void updateCategory(GoodsCategory goodsCategory){
		goodsCategoryDao.updateGoodsCategory(goodsCategory);
	}

	/**
	 * 根据id删除分类
	 * @param id
	 */
	public void deleteCategory(Long id){
		GoodsCategory goodsCategory = new GoodsCategory();
		goodsCategory.setId(id);
		goodsCategoryDao.removeGoodsCategory(goodsCategory);
		goodsDao.removeGoodsByCategoryId(id);
	}
	
	/**
	 * 根据id查询分类列表
	 * @param ids
	 * @return
	 */
	public List<GoodsCategory> queryGoodsCategory(List<Long> ids){
		return goodsCategoryDao.findGoodsCategoryByIds(ids);
	}
	
	/**
	 * 根据分类id获取商户id
	 * @param categoryId
	 * @return
	 */
	public Long queryMerchantIdByCategoryId(Long categoryId){
		GoodsCategory category = goodsCategoryDao.getOne(categoryId);
		if(category==null){
			return null;
		}
		return category.getMerchantId();
	}
	
}
