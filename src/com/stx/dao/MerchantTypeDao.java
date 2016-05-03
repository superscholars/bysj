package com.stx.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.merchant.MerchantType;
/**
 * 操作商户类型
 * @author gzzdsg
 * 2016年3月12日
 */
@Repository
public class MerchantTypeDao extends BaseDao{
	
	/**
	 * 获取所有商户类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<MerchantType> findMerchantType() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM MerchantType";
		List<MerchantType> list = (List<MerchantType>) session.createQuery(hql).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 获取前三个商户类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<MerchantType> find3MerchantType() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM MerchantType order by id";
		List<MerchantType> list = (List<MerchantType>) session.createQuery(hql).setFirstResult(0).setMaxResults(3).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 保存商户类型
	 * @param merchantType
	 * @return
	 */
	@Transactional
	public Long saveMerchantType(MerchantType merchantType){
		Session session = sessionFactory.getCurrentSession();
		return (Long)session.save(merchantType);
	}
}
