package dao;

import java.util.ArrayList;

import beans.employee;
import beans.person;

public interface EmployeeDao {
	public employee getEmployeeByID(Integer id);
	public ArrayList<employee> getEmployeesBySupervisor(Integer id);
	public ArrayList<person> getPeopleBySuperior(Integer id);
	public ArrayList<employee> getEmployeesByHead(Integer id);
	public ArrayList<employee> getEmployeesByHeadSupervisor(Integer id);
	public ArrayList<employee> getEmployeesByCooperate(Integer id);
	public void updateEmployeeRequests(employee e);
}
