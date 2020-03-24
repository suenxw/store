package com.xw.dao;

import java.util.List;

import com.xw.domain.Category;

public interface CategoryDao {

	List<Category> findAll() throws Exception;

	void save(Category c) throws Exception;

	void delete(Category c) throws Exception;

}
