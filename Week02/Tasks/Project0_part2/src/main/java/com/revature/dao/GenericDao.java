package com.revature.dao;

import java.util.List;

public interface GenericDao<T> {

	/**
	 * Inserts a bean of type T into the database
	 * 
	 * @param t
	 */
	public void insert(T t);

	/**
	 * Obtains a bean of type T from the database
	 * 
	 * @param t
	 */
	public T selectById(Integer id);

	/**
	 * Creates list which contains all beans of type T in the database
	 * 
	 * @param t
	 */
	public List<T> selectAll();

	/**
	 * Deletes a row from the database which has the given id as a pk
	 * 
	 * @param id
	 * @return
	 */
	public Integer deleteById(Integer id);

	/**
	 * Updates a row in the database using data from a given bean.
	 * 
	 * @param id
	 * @return
	 */
	public Integer update(T t);
}
