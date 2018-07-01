package com.revature.dao;

import java.util.List;

public interface GenericDao<T> {
	
	public void insert(T t);
	public T selectById(Integer id);
	public List<T> selectAll();
	public Integer deleteById(Integer id);
	public Integer update(T t);
}
