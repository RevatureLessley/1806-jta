package com.revature.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.revature.bean.Reimbursement;

public class ReimbursementDaoTests {

	@Test
	public void selectReimbursementByEmpIdTest() {
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();		
		
		List<Reimbursement> list = rd.selectReimbursementByEmpId(1);
		
		assertEquals(list.size(), 2);
		
	}
	
	@Test
	public void selectSupervisorReimbursementsTest() {
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();		
		
		List<Reimbursement> list = rd.selectSupervisorReimbursements(100);
		
		assertEquals(list.size(), 2);
		
	}
	
	@Test
	public void selectDeptHeadReimbursementsTest() {
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();		
		
		List<Reimbursement> list = rd.selectDeptHeadReimbursements(200);
		
		assertEquals(list.size(), 0);
		
	}
	
	@Test
	public void selectBencoReimbursementsTest() {
		ReimbursementDaoImpl rd = new ReimbursementDaoImpl();		
		
		List<Reimbursement> list = rd.selectBencoReimbursements(300);
		
		assertEquals(list.size(), 0);
		
	}

}
