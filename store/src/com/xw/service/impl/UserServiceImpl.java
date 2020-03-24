package com.xw.service.impl;

import com.xw.dao.UserDao;
import com.xw.dao.impl.UserDaoImpl;
import com.xw.domain.User;
import com.xw.service.UserService;
import com.xw.utils.BeanFactory;

public class UserServiceImpl implements UserService {

	@Override
	/*
	 *用户注册
	 */
	public void regist(User user) throws Exception {
		//调用dao完成注册
//		UserDao ud=(UserDao) BeanFactory.getBean("UserDao");
		UserDao ud = (UserDao) BeanFactory.getBean("UserDao");
		ud.save(user);

	}

	@Override
	/**
	 * 用户登录
	 */
	public User login(String username, String password) throws Exception {
		UserDao ud=(UserDao) BeanFactory.getBean("UserDao");
		
		return ud.getByUsernameAndPwd(username,password);
	}

}
