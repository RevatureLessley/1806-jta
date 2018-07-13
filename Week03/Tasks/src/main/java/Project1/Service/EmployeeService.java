package Project1.Service;

import Project1.Beans.*;
import Project1.DAOs.*;

public class EmployeeService {
	
	public static boolean employeeLogin(String username, String password) {
		return null != new EmployeeDAOImp().selectEmployee(username, password);
	}
}
