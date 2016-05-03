package com.stx.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.order.Order;
import com.stx.entity.order.OrderProcess;
import com.stx.util.CollectionUtils;

/**
 * 订单状态记录
 * @author gzzdsg
 * 2016年3月24日
 */
@Repository
public class OrderProcessDao extends BaseDao{

	
	/**
	 * 根据用户id查找订单流程
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<OrderProcess> findProcessByOid( Long orderId ) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM OrderProcess WHERE orderId = :orderId ORDER BY processTime";
		List<OrderProcess> list = (List<OrderProcess>) session.createQuery(hql)
				.setParameter("orderId", orderId)
				.list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 保存一条订单流程信息
	 * @param orderProcess
	 * @return
	 */
	@Transactional
	public Long saveOrderProcess(OrderProcess orderProcess){
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(orderProcess);
	}
	
}
