package com.xw.constant;

public interface Constant {
	int USER_IS_NOT_ACTIVE = 0;//常量，用户状态，用户未激活
	int USER_IS_ACTIVE = 1;//常量，用户状态，用户已激活
//	String SAVE_NAME ="ok";//记住用户名
	
	/**
	 * 热门商品
	 */
	int PRODUCT_IS_HOT = 1;
	
	/**
	 * 商品未下架
	 */
	int PRODUCT_IS_UP = 0;
	
	/**
	 * 商品已下架
	 */
	int PRODUCT_IS_DOWN = 1;
	
	
	/**
	 * 订单状态 未付款
	 */
	int ORDER_WEIFUKUAN=0;
	
	/**
	 * 订单状态 已付款
	 */
	int ORDER_YIFUKUAN=1;
	
	/**
	 * 订单状态 已发货
	 */
	int ORDER_YIFAHUO=2;
	
	/**
	 * 订单状态 已完成
	 */
	int ORDER_YIWANCHENG=3;
	
}
