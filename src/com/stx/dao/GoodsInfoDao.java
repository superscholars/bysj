package com.stx.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.order.GoodsInfo;
import com.stx.util.CollectionUtils;

/**
 * 订单商品信息操作
 * @author gzzdsg
 * 2016年3月24日
 */
@Repository
public class GoodsInfoDao extends BaseDao{

	/**
	 * 保存一条订单商品信息
	 * @param order
	 * @return
	 */
	@Transactional
	public Long saveGoodsInfo(GoodsInfo goodsInfo){
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(goodsInfo);
	}
	
	/**
	 * 查询订单商品信息
	 * @param orderId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<GoodsInfo> findGoodsInfoByOid(Long orderId){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM GoodsInfo WHERE orderId = :orderId";
		List<GoodsInfo> list = (List<GoodsInfo>) session.createQuery(hql)
				.setParameter("orderId", orderId)
				.list();
		if (CollectionUtils.isNotEmpty(list)) {
			return list;
		} else {
			return null;
		}
	}
	
}
