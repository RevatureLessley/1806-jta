package com.revature.services;

import java.sql.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.beans.Reimbursement;
import com.revature.dao.ReimbursementDao;

public class ReimbursementService 
{
	
	public List<Reimbursement> getLevelOneReimbursementInfo(String accountname)
	{
		ReimbursementDao rd = new ReimbursementDao();
		return rd.selectLevelOneReimbursementInfo(accountname);
	}
	
	public String getLevelOneReimbursementWithJSON(String accountname)
	{
		List<Reimbursement> reims = getLevelOneReimbursementInfo(accountname);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(reims);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
	
	public List<Reimbursement> getLevelTwoReimbursementInfo(String accountname)
	{
		ReimbursementDao rd = new ReimbursementDao();
		return rd.selectLevelTwoReimbursementInfo(accountname);
	}
	
	public String getLevelTwoReimbursementWithJSON(String accountname)
	{
		List<Reimbursement> reims = getLevelTwoReimbursementInfo(accountname);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(reims);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
	
	public List<Reimbursement> getLevelOneOrTwoReimbursementInfo(String accountname)
	{
		ReimbursementDao rd = new ReimbursementDao();
		return rd.selectLevelOneOrTwoReimbursementInfo(accountname);
	}
	
	public String getLevelOneOrTwoReimbursementWithJSON(String accountname)
	{
		List<Reimbursement> reims = getLevelOneOrTwoReimbursementInfo(accountname);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(reims);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
	
	public List<Reimbursement> getAllLevelsReimbursementInfo(String accountname)
	{
		ReimbursementDao rd = new ReimbursementDao();
		return rd.selectAllLevelsReimbursementInfo(accountname);
	}
	
	public String getAllLevelsReimbursementWithJSON(String accountname)
	{
		List<Reimbursement> reims = getAllLevelsReimbursementInfo(accountname);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(reims);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
	
	public boolean insertReimbursement(String eventDate, String eventTime, String eventLocation,
									   String eventDesc, Integer eventCost, String justification,
									   Integer gradeCutoff, Integer empId, Integer eventId,
									   Integer gradingFormatId)
	{
		ReimbursementDao rd = new ReimbursementDao();
		return rd.insertReimbursementViaSp(eventDate, eventTime, eventLocation, 
										   eventDesc, eventCost, justification, 
										   gradeCutoff, empId, eventId, gradingFormatId);
	}
	
	public List<Reimbursement> getReimbursementInfo(String accountname)
	{
		ReimbursementDao rd = new ReimbursementDao();
		return rd.selectReimbursementInfo(accountname);
	}
	
	public String getReimbursementWithJSON(String accountname)
	{
		List<Reimbursement> reims = getReimbursementInfo(accountname);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(reims);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
	
	public List<Reimbursement> getApprovedReimbursementInfo(String accountname)
	{
		ReimbursementDao rd = new ReimbursementDao();
		return rd.selectApprovedReimbursementInfo(accountname);
	}
	
	public String getApprovedReimbursementWithJSON(String accountname)
	{
		List<Reimbursement> reims = getApprovedReimbursementInfo(accountname);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(reims);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
	
	public List<Reimbursement> getDeclinedReimbursementInfo(String accountname)
	{
		//System.out.println("inside getReimbursementInfo inside ReimbursementService");
		ReimbursementDao rd = new ReimbursementDao();
		return rd.selectDeclinedReimbursementInfo(accountname);
	}
	
	public String getDeclinedReimbursementWithJSON(String accountname)
	{
		List<Reimbursement> reims = getDeclinedReimbursementInfo(accountname);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(reims);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
	
	public void updateApprovalToLevel2()
	{
		ReimbursementDao rd = new ReimbursementDao();
		rd.updateApprovalToLevelTwo();
	}
	
	public void updateApprovalToLevel0()
	{
		ReimbursementDao rd = new ReimbursementDao();
		rd.updateApprovalToLevelZero();
	}
	
	public void updateApprovalToLevel3()
	{
		ReimbursementDao rd = new ReimbursementDao();
		rd.updateApprovalToLevelThree();
	}
	
	public void updateApprovalToLevel4()
	{
		ReimbursementDao rd = new ReimbursementDao();
		rd.updateApprovalToLevelFour();
	}
}
