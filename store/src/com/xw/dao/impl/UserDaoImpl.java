package com.xw.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.xw.dao.UserDao;
import com.xw.domain.User;
import com.xw.utils.DataSourceUtils;

public class UserDaoImpl implements UserDao{

	@Override
	/*
	 *用户注册
	 */
	public void save(User user) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		/*
		 *  `uid` varchar(32) NOT NULL,
			  `username` varchar(20) DEFAULT NULL,
			  `password` varchar(20) DEFAULT NULL,
			  
			  `name` varchar(20) DEFAULT NULL,
			  `email` varchar(30) DEFAULT NULL,
			  `telephone` varchar(20) DEFAULT NULL,
			  
			  `birthday` date DEFAULT NULL,
			  `sex` varchar(10) DEFAULT NULL,
			  `state` int(11) DEFAULT NULL,
			  
			  `code` varchar(64) DEFAULT NULL,
		 */
		String sql = "insert into user values(?,?,?,?,?,?,?,?);";
		qr.update(sql, user.getUid(),user.getUsername(),user.getPassword(),
				user.getName(),user.getEmail(),user.getTelephone(),
				user.getBirthday(),user.getSex());
	}

	@Override
	/**
	 * 更新用户
	 */
	public void update(User user) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		/*
		 *  `uid` varchar(32) NOT NULL,
			  `username` varchar(20) DEFAULT NULL,
			  `password` varchar(20) DEFAULT NULL,
			  
			  `name` varchar(20) DEFAULT NULL,
			  `email` varchar(30) DEFAULT NULL,
			  `telephone` varchar(20) DEFAULT NULL,
			  
			  `birthday` date DEFAULT NULL,
			  `sex` varchar(10) DEFAULT NULL,
			  `state` int(11) DEFAULT NULL,
			  
			  `code` varchar(64) DEFAULT NULL,
		 */
		String sql="update user set password = ?,sex = ?, where uid = ?";
		qr.update(sql, user.getPassword(),user.getSex(),user.getUid());
	}

	@Override
	/**
	 * 用户登录
	 */
	public User getByUsernameAndPwd(String username, String password) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ? limit 1";
		return qr.query(sql, new BeanHandler<>(User.class), username,password);
	}

	public User getUserByName(String username) throws SQLException {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from user where username = ?";
		return qr.query(sql, new BeanHandler<>(User.class), username);
	}
}
