package com.stx.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.admin.Punish;

/**
 * 处罚表
 * @author gzzdsg
 */
@Repository
public class PunishDao extends BaseDao{
	
	/**
	 * 查询一条处罚记录
	 * @param merchantId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Punish getPunish(Long merchantId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Punish WHERE merchantId = :merchantId ORDER BY id DESC";
		List<Punish> list = (List<Punish>) session.createQuery(hql)
				.setParameter("merchantId", merchantId).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 保存punish信息
	 * @param punish
	 * @return
	 */
	@Transactional
	public Long savePunish(Punish punish){
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(punish);
	}

}
