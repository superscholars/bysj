package com.stx.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.merchant.Goods;
import com.stx.util.CollectionUtils;

/**
 * 操作商品
 * @author gzzdsg
 * 2016年3月12日
 */
@Repository
public class GoodsDao extends BaseDao{
	
	/**
	 * 查询该分类下的所有商品
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Goods> findGoods( Long categoryId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Goods WHERE categoryId = :categoryId";
		List<Goods> list = (List<Goods>) session.createQuery(hql)
				.setParameter("categoryId", categoryId).list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 查询该分类下的所有商品
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Goods> findHotGoods() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Goods ORDER BY buzz DESC";
		List<Goods> list = (List<Goods>) session.createQuery(hql).setMaxResults(6).list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 删除该分类下的所有商品
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Boolean removeGoodsByCategoryId( Long categoryId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Goods WHERE categoryId = :categoryId";
		List<Goods> list = (List<Goods>) session.createQuery(hql)
				.setParameter("categoryId", categoryId).list();
		if(CollectionUtils.isEmpty(list)){
			return false;
		}
		for(Goods goods : list){
			session.delete(goods);
		}
		return true;
	}
	
	/**
	 * 添加一个商品
	 * @param goods
	 * @return
	 */
	@Transactional
	public Long addGoods(Goods goods){
		Session session = sessionFactory.getCurrentSession();
		Serializable id = session.save(goods);
		return id==null?null:(Long)id;
	}
	
	/**
	 * 删除一个商品
	 * @param goods
	 */
	@Transactional
	public void removeGoods(Goods goods){
		Session session = sessionFactory.getCurrentSession();
		session.delete(goods);
	}
	
	/**
	 * 根据商品id获取一个实例
	 * @param goodsId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Goods getEntiry(Long goodsId){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Goods WHERE id = :goodsId";
		List<Goods> list = (List<Goods>)session.createQuery(hql).setParameter("goodsId", goodsId).list();
		if(CollectionUtils.isEmpty(list)){
			return null;
		}
		return list.get(0);
	}
	
	/**
	 * 修改商品信息
	 * @param goods
	 */
	@Transactional
	public void updateGoods(Goods goods){
		Session session = sessionFactory.getCurrentSession();
		session.update(goods);
	}
	
	/**
	 * 根据商品名称查询分类id
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Goods> findCategoryIdByGoodsName(String goodsName) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Goods WHERE name like :name";
		List<Goods> list = (List<Goods>) session.createQuery(hql)
				.setParameter("name", "%"+goodsName+"%").list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		} else {
			return null;
		}
	}
	
}
