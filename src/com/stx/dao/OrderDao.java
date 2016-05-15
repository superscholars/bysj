package com.stx.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.User;
import com.stx.entity.order.Order;
import com.stx.util.CollectionUtils;
import com.stx.util.StringUtils;

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
	
	/**
	 * 管理员查看订单列表
	 * @param startDate
	 * @param endDate
	 * @param searchType
	 * @param searchValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Order> adminSearch(Date startDate, Date endDate , String searchType ,String searchValue){
		Session session = sessionFactory.getCurrentSession();
		StringBuilder sb = new StringBuilder("FROM Order WHERE 1=1");
		if(StringUtils.isNotEmpty(searchValue)&&!"0".equals(searchType)){
			switch(searchType){
			case "1":
				sb.append(" AND orderNumber = '" + searchValue + "'");
				List<Order> list = (List<Order>) session.createQuery(sb.toString()).setFirstResult(0).setMaxResults(50).list();
				if (CollectionUtils.isNotEmpty(list)) {
					return list;
				} else {
					return null;
				}
				
			case "2":
				sb.append(" AND userAddr like '%" + searchValue +"%'");
				break;
				
			case "3":
				sb.append(" AND merchantName = '" + searchValue + "'");
				break;
				
			case "4":
				String userHql = "FROM User WHERE loginName = :loginName";
				List<User> users = (List<User>) session.createQuery(userHql).setParameter("loginName", searchValue).list();
				if(CollectionUtils.isEmpty(users)){
					return null;
				}
				sb.append(" AND userId = " + users.get(0).getId());
				break;
				
			}
		}
		if(startDate != null){
			sb.append(" AND createTime >= " +startDate);
		}
		if(endDate != null){
			sb.append(" AND createTime <= " + endDate);
		}
		
		sb.append(" ORDER BY id DESC");
		
		List<Order> orderList = (List<Order>) session.createQuery(sb.toString()).list();
		if(CollectionUtils.isEmpty(orderList)){
			return null;
		}else{
			return orderList;
		}
	}
	
}
