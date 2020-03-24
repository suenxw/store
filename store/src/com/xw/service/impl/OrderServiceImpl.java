package com.xw.service.impl;

import java.util.List;

import com.xw.dao.OrderDao;
import com.xw.domain.Order;
import com.xw.domain.OrderItem;
import com.xw.domain.PageBean;
import com.xw.service.OrderService;
import com.xw.utils.BeanFactory;
import com.xw.utils.DataSourceUtils;

public class OrderServiceImpl implements OrderService {

	@Override
	/**
	 * 保存订单
	 */
	public void save(Order order) throws Exception{
		try {
			//获取dao
			OrderDao od  = (OrderDao) BeanFactory.getBean("OrderDao");
			//1.开启事务
			DataSourceUtils.startTransaction();
			
			//2.向orders表中插入一条
			od.save(order);
			
			//3.向orderitem中插入n条
			for (OrderItem oi : order.getItems()) {
				od.saveItem(oi);
			}
			
			//4.事务控制
			DataSourceUtils.commitAndClose();
		} catch (Exception e) {
			e.printStackTrace();
			DataSourceUtils.rollbackAndClose();
			throw e;
		}
	}

	@Override
	/**
	 * 我的订单
	 */
	public PageBean<Order> findMyOrdersByPage(int pageNumber, int pageSize, String uid) throws Exception {
		OrderDao od  = (OrderDao) BeanFactory.getBean("OrderDao");
		
		//1.创建pagebean
		PageBean<Order> pb = new PageBean<>(pageNumber, pageSize);
		
		//2.查询总条数  设置总条数 
		int totalRecord = od.getTotalRecord(uid);
		pb.setTotalRecord(totalRecord);
		
		//3.查询当前页数据 设置当前页数据
		List<Order> data = od.findMyOrdersByPage(pb,uid);
		pb.setData(data);
		return pb;
	}

	@Override
	/**
	 * 订单详情
	 */
	public Order getById(String oid) throws Exception {
		OrderDao od = (OrderDao) BeanFactory.getBean("OrderDao");
		return od.getById(oid);
	}

	@Override
	/**
	 * 修改订单
	 */
	public void update(Order order) throws Exception {
		OrderDao od = (OrderDao) BeanFactory.getBean("OrderDao");
		od.update(order);
		
	}


}
