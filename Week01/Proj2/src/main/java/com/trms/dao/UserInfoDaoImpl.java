package com.trms.dao;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.trms.beans.UserInfo;
import com.trms.util.SqlBuilder;

public class UserInfoDaoImpl extends GenericDaoImpl<UserInfo> implements Dao<UserInfo, Integer> {
	final String tName = "UserInfo", idField = "UserInfoID";
	final Integer col = 7;
	SqlBuilder<Integer> sb = new SqlBuilder<>();
	Function<Object[], UserInfo> objectReturnBehaviour = 
			(item) -> new UserInfo(
					(int)item[0], 
					(int)item[1],
					item[2].toString(),
					item[3].toString(),
					item[4].toString(),
					item[5].toString(),
					Date.valueOf(item[6].toString()),
					Date.valueOf(item[7].toString())
					);
	
	@Override
	public List<UserInfo> selectAll() {
		return select(sb.sAll(tName), objectReturnBehaviour);
	}

	@Override
	public UserInfo selectByID(Integer id) {
		return select(sb.sWhere(tName, idField, id), objectReturnBehaviour).get(0);
	}

	Function<UserInfo, List<Object>> loadingBehaviour = 
			(item) -> Arrays.asList(
					item.getUsername(), 
					item.getfName(),
					item.getlName(),
					item.getBirthDate(),
					item.getHireDate(),
					item.getAddress(),
					item.getPostalCode()
					);
	
	@Override
	public Boolean insertNew(UserInfo t) {
		return callProcedure(t, sb.callInsert(tName, col), loadingBehaviour);
	}

	@Override
	public void udpateByGroup(List<UserInfo> ts) {
		ts.forEach(item -> update(item));
	}

	@Override
	public Boolean update(UserInfo t) {
		return callProcedure(t, sb.callUpdate(tName, t.getID(), col), loadingBehaviour);
	}

	@Override
	public Boolean deleteByID(Integer id) {
		return delete(tName, idField, id);
	}

}
