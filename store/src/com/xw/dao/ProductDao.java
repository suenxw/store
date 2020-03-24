package com.xw.dao;

import java.util.List;

import com.xw.domain.PageBean;
import com.xw.domain.Product;

public interface ProductDao {

	List<Product> findHot() throws Exception;

	List<Product> findNew() throws Exception;

	Product getById(String pid) throws Exception;

	List<Product> findByPage(PageBean<Product> pb, String cid) throws Exception;

	int getTotalRecord(String cid) throws Exception;

	List<Product> findAll() throws Exception;

	void save(Product p) throws Exception;

	void delete(Product p) throws Exception;

}
