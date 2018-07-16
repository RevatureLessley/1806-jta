package com.trms.dao;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import com.trms.beans.Employee;
import com.trms.util.SqlBuilder;

public class EmployeeDaoImpl extends GenericDaoImpl<Employee> implements Dao<Employee, Integer> {
    /*employeeID number(6),
    userInfoID number(6) UNIQUE,
    supervisorID number(6),
    title varchar2(50),*/
	final String tName = "Employee", idField = "employeeID";
	final Integer col = 2;
	SqlBuilder<Integer> sb = new SqlBuilder<>();
	Function<Object[], Employee> objectReturnBehaviour = 
			item -> new Employee(
					Integer.parseInt(item[0].toString()),
					Integer.parseInt(item[1].toString()),
					Integer.parseInt(item[2].toString()),
					item[3].toString()
					);

	@Override
	public List<Employee> selectAll() {
		return select(sb.sAll(tName), objectReturnBehaviour);
	}

	@Override
	public Employee selectByID(Integer id) {
		return select(sb.sWhere(tName, idField, id), objectReturnBehaviour).get(0);
	}

	Function<Employee, List<Object>> loadingBehaviour = 
			item -> Arrays.asList(
					item.getUserInfoID(),
					item.getSupervisorID(),
					item.getTitle()
					);

	@Override
	public Boolean insertNew(Employee t) {
		return callProcedure(t, sb.callInsert(tName, col), loadingBehaviour);
	}

	@Override
	public Boolean update(Employee t) {
		return callProcedure(t, sb.callUpdate(tName, t.getEmployeeID(), col), loadingBehaviour);
	}

	@Override
	public void udpateByGroup(List<Employee> ts) {
		ts.forEach(item -> update(item));
	}

	@Override
	public Boolean deleteByID(Integer id) {
		return delete(tName, idField, id);
	}

}
