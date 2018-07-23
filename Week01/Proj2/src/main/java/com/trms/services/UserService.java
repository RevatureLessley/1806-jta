package com.trms.services;

import java.sql.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.trms.beans.Employee;
import com.trms.beans.UserInfo;
import com.trms.beans.UserPass;
import com.trms.dao.Dao;
import com.trms.dao.EmployeeDaoImpl;
import com.trms.dao.UserInfoDaoImpl;
import com.trms.dao.UserPassDaoImpl;

public class UserService {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Boolean newUser(String username, String password, String fName, String lName,
			String address, Integer postalCode, Date birthDate, Date hireDate) {
		try {
		Dao dao = new UserPassDaoImpl();
		System.out.println("New UserPass returned: " + dao.insertNew(new UserPass(username, password)));
		dao = new UserInfoDaoImpl();
		System.out.println("New UserInfo returned: " + 
		dao.insertNew(new UserInfo(
				username, 
				fName,
				lName,
				birthDate,
				hireDate,
				address,
				postalCode
				)));
		dao = new EmployeeDaoImpl();
		List<UserInfo> users = (new UserInfoDaoImpl()).selectAll();
		Integer userInfoID = users.get(users.size() - 1).getID();
		System.out.println("New Employee returned: " + dao.insertNew(new Employee(userInfoID, null, "Employee")));
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Boolean newUser(String username, String password, String fName, 
			String lName,String address, Integer postalCode, Date birthDate, Date hireDate, Integer supervisor) {
		try {
		Dao dao = new UserPassDaoImpl();
		System.out.println("New UserPass returned: " + dao.insertNew(new UserPass(username, password)));
		dao = new UserInfoDaoImpl();
		System.out.println("New UserInfo returned: " + 
		dao.insertNew(new UserInfo(
				username, 
				fName,
				lName,
				birthDate,
				hireDate,
				address,
				postalCode
				)));
		dao = new EmployeeDaoImpl();
		Integer userInfoID = (new UserInfoDaoImpl()).selectByUsername(username).getID();
		System.out.println("New Employee returned: " + dao.insertNew(new Employee(userInfoID, supervisor, "Employee")));
		return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static boolean userLogin(String username, String password) {
		UserPassDaoImpl upd = new UserPassDaoImpl();
		UserPass user = null;
		if((user = upd.selectByID(username)) == null){
			return false;
		}
		if(!user.getPassword().equals(password)){
			return false;
		}
		
		return true;		
	}
	
	public static Employee buildUser(String username) {
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		UserInfo userInfo = (new UserInfoDaoImpl()).selectByUsername(username);
		Employee employee = edi.selectByUserInfoID(userInfo.getID());
		Employee supervisor = null;
		if(employee.getSupervisorID() != null) {
			supervisor = edi.selectByID(employee.getSupervisorID());
			employee = new Employee(employee.getEmployeeID(),employee.getTitle(), userInfo, supervisor);
		}else {
			employee = new Employee(employee.getEmployeeID(),employee.getTitle(), userInfo);
		}

		return employee;
	}
	
	public static String getAllUserJSON(){
		List<UserInfo> npcs = (new UserInfoDaoImpl()).selectAll();
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(npcs);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
}
