package com.revature.service;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.bean.Reimbursement;
import com.revature.dao.ReimbursementDaoImpl;

public class ReimbursementService {
	public static boolean insertReimbursement(Reimbursement r) {
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		return rd.insertReimbursement(r);
	}
	
	public static List<Reimbursement> getReimbursementByEmpId(int empId) {
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		return rd.selectReimbursementByEmpId(empId);
	}
	
	public String getReimbursementJSON(int empId) {
		List<Reimbursement> r = getReimbursementByEmpId(empId);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try {
			json = mapper.writeValueAsString(r);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	

}
