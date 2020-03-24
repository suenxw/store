package com.xw.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xw.domain.Product;
import com.xw.service.ProductService;
import com.xw.service.impl.ProductServiceImpl;
import com.xw.web.servlet.base.BaseServlet;

/**
 * 首页模块
 */
public class IndexServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

    @Override
    public String index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			//1.调用productservice查询最新商品 和 热门商品
			ProductService ps = new ProductServiceImpl();
			List<Product> hotList=ps.findHot();
			List<Product> newList=ps.findNew();
			
			//2.将两个list都放入request域中
			request.setAttribute("hList", hotList);
			request.setAttribute("nList", newList);
		} catch (Exception e) {
		}
		
		return "/jsp/index.jsp";
    }
}
