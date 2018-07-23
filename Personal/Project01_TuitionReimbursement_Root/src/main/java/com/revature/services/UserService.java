package com.revature.services;

import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.Reimbursement;
import com.revature.daos.EmployeeDao;
import com.revature.daos.EmployeeDaoImpl;
import com.revature.daos.ReimbursementDao;
import com.revature.daos.ReimbursementDaoImpl;
import com.revature.main.Driver;

public class UserService {
	public static boolean createUser(String role, int supVId, String fname, String lname, int phone,
			String email, String address, String location, String username, String password) {
		Employee user = null;
		EmployeeDao empd = new EmployeeDaoImpl();
		
		if(!(empd.selectEmployeeByUsername(username)==null)) return false;
		
		empd.insertEmployeeViaSp(new Employee(role, supVId, fname, lname, phone, email, address,
				location, username, password));
		
		return true;
	}
	
	public static boolean createReimbursement(Reimbursement reim) {
		ReimbursementDao reimd = new ReimbursementDaoImpl();
		
		if (!reimd.insertReimbursementViaSp(reim)) return false;
		
		return true;
	}
	
	public static boolean updateReimbursement(Reimbursement reim) {
		ReimbursementDao reimd = new ReimbursementDaoImpl();
		
		if (!reimd.updateReimbursementViaSp(reim)) return false;
		
		return true;
	}
	
	public static Employee userLogin(String username, String password){
		EmployeeDaoImpl empd = new EmployeeDaoImpl();
		Employee emp = null;
		if((emp = empd.selectEmployeeByUsername(username)) == null){
			return null;
		}
		if(!emp.getPassword().equals(password)){
			return null;
		}
		
		Driver.loggedIn = emp;
		return emp;
	}
	
	public static List<Reimbursement> getAllReimbursementsByEmpId(Integer id){
		ReimbursementDao reimd = new ReimbursementDaoImpl();
		return reimd.selectAllReimbursementByEmpId(id);
	}
	
	public static List<Reimbursement> getAllReimbursementsByApproverId(Integer id){
		ReimbursementDao reimd = new ReimbursementDaoImpl();
		return reimd.selectAllReimbursementByApproverId(id);
	}
	
	public static List<Employee> getAllEmployeesByRole(String role) {
		EmployeeDao empd = new EmployeeDaoImpl();
		return empd.selectAllEmployeesByRole(role);
	}
	
	public static Reimbursement getReimbursementById(Integer id) {
		ReimbursementDao reimd = new ReimbursementDaoImpl();
		return reimd.selectReimbursementById(id);
	}
	
	public static Boolean updateReimbursementSupervisorApprovalById(Integer id) {
		ReimbursementDao reimd = new ReimbursementDaoImpl();
		return reimd.updateReimbursementSupervisorApprovalByIdViaSP(id);
	}
	public static Boolean updateReimbursementHeadApprovalById(Integer id) {
		ReimbursementDao reimd = new ReimbursementDaoImpl();
		return reimd.updateReimbursementHeadApprovalByIdViaSP(id);
	}
	public static Boolean updateReimbursementBencoApprovalById(Integer id) {
		ReimbursementDao reimd = new ReimbursementDaoImpl();
		return reimd.updateReimbursementBencoApprovalByIdViaSP(id);
	}
	
	public static Boolean updateReimDocById(Integer id, byte[] rawBytes) {
		ReimbursementDao reimd = new ReimbursementDaoImpl();
		return reimd.updateReimbursementDocumentByIdViaSp(id, rawBytes);
	}
}
