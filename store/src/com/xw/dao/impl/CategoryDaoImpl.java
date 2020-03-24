package com.xw.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.xw.dao.CategoryDao;
import com.xw.domain.Category;
import com.xw.utils.DataSourceUtils;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	/**
	 * 查询所有分类
	 */
	public List<Category> findAll() throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from category";
		return qr.query(sql, new BeanListHandler<>(Category.class));
	}

	@Override
	/**
	 * 添加分类
	 */
	public void save(Category c) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into category values (?,?);";
		qr.update(sql, c.getCid(),c.getCname());
	}

	@Override
	public void delete(Category c) throws Exception {
		QueryRunner qr = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from category where cname = ?;";
		qr.update(sql,c.getCname());
	}

}
