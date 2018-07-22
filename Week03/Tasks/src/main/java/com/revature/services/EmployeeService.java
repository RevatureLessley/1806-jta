package com.revature.services;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.beans.Employee;
import com.revature.beans.RForm;
import com.revature.dao.EmployeeDaoImpl;

public class EmployeeService {
	public static boolean registerEmployee(
			String usern,
			String passw,
			String fname,
			String lname,
			int dirsupid,
			int depid,
			int emptype
		){
	Employee employee = new Employee(usern,passw,fname,lname,dirsupid,depid,emptype);
	EmployeeDaoImpl empDao = new EmployeeDaoImpl();
	if(!(empDao.selectEmployeeByUserN(usern) == null)){
		return false;
	}
	if(empDao.insertEmployeeViaSp(employee)) return true;
	return false;
	}
	
	public static boolean employeeLogin(String usern,String passw) {
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		Employee employee;
		if((employee = empDao.selectEmployeeByUserN(usern)) == null) {
			return false;
		}
		if(!employee.getPassW().equals(passw)) {
			return false;
		}
		return true;
	}
	public static Employee getEmp(String usern,String passw){
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		Employee employee;
		employee = empDao.selectEmployeeByUserN(usern);
		Employee dirSup = empDao.selectEmployeeById(employee.getDirSupId());
		employee.setDirSupName(dirSup.getFirstN() + " " + dirSup.getLastN());
		employee.setDepName(DepartmentService.department.getDepIdMap().get(employee.getDepId()));
		employee.setEmpTypeName(EmployeeTypeService.emptypes.getEmpTypeIdMap().get(employee.getEmpType()));
		return employee;
	}
	public static String getEmpInfoJSON(String usern,String passw){
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		Employee employee;
		employee = empDao.selectEmployeeByUserN(usern);
		Employee dirSup = empDao.selectEmployeeById(employee.getDirSupId());
		employee.setDirSupName(dirSup.getFirstN() + " " + dirSup.getLastN());
		employee.setDepName(DepartmentService.department.getDepIdMap().get(employee.getDepId()));
		employee.setEmpTypeName(EmployeeTypeService.emptypes.getEmpTypeIdMap().get(employee.getEmpType()));
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(employee);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
	public static String getEmpRFormsJSON(int empid) {
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		List<RForm> rforms = empDao.selectRformByEmployeeId(empid);
		for (RForm form: rforms) {
			form.setEventTypeName(EventTypeService.eventtypes.getEventTypeNameMap()
					.get(form.getEventTypeId()));
			form.setEmpName(empDao.selectEmployeeById(form.getEmpid()).getFirstN()
					+ " " + empDao.selectEmployeeById(form.getEmpid()).getLastN());
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try {
			json = mapper.writeValueAsString(rforms);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	public static String getSupRFormsJSON(int supid) {
		EmployeeDaoImpl empDao = new EmployeeDaoImpl();
		List<RForm> rforms = empDao.selectRformBySupId(supid);
		for (RForm form: rforms) {
			form.setEventTypeName(EventTypeService.eventtypes.getEventTypeNameMap()
					.get(form.getEventTypeId()));
			form.setEmpName(empDao.selectEmployeeById(form.getEmpid()).getFirstN()
					+ " " + empDao.selectEmployeeById(form.getEmpid()).getLastN());
			form.setIsSup(1);
		}
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try {
			json = mapper.writeValueAsString(rforms);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
}
