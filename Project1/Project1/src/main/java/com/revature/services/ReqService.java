package com.revature.services;

import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.beans.ReimbursementReq;
import com.revature.dao.ReimbursementReqDaoImpl;

public class ReqService {
	
	public String getReqsJSON(String table)
	{
		ReimbursementReqDaoImpl rd = new ReimbursementReqDaoImpl();
		List<ReimbursementReq> reqs = rd.selectReqs(table);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try
		{
			json = mapper.writeValueAsString(reqs);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return json;
	}
	
	public void ApproveReq(int reqId,  String role)
	{
		ReimbursementReqDaoImpl rd = new ReimbursementReqDaoImpl(); 
		
		ReimbursementReq req = rd.selectReqByReqId(reqId);
		
		
		
		
		if (role.equals("_ds"))
		{
			req.setDsApprove(true);
		}
		else if(role.equals("_dh"))
		{
			req.setDhApprove(true);
		}
		else if(role.equals("_bc"))
		{
			req.setBcApprove(true);
		}
		
		String table = null;
		
		if (role.equals("_ds"))
		{
			table = "_dh";
			rd.insertReq(req, table);
			rd.deleteReq(req, role);
		}
		else if(role.equals("_dh"))
		{
			table = "_bc";
			rd.insertReq(req, table);
			rd.deleteReq(req, role);
		}
		else if(role.equals("_bc"))
		{
			rd.deleteReq(req, role);
		}
		
		rd.insertReq(req, table);
	}

}
