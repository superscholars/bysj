package com.stx.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.order.Order;
import com.stx.util.CollectionUtils;

/**
 * 操作订单表
 * @author gzzdsg
 * 2016年3月14日
 */
@Repository
public class OrderDao extends BaseDao{

	/**
	 * 按照商户和订单状态查询订单
	 * @param merchantId
	 * @param statusS
	 * @param statusE
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Order> findOrdersByStatus( Long merchantId ,Integer statusS, Integer statusE) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Order as o WHERE o.merchantId = :merchantId AND o.status > :statusS AND o.status <= :statusE ORDER BY o.createTime";
		List<Order> list = (List<Order>) session.createQuery(hql)
				.setParameter("merchantId", merchantId)
				.setParameter("statusS", statusS)
				.setParameter("statusE", statusE)
				.list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 根据用户id查找订单
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Order> findOrdersByUid( Long userId ) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Order as o WHERE o.userId = :userId AND o.status > 0 ORDER BY o.createTime DESC";
		List<Order> list = (List<Order>) session.createQuery(hql)
				.setParameter("userId", userId)
				.list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 根据订单id查询订单信息
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Order findOrdersById( Long orderId ) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Order as o WHERE o.id = :orderId ";
		List<Order> list = (List<Order>) session.createQuery(hql)
				.setParameter("orderId", orderId)
				.list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	
	/**
	 * 保存一条订单信息
	 * @param order
	 * @return
	 */
	@Transactional
	public Long saveOrder(Order order){
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(order);
	}
	
	/**
	 * 保存一条订单信息
	 * @param order
	 * @return
	 */
	@Transactional
	public void updateOrder(Order order){
		Session session = sessionFactory.getCurrentSession();
		session.update(order);
	}
	
	/**
	 * 判断是否为新用户
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Boolean isNewUser( Long userId ) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Order WHERE userId = :userId ";
		List<Order> list = (List<Order>) session.createQuery(hql)
				.setParameter("userId", userId)
				.list();
		if (CollectionUtils.isNotEmpty(list)) {
			return false;
		} else {
			return true;
		}
	}
	
}
