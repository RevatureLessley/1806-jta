package com.revature.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

import com.revature.bean.Reimbursement;
import com.revature.dao.ReimbursementDaoImpl;

public class ReimbursementService {
	
	private static Logger logger = Logger.getLogger(ReimbursementService.class);
	
	public static boolean insertReimbursement(Reimbursement r) {
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		
		logger.info("Employee with id: " + r.getEmpId() + " submitted a request");
		return rd.insertReimbursement(r);
	}
	
	public static List<Reimbursement> getReimbursementByEmpId(int empId) {
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		
		logger.info("Reimbursements for employee with id: " + empId + " retrieved");
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
		logger.info("Reimbursement for employee with id: " + empId + " retrieved as JSON object");
		return json;
	}
	
	public static List<Reimbursement> getSupervisorReimbursements(int suId){
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		
		logger.info("Reimbursments for subordinate employees for supervisor with id: " + suId);
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
		logger.info("Reimbursments for subordinate employees for supervisor with id: " + suId + " retrieved as JSON");
		return json;
	}
	
	public static int updateSupervisorReimbursement(int reimId) {
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		
		logger.info("Updated supervisor approval for reimbursement with id " + reimId);
		return rd.updateSupervisorApproval(reimId);
	}
	
	public static List<Reimbursement> getDeptHeadReimbursements(int dhId){
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		
		logger.info("Reimbursments for subordinate employees for departement head with id: " + dhId);
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
		logger.info("Reimbursments for subordinate employees for department head with id: " + dhId + " retrieved as JSON");
		return json;
	}
	
	public static int updateDeptHeadReimbursement(int reimId) {
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		
		logger.info("Updated department head approval for reimbursement with id " + reimId);
		return rd.updateDeptHeadApproval(reimId);
	}
	
	public static List<Reimbursement> getBencoReimbursements(int bencoId){
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		
		logger.info("Reimbursments for subordinate employees for benco with id: " + bencoId);
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
		logger.info("Reimbursments for subordinate employees for benco with id: " + bencoId + " retrieved as JSON");
		return json;		
	}
	
	public static int updateBencoReimbursement(int reimId) {
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();
		
		logger.info("Updated benco approval for reimbursement with id " + reimId);
		return rd.updateBencoApproval(reimId);
	}

}
