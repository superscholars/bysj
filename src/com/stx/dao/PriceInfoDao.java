package com.stx.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.order.Order;
import com.stx.entity.order.PriceInfo;
import com.stx.util.CollectionUtils;

/**
 * 订单费用信息
 * @author gzzdsg
 * 2016年3月24日
 */
@Repository
public class PriceInfoDao extends BaseDao{

	/**
	 * 保存一条订单费用信息
	 * @param priceInfo
	 * @return
	 */
	@Transactional
	public Long savePriceInfo(PriceInfo priceInfo){
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(priceInfo);
	}
	
	/**
	 * 根据订单id查询订单信息
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public PriceInfo findPriceInfoByOid( Long orderId ) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM PriceInfo WHERE orderId = :orderId ";
		List<PriceInfo> list = (List<PriceInfo>) session.createQuery(hql)
				.setParameter("orderId", orderId)
				.list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		} else {
			return null;
		}
	}
}
