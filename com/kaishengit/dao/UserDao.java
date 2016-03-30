package com.kaishengit.dao;


import org.apache.commons.dbutils.handlers.BeanHandler;

import com.kaishengit.entity.User;
import com.kaishengit.util.DBHelp;

public class UserDao {

	public User findByUsername(String username) {
		
		String sql = "select * from t_user where username = ?";
		return DBHelp.query(sql, new BeanHandler<User>(User.class), username);
	}
	
	
}
