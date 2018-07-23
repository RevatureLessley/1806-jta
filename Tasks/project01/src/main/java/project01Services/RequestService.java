package project01Services;

import java.sql.Date;

import org.apache.log4j.Logger;

import project01Dao.RequestDaoImpl;

public class RequestService {
	public static RequestDaoImpl reqDao = new RequestDaoImpl();
	final static Logger logger = Logger.getLogger(RequestService.class);

	
	public void createRequest(String username, String event,       String justify, String location, String firstname, 
	    	                  String lastname, String description, String fileName,Float cost,      Date theDate)
	{
		  logger.info("");
		  int reqId = reqDao.getNumberOf();
		  logger.info("setReimbursement at line 20");
	      reqDao.setReimbursement(username,event,justify,location,firstname,lastname,description,fileName,cost,reqId,theDate);
		  logger.info("Set approval table at line 22");
	      reqDao.approvalTable(reqId, theDate);
	      reqId++;
	      reqDao.updateNumberof(reqId);
	      
	}

	public int getReqId() {
		logger.info("");
		return reqDao.getNumberOf();
	}
}
