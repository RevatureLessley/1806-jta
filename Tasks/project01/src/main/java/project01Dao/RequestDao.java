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
	public void approvalTable(int id, Date date);
	public List<Request> getListOfRequests();
	public List<Request> getListOfRequestsDS();
	public List<Request> getListOfRequestsBC();
	public List<Request> getListOfRequestsClient(String uname);
	public List<String> getListOfFileNames(String id);
	
	public void directSupervisorApproval(String id);
	public void departmentHeadApproval(String id);
	public void bCApproval(String id);
	public void createAdditionalFiles(String id, String fileName);

}
