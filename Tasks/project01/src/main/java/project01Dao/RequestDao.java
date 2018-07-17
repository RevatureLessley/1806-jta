package project01Dao;

import java.sql.Date;
import java.util.List;

import beans.Request;

public interface RequestDao {
	public void setReimbursement(	String username, 
									String event, 
									String justify,
									String fname,
									String lname,
	 								String description, 
									String location, 
									String file_locat,
	 								 Float cost, 
	 								   int id,
	 								  Date date );
	
	public int getNumberOf();
	public void updateNumberof(int id);
	public void approvalTable(int id);
	public List<Request> getListOfRequests();
	public List<Request> getListOfRequestsClient(String uname);
	
	public void directSupervisorApproval(String id);

}
