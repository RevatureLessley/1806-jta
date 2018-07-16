package com.trms.dao;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.trms.beans.RequestStatus;
import com.trms.util.Convert;
import com.trms.util.SqlBuilder;

public class RequestStatusDaoImpl extends GenericDaoImpl<RequestStatus> implements Dao<RequestStatus, Integer> {
	final String tName = "RequestStatus", idField = "statusID";
	final Integer col = 6;
	SqlBuilder<Integer> sb = new SqlBuilder<>();
	
	Function<Object[], RequestStatus> objectReturnBehaviour =
			item -> new RequestStatus(
				    Integer.parseInt(item[0].toString()),
				    Integer.parseInt(item[1].toString()),
				    Integer.parseInt(item[2].toString()),
				    Integer.parseInt(item[3].toString()),
				    Convert.integerToBoolean((int)item[4]),
				    Convert.integerToBoolean((int)item[5]),
				   	Convert.integerToBoolean((int)item[6])
					);
			
	@Override
	public List<RequestStatus> selectAll() {
		return select(sb.sAll(tName), objectReturnBehaviour);
	}

	@Override
	public RequestStatus selectByID(Integer id) {
		return select(sb.sWhere(tName, idField, id), objectReturnBehaviour).get(0);
	}

	Function<RequestStatus, List<Object>> loadingBehaviour =
			item -> Arrays.asList(
				    item.getSuperApprovalID(),
				    item.getDepHeadApprovalID(),
				    item.getBenCoApprovalID(),
				    item.isUrgent(),
				    item.isCanceled(),
				    item.isExceededAvailableFunds()
					);
	
	@Override
	public Boolean insertNew(RequestStatus t) {
		return callProcedure(t, sb.callInsert(tName, col), loadingBehaviour);
	}

	@Override
	public Boolean update(RequestStatus t) {
		return callProcedure(t, sb.callInsert(tName, col), loadingBehaviour);
	}

	@Override
	public void udpateByGroup(List<RequestStatus> ts) {
		ts.forEach(item -> update(item));
	}

	@Override
	public Boolean deleteByID(Integer id) {
		return delete(tName, idField, id);
	}

}
