package com.stx.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {
	
	@Autowired
	protected SessionFactory sessionFactory;
	
}
