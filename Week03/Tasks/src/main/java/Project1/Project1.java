package Project1;

import java.sql.*;

import Project1.DAO.*;

public class Project1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Connection connection = DatabaseConnection.connect();
		EmployeeDAOImp edi = new EmployeeDAOImp();
		edi.insertEmployee();
	}

}
