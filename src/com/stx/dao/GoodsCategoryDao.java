package com.stx.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.merchant.GoodsCategory;

/**
 * 操作商品分类
 * 
 * @author gzzdsg 2016年3月12日
 */
@Repository
public class GoodsCategoryDao extends BaseDao {

	/**
	 * 查询商户的所有分类
	 * @param merchantId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<GoodsCategory> findGoodsCategory(Long merchantId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM GoodsCategory WHERE merchantId = :merchantId ORDER BY sort";
		List<GoodsCategory> list = (List<GoodsCategory>) session.createQuery(
				hql).setParameter("merchantId", merchantId).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return new ArrayList<GoodsCategory>();
		}
	}
	
	/**
	 * 查询该商户是否存在这个分类
	 * @param merchantId
	 * @param categoryName
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Boolean existsCategory(Long merchantId,String categoryName) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM GoodsCategory WHERE merchantId = :merchantId AND categoryName = :categoryName ORDER BY sort";
		List<GoodsCategory> list = (List<GoodsCategory>) session.createQuery(
				hql).setParameter("merchantId", merchantId)
				.setParameter("categoryName", categoryName).list();
		if (list != null && list.size() > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	/**
	 * 添加分类
	 * @param goodsCategory
	 * @return
	 */
	@Transactional
	public Long addGoodsCategory(GoodsCategory goodsCategory){
		Session session = sessionFactory.getCurrentSession();
		Serializable id = session.save(goodsCategory);
		if(id==null){
			return null;
		}
		return (Long)id;
	}
	
	/**
	 * 删除一个商品分类
	 * @param goodsCategory
	 */
	@Transactional 
	public void removeGoodsCategory(GoodsCategory goodsCategory){
		Session session = sessionFactory.getCurrentSession();
		session.delete(goodsCategory);
	}
	
	/**
	 * 根据id获取一个分类实例
	 * @param categoryId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public GoodsCategory getOne(Long categoryId){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM GoodsCategory WHERE Id = :Id ";
		List<GoodsCategory> list = (List<GoodsCategory>) session.createQuery(
				hql).setParameter("Id", categoryId).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 修改商品分类信息
	 * @param goodsCategory
	 */
	@Transactional
	public void updateGoodsCategory(GoodsCategory goodsCategory){
		Session session = sessionFactory.getCurrentSession();
		session.update(goodsCategory);
	}
	
	
	/**
	 * 根据分类名称id查询所有分类信息
	 * @param merchantId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<GoodsCategory> findGoodsCategoryByIds(List<Long> categorys) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM GoodsCategory WHERE id in (:categorys)";
		List<GoodsCategory> list = (List<GoodsCategory>) session.createQuery(
				hql).setParameterList("categorys", categorys).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return new ArrayList<GoodsCategory>();
		}
	}

}
