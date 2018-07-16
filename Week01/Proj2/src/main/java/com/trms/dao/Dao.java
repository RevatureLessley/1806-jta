package com.trms.dao;

import java.util.List;

public interface Dao<T, K>{
	public List<T> selectAll();
	public T selectByID(K id);
	public Boolean insertNew(T t);
	public Boolean update(T t);
	public void udpateByGroup(List<T> ts);
	public Boolean deleteByID(K id);
}
