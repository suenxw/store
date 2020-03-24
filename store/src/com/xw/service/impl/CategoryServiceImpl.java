package com.xw.service.impl;

import java.util.List;

import com.xw.constant.Constant;
import com.xw.dao.CategoryDao;
import com.xw.dao.impl.CategoryDaoImpl;
import com.xw.domain.Category;
import com.xw.service.CategoryService;
import com.xw.utils.BeanFactory;
import com.xw.utils.JsonUtil;

import redis.clients.jedis.Jedis;

public class CategoryServiceImpl implements CategoryService {

	@Override
	/**
	 * 查询所有分类
	 */
	public String findAll() throws Exception {
		//1.调用dao 查询所有分类
		CategoryDao cd = new CategoryDaoImpl();
		List<Category> list = cd.findAll();
		
		//2.将list转换成json字符串
		if(list!=null && list.size()>0){
			return JsonUtil.list2json(list);
		}
		return null;
	}

	@Override
	/**
	 * 后台展示所有分类
	 */
	public List<Category> findList() throws Exception {
		CategoryDao cd = (CategoryDao) BeanFactory.getBean("CategoryDao");
		return cd.findAll();
	}
	
	
	@Override
	/**
	 * 添加分类
	 */
	public void save(Category c) throws Exception {
		//1.调用dao 完成添加
		CategoryDao cd = (CategoryDao) BeanFactory.getBean("CategoryDao");
		cd.save(c);
	}
	
	@Override
	/**
	 * 删除分类
	 */
	public void delete(Category c) throws Exception {
		//1.调用dao 完成添加
		CategoryDao cd = (CategoryDao) BeanFactory.getBean("CategoryDao");
		cd.delete(c);
		
	}

}
