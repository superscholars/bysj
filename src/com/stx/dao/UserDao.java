package com.stx.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.stx.entity.User;

@Repository
public class UserDao extends BaseDao{

	/**
	 * 查询用户
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public User findUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User WHERE loginName = :loginName AND password = :password";
		List<User> list = (List<User>) session.createQuery(hql)
				.setParameter("loginName", user.getLoginName())
				.setParameter("password", user.getPassword()).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 检查登陆名是否已存在
	 * @param user
	 * @return	是否存在
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Boolean existsUserByLoginName(String loginName){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User WHERE loginName = :loginName";
		List<User> list = (List<User>) session.createQuery(hql)
				.setParameter("loginName", loginName).list();
		if(list !=null && list.size() >0){
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * 检查昵称是否已存在
	 * @param user
	 * @return	是否存在
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Boolean existsUserByNickName(String nickName){
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User WHERE nickName = :nickName";
		List<User> list = (List<User>) session.createQuery(hql)
				.setParameter("nickName", nickName).list();
		if(list !=null && list.size() >0){
			return true;
		} else{
			return false;
		}
	}

	/**
	 * 注册用户
	 * @param user
	 * @return
	 */
	@Transactional
	public Long saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		return (Long)session.save(user);
	}
	
	/**
	 * 修改信息
	 * 
	 * @param user
	 */
	@Transactional
	public void updateUserInfo(User user){
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		return ;
	}

	/**
	 * 根据用户id查询用户
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public User findUserById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User WHERE id = :id ";
		List<User> list = (List<User>) session.createQuery(hql)
				.setParameter("id", id).list();
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}
	
	/**
	 * 根据登陆状态开关查询用户
	 * 
	 * @param loginSwitch
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<User> findUserBySwitch(Integer loginSwitch) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User WHERE loginSwitch = :loginSwitch ";
		List<User> list = (List<User>) session.createQuery(hql)
				.setParameter("loginSwitch", loginSwitch).list();
		if (list != null && list.size() > 0) {
			return list;
		} else {
			return null;
		}
	}
	
}
