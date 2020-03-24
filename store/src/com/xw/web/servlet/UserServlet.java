package com.xw.web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.xw.constant.Constant;
import com.xw.dao.impl.UserDaoImpl;
import com.xw.domain.User;
import com.xw.service.UserService;
import com.xw.service.impl.UserServiceImpl;
import com.xw.utils.UUIDUtils;
import com.xw.web.servlet.base.BaseServlet;

/**
 * 用户模块
 */
public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 退出
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();//清空session
		
		response.sendRedirect(request.getContextPath());
		return null;
	}
	
	
	
	/**
	 * 用户登录
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取用户名和密码
			String username = request.getParameter("username");
			String password	= request.getParameter("password");
			
			//2.调用service完成登录 返回值:user
			UserService us = new UserServiceImpl();
			User user=us.login(username,password);
			
			//3.判断user 根据结果生成提示
			if(user == null){
				//用户名和密码不匹配
				request.setAttribute("msg", "用户名和密码不匹配");;
				return "/jsp/login.jsp";
			}
			
			//登录成功 保存用户登录状态
			request.getSession().setAttribute("user", user);
			
			//跳转到 index.jsp
			response.sendRedirect(request.getContextPath());
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "用户登录失败");
			return "/jsp/msg.jsp";
		}
		
		return null;
	}
	
	/**
	 * 跳转到登录页面
	 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/login.jsp";
	}
	
	
	/**
	 * 跳转至注册页面
	 */
	public String registUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		return "/jsp/register.jsp";
		
	}
	
	/**
	 * 用户注册
	 */
	public String regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//1.封装对象
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());
			
			//数据库中有些字段是没有的
			//1.1手动封装uid
			user.setUid(UUIDUtils.getId());
			
			/*//1.2手动封装激活状态 state
			user.setState(Constant.USER_IS_ACTIVE);
			*/
			
			//2.调用service完成注册
			UserService us=new UserServiceImpl();
			us.regist(user);
			
			//3.页面转发 提示信息
			request.setAttribute("msg", "恭喜你,注册成功！");
		}catch (Exception e) {
			e.printStackTrace();
			//转发到 msg.jsp
			request.setAttribute("msg", "用户注册失败!");
			return "/jsp/msg.jsp";
		}
		
		return "/jsp/msg.jsp";
	}


	/**
	 * 检查是否重名
	 */
	public void check(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {

			PrintWriter out = res.getWriter();
			String username = req.getParameter("username");

			UserDaoImpl userDaoImpl = new UserDaoImpl();
			if(username!=null) {
				if(userDaoImpl.getUserByName(username)!=null)
					out.print(-1);
			} else out.print(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
