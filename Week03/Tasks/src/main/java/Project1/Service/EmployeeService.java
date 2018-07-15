package Project1.Service;

import Project1.DAOs.*;

public class EmployeeService {
	
	private static EmployeeDAOImp edi = new EmployeeDAOImp();
	
	public static boolean checkUsername(String username) {
		return edi.checkEmployee(username);
	}
	
	public static boolean employeeLogin(String username, String password) {
		return null != edi.selectEmployee(username, password);
	}
	
	public static void employeeRegister(String username, String password,
										   String firstname, String lastname, 
										   String department,
										   String supervisor, String isBenco) {
		edi.insertEmployee(username, password, firstname, lastname,
								  department, supervisor, isBenco);
	}
}
