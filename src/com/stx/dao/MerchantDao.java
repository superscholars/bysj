package com.stx.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.merchant.Merchant;
import com.stx.util.CollectionUtils;

/**
 * 操作商户信息
 * 
 * @author gzzdsg
 * 2016年3月10日
 */
@Repository
public class MerchantDao extends BaseDao{

	/**
	 * 寻找商铺资料
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Merchant findMerchant(Long userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Merchant WHERE userId = :userId ";
		List<Merchant> list = (List<Merchant>) session.createQuery(hql)
				.setParameter("userId", userId).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 寻找全部商铺资料倒序
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Merchant> findAllMerchant() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Merchant order by id ";
		List<Merchant> list = (List<Merchant>) session.createQuery(hql).list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 根据商户名称查找
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Merchant> findMerchantByName(String merchantName) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Merchant WHERE name = :merchantName order by id ";
		List<Merchant> list = (List<Merchant>) session.createQuery(hql).setParameter("merchantName", merchantName).list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 管理员根据商铺类型寻找商铺
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Merchant> adminFindMerchantByType(String merchantType) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Merchant WHERE merchantType = :merchantType";
		List<Merchant> list = (List<Merchant>) session.createQuery(hql)
				.setParameter("merchantType", merchantType).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 根据商户id查找商户
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Merchant findMerchantByMerchantId(Long merchantId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Merchant WHERE id = :merchantId ";
		List<Merchant> list = (List<Merchant>) session.createQuery(hql)
				.setParameter("merchantId", merchantId).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 修改信息
	 * 
	 * @param merchant
	 */
	@Transactional
	public void updateMerchantInfo(Merchant merchant){
		Session session = sessionFactory.getCurrentSession();
		session.update(merchant);
		return ;
	}
	
	/**
	 * 根据id查询所有商户
	 * 
	 * @param userIds
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Merchant> findMerchantsByIds(List<Long> ids) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Merchant WHERE id in (:ids) AND openFlag = 1";
		List<Merchant> list = (List<Merchant>) session.createQuery(hql)
				.setParameterList("ids", ids).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 保存一个商户信息
	 * @param merchant
	 * @return
	 */
	@Transactional
	public Long saveMerchant(Merchant merchant){
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(merchant);
	}
	
	/**
	 * 查询单量多的商户
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Merchant> findHotMerchants() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Merchant WHERE openFlag = 1 ORDER BY monthCount DESC ";
		List<Merchant> list = (List<Merchant>) session.createQuery(hql).setMaxResults(6).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	
	/**
	 * 根据商铺类型寻找商铺
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Merchant> findMerchantByType(String merchantType) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Merchant WHERE merchantType = :merchantType AND openFlag = 1";
		List<Merchant> list = (List<Merchant>) session.createQuery(hql)
				.setParameter("merchantType", merchantType).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 根据商铺类型寻找3个商铺
	 * 
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Merchant> find3MerchantByType(String merchantType) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Merchant WHERE merchantType = :merchantType AND openFlag = 1";
		List<Merchant> list = (List<Merchant>) session.createQuery(hql)
				.setParameter("merchantType", merchantType).setMaxResults(3).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
}
