package com.trms.dao;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.trms.beans.UserHistory;
import com.trms.util.SqlBuilder;

public class UserHistoryDaoImpl extends GenericDaoImpl<UserHistory> implements Dao<UserHistory, Integer> {
	//quick ref table guide
	/*
	historyID number(6),
    userID number(6),
    content VARCHAR(2000),
    */
	final String tName = "UserHistory", idField = "historyID";
	final Integer col = 2;
	SqlBuilder<Integer> sb = new SqlBuilder<>();
	Function<Object[], UserHistory> objectReturnBehaviour =
			item -> new UserHistory(
					Integer.parseInt(item[0].toString()),
					Integer.parseInt(item[1].toString()),
					item[2].toString()
					);
	
	@Override
	public List<UserHistory> selectAll() {
		return select(sb.sAll(tName), objectReturnBehaviour);
	}

	@Override
	public UserHistory selectByID(Integer id) {
		return select(sb.sWhere(tName, idField, id), objectReturnBehaviour).get(0);
	}

	Function<UserHistory, List<Object>> loadingBehaviour =
			item -> Arrays.asList(
					item.getHistoryID(),
					item.getUserID(),
					item.getContent()
					);
	
	@Override
	public Boolean insertNew(UserHistory t) {
		return callProcedure(t, sb.callInsert(tName, col), loadingBehaviour);
	}
	
	@Override
	public Boolean update(UserHistory t) {
		return callProcedure(t, sb.callUpdate(tName, t.getHistoryID(), col), loadingBehaviour);
	}
	
	@Override
	public void udpateByGroup(List<UserHistory> ts) {
		ts.forEach(item -> update(item));
	}

	@Override
	public Boolean deleteByID(Integer id) {
		return delete(tName, idField, id);
	}
	
}
