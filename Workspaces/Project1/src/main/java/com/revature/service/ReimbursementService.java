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
	
	public static List<Reimbursement> getSupervisorReimbursements(int suId){
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		return rd.selectSupervisorReimbursements(suId);
	}
	
	public String getSupervisorReimbursementsJSON(int suId) {
		List<Reimbursement> r = getSupervisorReimbursements(suId);
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
	
	public static int updateSupervisorReimbursement(int reimId) {
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		return rd.updateSupervisorApproval(reimId);
	}
	
	public static List<Reimbursement> getDeptHeadReimbursements(int dhId){
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		return rd.selectDeptHeadReimbursements(dhId);
	}
	
	public String getDeptHeadReimbursementsJSON(int dhId) {
		List<Reimbursement> r = getDeptHeadReimbursements(dhId);
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
	
	public static int updateDeptHeadReimbursement(int reimId) {
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		return rd.updateDeptHeadApproval(reimId);
	}
	
	public static List<Reimbursement> getBencoReimbursements(int bencoId){
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		return rd.selectBencoReimbursements(bencoId);
	}
	
	public String getBencoReimbursementsJSON(int bencoId) {
		List<Reimbursement> r = getBencoReimbursements(bencoId);
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
	
	public static int updateBencoReimbursement(int reimId) {
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		return rd.updateBencoApproval(reimId);
	}

}
