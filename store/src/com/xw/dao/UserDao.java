package com.xw.dao;

import java.sql.SQLException;

import com.xw.domain.User;

public interface UserDao {

	void save(User user) throws Exception;
	
	void update(User user) throws Exception;

	User getByUsernameAndPwd(String username, String password) throws Exception;

}
