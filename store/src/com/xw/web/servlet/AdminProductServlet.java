package com.xw.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xw.domain.Category;
import com.xw.domain.Product;
import com.xw.service.CategoryService;
import com.xw.service.ProductService;
import com.xw.utils.BeanFactory;
import com.xw.web.servlet.base.BaseServlet;

/**
 * Servlet implementation class AdminProductServlet
 */
public class AdminProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public String findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.调用service 查询以上架商品
			ProductService ps = (ProductService) BeanFactory.getBean("ProductService");
			List<Product> list = ps.findAll();
			
			//2.将返回值放入request中,请求转发
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		return "/admin/product/list.jsp";
	}

	/**
	 * 跳转到添加的页面上
	 */
	public String addUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			//调用categoryservice 查询所有分类
			CategoryService cs = (CategoryService) BeanFactory.getBean("CategoryService");
			
			request.setAttribute("list", cs.findList());
		} catch (Exception e) {
		}
		return "/admin/product/add.jsp";
	}
	
	/**
	 * 删除商品
	 */
	public String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.封装category对象
			Product p = new Product();
//			c.setCname(request.getParameter("cname"));
			request.setCharacterEncoding("UTF-8");
			System.out.println(request.getParameter("pid"));
			
			p.setPid(request.getParameter("pid"));

			
			//2.调用service完成添加操作
			ProductService ps = (ProductService) BeanFactory.getBean("ProductService");
			ps.delete(p);
			
			//3.重定向
			response.sendRedirect(request.getContextPath()+"/adminProduct?method=findAll");
			
			
			return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
