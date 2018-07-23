package com.revature.services;

import static com.revature.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.revature.beans.ReimbursementReq;
import com.revature.dao.ReimbursementReqDaoImpl;
import com.revature.util.Connections;

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

}
