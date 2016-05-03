package com.stx.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.merchant.Strategy;
import com.stx.util.CollectionUtils;

/**
 * 操作优惠策略
 * @author gzzdsg
 * 2016年3月13日
 */
@Repository
public class StrategyDao extends BaseDao{

	/**
	 * 查询该商户的某类型的优惠
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Strategy> findStrategyByMerchantAndType( Long merchantId, Integer strategyType) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Strategy WHERE merchantId = :merchantId AND strategyType = :strategyType ORDER BY premisePrice DESC";
		List<Strategy> list = (List<Strategy>) session.createQuery(hql)
				.setParameter("merchantId", merchantId)
				.setParameter("strategyType", strategyType).list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 查询该商户的某类型的优惠
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Strategy> findHotStrategye() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Strategy ORDER BY balancePrice DESC";
		List<Strategy> list = (List<Strategy>) session.createQuery(hql).setMaxResults(6).list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 保存一个商户优惠策略
	 * @param strategy
	 * @return
	 */
	@Transactional
	public Long saveStrategy(Strategy strategy){
		Session session = sessionFactory.getCurrentSession();
		Serializable id = session.save(strategy);
		if(id==null){
			return null;
		}
		return (Long)id;
	}
	
	/**
	 * 删除一项策略
	 * @param strategy
	 */
	@Transactional
	public void removeStrategy(Strategy strategy){
		Session session = sessionFactory.getCurrentSession();
		session.delete(strategy);
	}
	
	/**
	 * 根据一项策略的id查找策略的详细信息
	 * @param strategyId
	 * @return
	 */
	@Transactional
	@SuppressWarnings("unchecked")
	public Strategy getEntity(Long strategyId){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Strategy WHERE id = :id";
		List<Strategy> list = 
				(List<Strategy>) session.createQuery(hql)
				.setParameter("id", strategyId).list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}

	/**
	 * 修改某项策略
	 * @param strategy
	 */
	@Transactional
	public void updateStrategy(Strategy strategy){
		Session session = sessionFactory.getCurrentSession();
		session.update(strategy);
	}
	
}
