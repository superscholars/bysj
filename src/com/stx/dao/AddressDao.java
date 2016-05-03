package com.stx.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.user.Address;

/**
 * 操作地址
 * @author gzzdsg
 * 2016年3月23日
 */
@Repository
public class AddressDao extends BaseDao{

	/**
	 * 查询用户的所有地址
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Address> findAddressesByUserId(Long userId ) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Address WHERE userId = :userId ORDER BY status";
		List<Address> list = (List<Address>) session.createQuery(
				hql).setParameter("userId", userId).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
	/**
	 * 保存一个地址
	 * @param address
	 * @return
	 */
	@Transactional
	public Long saveAddress(Address address){
		Session session = sessionFactory.getCurrentSession();
		return (Long) session.save(address);
	}
	
	/**
	 * 根据id查询一个地址
	 * @param addressId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Address getEntity(Long addressId ) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM Address WHERE id = :addressId ";
		List<Address> list = (List<Address>) session.createQuery(
				hql).setParameter("addressId", addressId).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 删除地址
	 * @param address
	 */
	@Transactional
	public void removeAddress(Address address){
		Session session = sessionFactory.getCurrentSession();
		session.delete(address);
	}
	
	/**
	 * 修改地址
	 * @param address
	 */
	@Transactional
	public void updateAddress(Address address){
		Session session = sessionFactory.getCurrentSession();
		session.update(address);
	}
	
}
