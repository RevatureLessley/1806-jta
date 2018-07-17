package project01Services;

import java.sql.Date;

import project01Dao.RequestDaoImpl;

public class RequestService {
	public static RequestDaoImpl reqDao = new RequestDaoImpl();
	
	public void createRequest(String username, String event,       String justify, String location, String firstname, 
	    	                  String lastname, String description, String fileName,Float cost,      Date theDate)
	{
		  int reqId = reqDao.getNumberOf();
	      reqDao.setReimbursement(username,event,justify,location,firstname,lastname,description,fileName,cost,reqId,theDate);
	      reqDao.approvalTable(reqId);
	      reqId++;
	      reqDao.updateNumberof(reqId);
	      
	}

	public int getReqId() {
		return reqDao.getNumberOf();
	}
}
