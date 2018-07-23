package com.trms.dao;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.trms.beans.GradeFormat;
import com.trms.util.SqlBuilder;

public class GradeFormatDaoImpl extends GenericDaoImpl<GradeFormat> implements Dao<GradeFormat, Integer> {
	final String tName = "GradeFormat", idField = "formatID";
	final Integer col = 1;
	SqlBuilder<Integer> sb = new SqlBuilder<>();
	
	Function<Object[], GradeFormat> objectReturnBehaviour =
			item -> new GradeFormat(
					Integer.parseInt(item[0].toString()),
					Integer.parseInt(item[1].toString())
					);

	@Override
	public List<GradeFormat> selectAll() {
		return select(sb.sAll(tName), objectReturnBehaviour);
	}

	@Override
	public GradeFormat selectByID(Integer id) {
		return select(sb.sWhere(tName, idField, id), objectReturnBehaviour).get(0);
	}

	Function<GradeFormat, List<Object>> loadingBehaviour =
			item -> Arrays.asList(
					item.getID(),
					item.getPassingGrade()
					);
	
	@Override
	public Boolean insertNew(GradeFormat t) {
		return callProcedure(t, sb.callInsert(tName, col), loadingBehaviour);
	}

	@Override
	public Boolean update(GradeFormat t) {
		return callProcedure(t, sb.callUpdate(tName, t.getID(), col), loadingBehaviour);
	}

	@Override
	public void udpateByGroup(List<GradeFormat> ts) {
		ts.forEach(item -> update(item));
	}

	@Override
	public Boolean deleteByID(Integer id) {
		return delete(tName, idField, id);
	}

}
