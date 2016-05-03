package com.stx.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.user.Collection;

/**
 * 收藏操作
 * @author gzzdsg
 * 2016年3月22日
 */
@Repository
public class CollectionDao extends BaseDao{

	/**
	 * 查询关系商户
	 * @param userId
	 * @param merchantId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Collection> findCollectionByTwoId(Long userId ,Long merchantId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Collection WHERE userId = :userId AND merchantId = :merchantId";
		List<Collection> list = (List<Collection>) session.createQuery(
				hql).setParameter("userId", userId).setParameter("merchantId", merchantId).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 查询用户的所有收藏
	 * @param userId
	 * @param merchantId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Collection> findCollectionByUserId(Long userId ) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Collection WHERE userId = :userId";
		List<Collection> list = (List<Collection>) session.createQuery(
				hql).setParameter("userId", userId).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 添加一条收藏关系
	 * @param collection
	 * @return
	 */
	@Transactional
	public Long saveCollection(Collection collection){
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(collection);
	}
	
	/**
	 * 
	 * @param collection
	 * @return
	 */
	@Transactional
	public void deleteCollection(Collection collection){
		Session session = sessionFactory.getCurrentSession();
		session.delete(collection);
	}
	
}
