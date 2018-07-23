package com.revature.services;

import static com.revature.util.LogFourJ.log;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.beans.Employee;
import com.revature.beans.ReimbursementForm;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImpl;

public class EmployeeService {
	
	
	public static boolean createEmployee(HttpServletRequest request){
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		int idTemp = 0 ;
		switch(request.getParameter("Employee_Dept")) {
		case "Math":
			idTemp = 1001;
		case "History":
			idTemp = 1010;
		case "Hr":
			idTemp = 1111;
		}
		
		if(!(edi.selectEmployeeByUserName(request.getParameter("Employee_UserName")) == null )) {
			log.info("User Already Exist, Please Chose Another Username");
			return false;
		}
		Employee emp = new Employee(
					request.getParameter("Employee_Fname"),
					request.getParameter("Employee_Lname"),
					request.getParameter("Employee_UserName"),
					request.getParameter("Employee_Password"),
					request.getParameter("Employee_Phone"),
					request.getParameter("Employee_Email"),
					request.getParameter("Employee_Dept")
					);
		emp.setId(idTemp);
		edi.insertEmployee(emp);
		return true;
	}
	public static boolean employeeLogin(String Employee_UserName, String Employee_Password){
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		Employee emp = null;
		if(((emp = edi.selectEmployeeByUserName(Employee_UserName)) == null)){
			log.info("Login False(USERNAME)");
			return false;
		}
		if(!emp.getEmpPassword().equals(Employee_Password)){
			log.info("Login False(Password)");
			return false;
		}
		
		log.info("Login True");
		return true;
	}
	public static boolean employeeRole(String Employee_Role){
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		Employee emp = null;
		emp = edi.selectEmployeeByRole(Employee_Role);
		if(Employee_Role == "Direct Supper"){
			log.info(Employee_Role + "Role true");
			return true;
		}
		
		log.info("Role false");
		return false;
	}
	public List<Employee> getAllClams2(){
		EmployeeDao ed = new EmployeeDaoImpl();
		List<Employee> eds = ed.selectAllEmployee();
		return eds;
	}
	public String getAllJSON2(){
		List<Employee> es = getAllClams2();
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(es);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
}
