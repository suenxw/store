package com.xw.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xw.constant.Constant;
import com.xw.domain.Cart;
import com.xw.domain.CartItem;
import com.xw.domain.Order;
import com.xw.domain.OrderItem;
import com.xw.domain.PageBean;
import com.xw.domain.User;
import com.xw.service.OrderService;
import com.xw.utils.BeanFactory;
import com.xw.utils.UUIDUtils;
import com.xw.web.servlet.base.BaseServlet;

/**
 * 订单模块
 */
public class OrderServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * 获取订单详情
	 */
	public String getById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取oid
			String oid = request.getParameter("oid");
			
			//2.调用service 查询单个订单 
			OrderService os = (OrderService) BeanFactory.getBean("OrderService");
			Order order = os.getById(oid);
			
			//3.请求转发
			request.setAttribute("bean",order);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "查询订单详情失败");
			return "/jsp/msg.jsp";
		}
		return "/jsp/order_info.jsp";
	}
	
	/**
	 * 我的订单
	 */
	public String findMyOrdersByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//1.获取pageNumber 设置pagesize 获取userid
			int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
			int pageSize=3;
			
			User user=(User)request.getSession().getAttribute("user");
			if(user == null){
				//未登录 提示
				request.setAttribute("msg", "请先登录");
				return "/jsp/msg.jsp";
			}
			
			//2.调用service获取当前页所有数据  pagebean
			OrderService os = (OrderService) BeanFactory.getBean("OrderService");
			PageBean<Order> bean = os.findMyOrdersByPage(pageNumber,pageSize,user.getUid());
			
			//3.将pagebean放入request域中,请求转发 order_list.jsp
			request.setAttribute("pb", bean);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "获取我的订单失败");
			return "/jsp/msg.jsp";
		}
		return "/jsp/order_list.jsp";
	}
	/**
	 * 保存订单
	 */
	public String save(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			//-1.从session中获取user
			User user=(User) request.getSession().getAttribute("user");
			if(user == null){
				//未登录 提示
				request.setAttribute("msg", "请先登录!");
				return "/jsp/msg.jsp";
			}
			
			//0.获取购物车
			Cart cart=(Cart) request.getSession().getAttribute("cart");
			
			//1.封装订单对象
			//1.1创建对象
			Order order = new Order();
			
			//1.2设置oid 
			order.setOid(UUIDUtils.getId());
			
			//1.3设置ordertime
			order.setOrdertime(new Date());
			
			//1.4设置total 购物车中
			order.setTotal(cart.getTotal());
			
			//1.5设置state
			order.setState(Constant.ORDER_WEIFUKUAN);
			
			//1.6设置user
			order.setUser(user);
			
			//1.7设置items(订单项列表) 遍历购物项列表
			for (CartItem	ci : cart.getCartItems()) {
				//1.7.1封装成orderitem
				//a.创建orderitem
				OrderItem oi = new OrderItem();
				
				//b.设置itemid uuid
				oi.setItemid(UUIDUtils.getId());
				
				//c.设置count 从ci中获取
				oi.setCount(ci.getCount());
				
				//d.设置subtotal 从ci中获取
				oi.setSubtotal(ci.getSubtotal());
				
				//e.设置product 从ci中获取
				oi.setProduct(ci.getProduct());
				
				//f.设置order 
				oi.setOrder(order);
				
				//1.7.2 将orderitem加入order 的items中
				order.getItems().add(oi);
			}
			
			
			//2.调用orderservice完成保存操作
			OrderService os = (OrderService) BeanFactory.getBean("OrderService");
			os.save(order);
			
			//2.9清空购物车
			cart.clearCart();
			
			//3.请求转发到 order_info.jsp
			request.setAttribute("bean", order);
		} catch (Exception e) {
		}
		return "/jsp/order_info.jsp";
	}

	/**
	 * 在线支付
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public String pay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取收获信息 获取oid 获取银行
		
		//2.调用service获取订单 修改收获人信息  更新订单
		
		//3.拼接给第三方的url
		
		//4.重定向
		
		
		try {
			//接受参数
			String address=request.getParameter("address");
			String name=request.getParameter("name");
			String telephone=request.getParameter("telephone");
			String oid=request.getParameter("oid");
			
			
			//通过id获取order
			OrderService s=(OrderService) BeanFactory.getBean("OrderService");
			Order order = s.getById(oid);
			
			order.setAddress(address);
			order.setName(name);
			order.setTelephone(telephone);
			order.setState(Constant.ORDER_YIFUKUAN);
			//更新order
			s.update(order);
			
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "支付失败");
			return "/jsp/msg.jsp";
		}
		
		request.setAttribute("msg", "支付成功");
		return "/jsp/msg.jsp";
	}
	
}
