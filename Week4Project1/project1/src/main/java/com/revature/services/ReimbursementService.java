package com.revature.services;

import java.sql.Date;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.beans.Reimbursement;
import com.revature.dao.ReimbursementDao;

public class ReimbursementService 
{
	
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
		System.out.println("inside getReimbursementInfo inside ReimbursementService");
		ReimbursementDao rd = new ReimbursementDao();
		return rd.selectReimbursementInfo(accountname);
	}
	
	public String getReimbursementWithJSON(String accountname)
	{
		System.out.println("INSIDE getReimbursementWithJSON in ReimbursementService");
		List<Reimbursement> reims = getReimbursementInfo(accountname);
		System.out.println("reims after getReimbursementInfo in getReimbursementWithJSON: " + reims);
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
		//System.out.println("inside getReimbursementInfo inside ReimbursementService");
		ReimbursementDao rd = new ReimbursementDao();
		return rd.selectApprovedReimbursementInfo(accountname);
	}
	
	public String getApprovedReimbursementWithJSON(String accountname)
	{
		//System.out.println("INSIDE getReimbursementWithJSON in ReimbursementService");
		List<Reimbursement> reims = getApprovedReimbursementInfo(accountname);
		//System.out.println("reims after getReimbursementInfo in getReimbursementWithJSON: " + reims);
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
		//System.out.println("INSIDE getReimbursementWithJSON in ReimbursementService");
		List<Reimbursement> reims = getDeclinedReimbursementInfo(accountname);
		//System.out.println("reims after getReimbursementInfo in getReimbursementWithJSON: " + reims);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(reims);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
}
