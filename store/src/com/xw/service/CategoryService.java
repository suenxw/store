package com.xw.service;

import java.util.List;

import com.xw.domain.Category;

public interface CategoryService {

	String findAll() throws Exception;
	
	/*String findAllFromRedis() throws Exception;*/

	List<Category> findList() throws Exception;

	void save(Category c) throws Exception;

	void delete(Category c) throws Exception;

	

}
