package Project1.Service;

import Project1.Beans.*;
import Project1.DAOs.*;

public class EmployeeService {
	
	private static EmployeeDAOImp edi = new EmployeeDAOImp();
	
	public static boolean checkUsername(String username) {
		return edi.checkEmployee(username);
	}
	
	public static Employee employeeLogin(String username, String password) {
		return edi.selectEmployee(username, password);
	}
	
	public static Employee employeeRegister(String username, String password,
										   String firstname, String lastname, 
										   String department, String email,
										   String supervisor, String isBenco) {
		return edi.insertEmployee(username, password, firstname, lastname,
								  department, email, supervisor, isBenco);
	}
}
