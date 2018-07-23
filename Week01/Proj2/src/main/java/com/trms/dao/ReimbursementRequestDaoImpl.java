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
					Integer.parseInt(item[0].toString()), 
					Integer.parseInt(item[1].toString()), 
					Integer.parseInt(item[2].toString()), 
					Integer.parseInt(item[3].toString()),
					Integer.parseInt(item[4].toString()),
					Date.valueOf(item[5].toString().substring(0, 9)),
					Float.parseFloat(item[6].toString())
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
