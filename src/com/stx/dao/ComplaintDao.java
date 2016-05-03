package com.stx.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.admin.Complaint;
import com.stx.util.CollectionUtils;

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
	public Long saveComplaint(Complaint complaint){
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(complaint);
	}
	
	/**
	 * 时间倒序查询投诉信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Complaint>  listComplaintTimeDesc(Date startDate, Date endDate){
		Session session = sessionFactory.getCurrentSession();
		StringBuilder sb = new StringBuilder("FROM Complaint WHERE 1=1");
		if(startDate != null){
			sb.append(" AND createTime > '" + startDate + "'");
		}
		if(endDate != null){
			sb.append(" AND createTime < '" + endDate + "'");
		}
		sb.append(" order by createTime desc");
		List<Complaint> complaints = (List<Complaint>) session.createQuery(sb.toString()).setFirstResult(0).setMaxResults(30).list();
		if(CollectionUtils.isNotEmpty(complaints)){
			return complaints;
		}
		return null;
	}
	
}
