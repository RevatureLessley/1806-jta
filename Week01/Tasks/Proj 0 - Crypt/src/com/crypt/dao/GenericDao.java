package com.crypt.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDao<T, K> {
	public List<T> selectAll() throws SQLException;
	public void insert(T t);
	public T selectById(K id);
	public Integer deleteById(K id);
	public Integer updateById(K id);
	public boolean insertViaSp(T t);
}
