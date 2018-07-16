package com.trms.dao;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.trms.beans.UserPass;
import com.trms.util.SqlBuilder;

public class UserPassDaoImpl extends GenericDaoImpl<UserPass> implements Dao<UserPass, String>{
	final String tName = "UserPass", idField = "username";
	final Integer col = 2;
	SqlBuilder<String> sb = new SqlBuilder<>();
	Function<Object[], UserPass> objectReturnBehaviour = 
			(item) -> new UserPass(
					item[0].toString(), 
					item[1].toString()
					);

	@Override
	public List<UserPass> selectAll() {
		return select(sb.sAll(tName), objectReturnBehaviour);
	}

	@Override
	public UserPass selectByID(String username) {
		return select(sb.sWhere(tName, idField, username), objectReturnBehaviour).get(0);
	}

	Function<UserPass, List<Object>> loadingBehaviour = (item) -> Arrays.asList(item.getUsername(), item.getPassword());

	@Override
	public Boolean insertNew(UserPass usernamePassword) {		
		System.out.println("UserPassDaoImpl: " + usernamePassword);
		return callProcedure(usernamePassword, sb.callInsert(tName, col), loadingBehaviour);
	}

	@Override
	public Boolean update(UserPass userPass) {//TODO:Improve using addBatch and execute Batch
		return callProcedure(userPass, sb.callUpdate(tName, userPass.getUsername(), col), loadingBehaviour);
	}
	
	@Override
	public void udpateByGroup(List<UserPass> ups) { ups.forEach(item -> update(item)); }

	@Override
	public Boolean deleteByID(String username) {
		return delete(tName, idField, username);
	}
}
