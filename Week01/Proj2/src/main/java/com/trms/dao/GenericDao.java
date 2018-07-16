package com.trms.dao;

import java.util.List;
import java.util.function.Function;

public interface GenericDao<T> {

	List<T> select(String sql, Function<Object[], T> f);

	Boolean callProcedure(T t, String sql, Function<T, List<Object>> f);

	Boolean delete(String tableName, String idFieldName, Object id);

}
