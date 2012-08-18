package org.dp80.jpa.ex1.dao;

import java.util.List;


public interface BaseDao<T> {
	
	Class<T> getEntityClass();
	
	void save(T entity);
	
	T get(Long id);
	
	List<T> getAll();
	
	
}
