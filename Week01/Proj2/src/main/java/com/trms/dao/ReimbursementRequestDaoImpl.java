package com.trms.dao;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.trms.beans.ReimbursementRequest;
import com.trms.util.SqlBuilder;

public class ReimbursementRequestDaoImpl extends GenericDaoImpl<ReimbursementRequest>
		implements Dao<ReimbursementRequest, Integer> {
	final String tName = "ReimbursementRequest", idField = "requestID";
	final Integer col = 6;
	SqlBuilder<Integer> sb = new SqlBuilder<>();
	
	Function<Object[], ReimbursementRequest> objectReturnBehaviour =
			item -> new ReimbursementRequest(
					(int)item[0], 
					(int)item[1], 
					(int)item[2], 
					(int)item[3], 
					(int)item[4],
					Date.valueOf(item[5].toString()),
					(float)item[6]
					);
	
	@Override
	public List<ReimbursementRequest> selectAll() {
		return select(sb.sAll(tName), objectReturnBehaviour);
	}

	@Override
	public ReimbursementRequest selectByID(Integer id) {
		return select(sb.sWhere(tName, idField, id), objectReturnBehaviour).get(0);
	}

	Function<ReimbursementRequest, List<Object>> loadingBehaviour =
			item -> Arrays.asList(
					item.getRequestID(), 
					item.getRequestorID(), 
					item.getStatusID(), 
					item.getGradeFormatID(), 
					item.getEventID(),
					item.getRequestDate(), 
					item.getCost()
					);

	@Override
	public Boolean insertNew(ReimbursementRequest t) {
		return callProcedure(t, sb.callInsert(tName, col), loadingBehaviour);
	}

	@Override
	public Boolean update(ReimbursementRequest t) {
		return callProcedure(t, sb.callUpdate(tName, t.getEventID(), col), loadingBehaviour);
	}

	@Override
	public void udpateByGroup(List<ReimbursementRequest> ts) {
		ts.forEach(item -> update(item));
	}

	@Override
	public Boolean deleteByID(Integer id) {
		return delete(tName, idField, id);
	}

}
