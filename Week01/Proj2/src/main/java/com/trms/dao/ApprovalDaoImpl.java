package com.trms.dao;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.trms.beans.Approval;
import com.trms.util.Convert;
import com.trms.util.SqlBuilder;

public class ApprovalDaoImpl extends GenericDaoImpl<Approval> implements Dao<Approval, Integer> {
//    approvalID number(6),
//    approved number(1) CHECK(approved=1 OR approved=0),
//    approvingUserID number(6),
//    approvalDate date,
	final String tName = "Approval", idField = "approvalID";
	final Integer col = 3;
	SqlBuilder<Integer> sb = new SqlBuilder<>();
	
	Function<Object[], Approval> objectReturnBehaviour = 
			item -> new Approval(
					Integer.parseInt(item[0].toString()),
					Integer.parseInt(item[2].toString()),
					Convert.integerToBoolean((int) item[1]),
					Date.valueOf(item[3].toString())
					);
	
	@Override
	public List<Approval> selectAll() {
		return select(sb.sAll(tName), objectReturnBehaviour);
	}

	@Override
	public Approval selectByID(Integer id) {
		return select(sb.sWhere(tName, idField, id), objectReturnBehaviour).get(0);
	}
	
	Function<Approval, List<Object>> loadingBehaviour =
			item -> Arrays.asList(
					item.getApprovalID(),
					item.getApprovingUserID(),
					Convert.booleanToInteger(item.isApproved()),
					item.getApprovalDate()
					);
	
	@Override
	public Boolean insertNew(Approval t) {
		return callProcedure(t, sb.callInsert(tName, col), loadingBehaviour);
	}

	@Override
	public Boolean update(Approval t) {
		return callProcedure(t, sb.callUpdate(tName, t.getApprovalID(), col), loadingBehaviour);
	}

	@Override
	public void udpateByGroup(List<Approval> ts) {
		ts.forEach(item -> update(item));
	}

	@Override
	public Boolean deleteByID(Integer id) {
		return delete(tName, idField, id);
	}

}
