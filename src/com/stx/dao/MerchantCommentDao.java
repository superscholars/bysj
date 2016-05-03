package com.stx.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.user.MerchantComment;

/**
 * 商户评论操作
 * @author gzzdsg
 * 2016年3月22日
 */
@Repository
public class MerchantCommentDao extends BaseDao{

	/**
	 * 查询商户所有评论
	 * @param userId
	 * @param merchantId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<MerchantComment> findCommentByMerchantId(Long merchantId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM MerchantComment WHERE merchantId = :merchantId order by id desc";
		List<MerchantComment> list = (List<MerchantComment>) session.createQuery(
				hql).setParameter("merchantId", merchantId).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 保存一条评论
	 * @param comment
	 * @return
	 */
	@Transactional
	public Long saveComment(MerchantComment comment){
		Session session = sessionFactory.getCurrentSession();
		return (Long)session.save(comment);
	}
	
}
