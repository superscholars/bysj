package com.stx.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.admin.Complaint;

/**
 * 投诉信息操作
 * @author gzzdsg
 * 2016年4月10日
 */
@Repository
public class ComplaintDao extends BaseDao{
	
	/**
	 * 添加一条投诉信息
	 * @param collection
	 * @return
	 */
	@Transactional
	public Long saveCollection(Complaint complaint){
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(complaint);
	}
	
}
