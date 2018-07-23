package project01Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import beans.Request;
import project01Services.RequestService;
import project01util.Connections;

public class RequestDaoImpl implements RequestDao{
	final static Logger logger = Logger.getLogger(RequestDaoImpl.class);


	@Override
	public int getNumberOf() {
		logger.info("Attempting to connect to DB");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT NUMBEROF FROM PROJECT1_NUMBEROF WHERE ID=1";
			logger.info("SQL STATEMENT: "+sql);
			ResultSet rs = null;
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();

			return rs.getInt(1);
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("SQL ERRROR");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Unable to close the connection");
			}
		}
		return -1;
	}

	@Override
	public void setReimbursement(String username, String event, String justify, String address, String fname,
			String lname, String description, String file_locat, Float cost, int id, Date date) {
		
		logger.info("Attempting to connect to DB");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "call insertReim  (?,?,?,?,?,?,?,?,?,?,?)";
			
			stmt = conn.prepareStatement(sql);
			
			stmt.setString   (1, username);
			stmt.setString   (2, event);
			stmt.setString   (3, justify);
			stmt.setString   (4, address);
			stmt.setString   (5, fname);
			stmt.setString   (6, lname);
			stmt.setString   (7, description);
			stmt.setString   (8, file_locat);
			stmt.setFloat    (9, cost);
			stmt.setInt      (10, id);
			stmt.setDate     (11, date);

			stmt.executeQuery();
			
		}catch(SQLException e){
			logger.error("SQL exception");
			e.printStackTrace();
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Unable to close the connection");
			}
		}
		
	}

	public void updateNumberof(int id) {
		logger.info("Attempting to connect to database");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "UPDATE PROJECT1_NUMBEROF SET NUMBEROF=? WHERE ID=1";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.executeQuery();

		}catch(SQLException e){
			e.printStackTrace();
			logger.error("SQL exception");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Couldn't close the connection");
			}
		}
	}

	@Override
	public void approvalTable(int id, Date date) {
		logger.info("Attempting to set up approval table");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "call rdy_App (?,?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt   (1, id);
			stmt.setDate  (2, date);
			stmt.executeQuery();
			
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("SQL exception");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Couldn't close the connection");
			}
		}
		
	}

	@Override
	public List<Request> getListOfRequests() {
		logger.info("Connecting to get a list of requests");
		ResultSet rs = null;
		List<Request> requestList = new ArrayList<>();
		Statement stmt = null;
		try(Connection conn = Connections.getConnection())
		{
			String sql = "select project1_reimbursement.username, project1_reimbursement.fname, project1_reimbursement.lname, "+ 
					"project1_reimbursement_details.date_sub, project1_reimbursement_details.request_id, "+
					"project1_reimbursement_details.description,project1_reimbursement_details.event, "+
					"project1_reimbursement_details.justification, project1_reimbursement.cost from project1_reimbursement "+
					"full outer join project1_reimbursement_details on "+
					"project1_reimbursement.request_id = project1_reimbursement_details.request_id "+
					"where project1_reimbursement.request_id in "+
					"(select request_id from project1_approval where project1_approval.direct_supervisor_app = 0) "+
					"order by project1_reimbursement.lname";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()){

				Request request = new Request(rs.getString("fname"), 
										  rs.getString("lname"), 
										  rs.getString("username"), 
										  rs.getString("description"), 
										  rs.getString("event"), 
										  rs.getString("justification"), 
										  rs.getFloat("cost"), 
										  rs.getInt("request_id"),
										  rs.getDate("date_sub"));
				
				//request.printAll();
				
				requestList.add(request);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("SQL exception");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Couldn't close the connection");
			}
		}
		
		return requestList;
	}

	@Override
	public List<Request> getListOfRequestsClient(String uname) {
		logger.info("Attempting to connect to database");
		ResultSet rs = null;
		List<Request> requestList = new ArrayList<>();
		Statement stmt = null;
		try(Connection conn = Connections.getConnection())
		{
			String sql = "select project1_reimbursement.username, project1_reimbursement.fname, project1_reimbursement.lname, " + 
					"project1_reimbursement_details.date_sub, project1_reimbursement_details.request_id, " + 
					"project1_reimbursement_details.description,project1_reimbursement_details.event, " + 
					"project1_reimbursement_details.justification, project1_reimbursement.cost from project1_reimbursement " + 
					"full outer join project1_reimbursement_details on " + 
					"project1_reimbursement.request_id = project1_reimbursement_details.request_id " + 
					"where project1_reimbursement.username = '"+ uname + 
					"' order by project1_reimbursement.lname ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				logger.info("Processing the list");
				Request request = new Request(rs.getString("fname"), 
										  rs.getString("lname"), 
										  rs.getString("username"), 
										  rs.getString("description"), 
										  rs.getString("event"), 
										  rs.getString("justification"), 
										  rs.getFloat("cost"), 
										  rs.getInt("request_id"),
										  rs.getDate("date_sub"));
				
				requestList.add(request);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("SQL exception");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Couldn't close the connection");
			}
		}
		
		return requestList;
	}

	@Override
	public void directSupervisorApproval(String id) {
		logger.info("Attempt to connect to database");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "UPDATE project1_approval SET direct_supervisor_app = 1 WHERE request_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeQuery();

			
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("SQL exception");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Couldn't close the connection");
			}
		}
	}

	//Adds additional file to the database
	@Override
	public void createAdditionalFiles(String id, String fileName) {
		logger.info("Uploading additional file");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "insert into project1_file_location values(?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, fileName);
			stmt.executeQuery();
			
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("SQL exception");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Unable to close the connection");
			}
		}		
	}

	
	/**
	 * Returns a list of all the files with the given id
	 * */
	@Override
	public List<String> getListOfFileNames(String id) {
		logger.info("Connecting to database");
		ResultSet rs = null;
		List<String> fileList = new ArrayList<>();
		PreparedStatement stmt = null;
		try(Connection conn = Connections.getConnection())
		{
			String sql = "select * from project1_file_location where request_id = "+ id; 
			stmt = conn.prepareStatement(sql);
			//stmt.setString (1, id);
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				logger.info("Grabbing a file");
				fileList.add(rs.getString("file_location"));
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("Couldn't connect");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Couldn't close the connection");
			}
		}
		
		return fileList;
	}

	@Override
	public List<Request> getListOfRequestsDS() {
			logger.info("Getting list of requests");
			ResultSet rs = null;
			List<Request> requestList = new ArrayList<>();
			Statement stmt = null;
			try(Connection conn = Connections.getConnection())
			{
				String sql = "select project1_reimbursement.username, project1_reimbursement.fname, project1_reimbursement.lname, " + 
						"project1_reimbursement_details.date_sub, project1_reimbursement_details.request_id, " + 
						"project1_reimbursement_details.description,project1_reimbursement_details.event, " + 
						"project1_reimbursement_details.justification, project1_reimbursement.cost from project1_reimbursement " + 
						"full outer join project1_reimbursement_details on " + 
						"project1_reimbursement.request_id = project1_reimbursement_details.request_id " + 
						"where project1_reimbursement.request_id in " + 
						"(select request_id from project1_approval where project1_approval.direct_supervisor_app = 1 " + 
						"and project1_approval.department_head_app = 0) "+
						"order by project1_reimbursement.lname";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql);

				while(rs.next()){
					logger.info("Processing Request");
					Request request = new Request(rs.getString("fname"), 
											  rs.getString("lname"), 
											  rs.getString("username"), 
											  rs.getString("description"), 
											  rs.getString("event"), 
											  rs.getString("justification"), 
											  rs.getFloat("cost"), 
											  rs.getInt("request_id"),
											  rs.getDate("date_sub"));
					
					requestList.add(request);
				}
				
				
			}catch(SQLException e){
				e.printStackTrace();
				logger.error("Couldn't connect");
			}finally{
				try {stmt.close();} 
				catch (SQLException e) {
					e.printStackTrace();
					System.out.println("Couldn't Close the Connection");
				}
			}
			
			return requestList;
		
	}

	@Override
	public void departmentHeadApproval(String id) {
		logger.info("Approval for DH attempting to connect");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "UPDATE project1_approval SET department_head_app = 1 WHERE request_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeQuery();

			
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("Couldn't connect");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Couldn't close the connection");
			}
		}		
	}

	@Override
	public List<Request> getListOfRequestsBC() {
		logger.info("Getting a list of requests");
		ResultSet rs = null;
		List<Request> requestList = new ArrayList<>();
		Statement stmt = null;
		try(Connection conn = Connections.getConnection())
		{
			String sql = "select project1_reimbursement.username, project1_reimbursement.fname, project1_reimbursement.lname, " + 
					"project1_reimbursement_details.date_sub, project1_reimbursement_details.request_id, " + 
					"project1_reimbursement_details.description,project1_reimbursement_details.event, " + 
					"project1_reimbursement_details.justification, project1_reimbursement.cost from project1_reimbursement " + 
					"full outer join project1_reimbursement_details on " + 
					"project1_reimbursement.request_id = project1_reimbursement_details.request_id " + 
					"where project1_reimbursement.request_id in " + 
					"(select request_id from project1_approval where project1_approval.department_head_app = 1 " +
					"and project1_approval.department_head_app = 1 "+
					"and project1_approval.benefits_co_app = 0) "+
					"order by project1_reimbursement.lname";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			while(rs.next()){
				logger.info("Processing the info");
				Request request = new Request(rs.getString("fname"), 
										  rs.getString("lname"), 
										  rs.getString("username"), 
										  rs.getString("description"), 
										  rs.getString("event"), 
										  rs.getString("justification"), 
										  rs.getFloat("cost"), 
										  rs.getInt("request_id"),
										  rs.getDate("date_sub"));
				
				requestList.add(request);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("Couldn't connect");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Couldn't close the connection");
			}
		}
		
		return requestList;
	}

	@Override
	public void bCApproval(String id) {
		logger.info("Connecting for BC approval");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "UPDATE project1_approval SET benefits_co_app = 1 WHERE request_id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.executeQuery();

			
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("Something went wrong with connecting");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Couldn't close the connection");
			}
		}
	}

	@Override
	public String getEvent(String id) {
		logger.info("Get EVENT connecting to DB");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT EVENT FROM PROJECT1_REIMBURSEMENT_DETAILS WHERE REQUEST_ID="+id;
			ResultSet rs = null;
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();

			return rs.getString(1);
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("Couldn't connect to DB");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Couldn't close the connection");
			}
		}
		return null;
	}

	@Override
	public Float getFund(String id) {
		logger.info("Getting funds connecting to DB");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "select funds from project1_available where username in (select username from project1_reimbursement where request_id="+id+")";
			ResultSet rs = null;
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();

			return rs.getFloat(1);
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("Couldn't connect to DB");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Can't close the connection");
			}
		}
		return (float) -1;
	}

	@Override
	public Float getCost(String id) {
		logger.info("Getting cost connecting to DB");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT COST FROM PROJECT1_REIMBURSEMENT WHERE REQUEST_ID="+id;
			ResultSet rs = null;
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();

			return rs.getFloat(1);
		}catch(SQLException e){
			e.printStackTrace();
			logger.info("Error in connecting to database");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Can't close the connection");
			}
		}
		return (float) -1;
	}

	@Override
	public void updateFunds(String username, float amount) {
		logger.info("Updating Funds and connecting to DB");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "UPDATE PROJECT1_AVAILABLE SET FUNDS=? WHERE USERNAME=?";
			stmt = conn.prepareStatement(sql);
			stmt.setFloat(1, amount);
			stmt.setString(2, username);
			stmt.executeQuery();

		}catch(SQLException e){
			e.printStackTrace();
			logger.error("Error in connecting to the database");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Error in closing the connection");
			}
		}
	}

	@Override
	public String getUsername(String id) {
		logger.info("Connecting to database");
		PreparedStatement stmt = null; 
		try(Connection conn = Connections.getConnection())
		{
			String sql = "SELECT USERNAME FROM PROJECT1_REIMBURSEMENT WHERE REQUEST_ID="+id;
			ResultSet rs = null;
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			rs.next();

			return rs.getString(1);
		}catch(SQLException e){
			e.printStackTrace();
			logger.error("Can't connect to DB");
		}finally{
			try {stmt.close();} 
			catch (SQLException e) {
				e.printStackTrace();
				logger.error("Can't close connection");
			}
		}
		return null;
	}
	
	
	
	
	
	/*public List<Npc> selectAllNpc() {
		
		Statement stmt = null; // Simple SQL query to be executed
		ResultSet rs = null; //Object that holds query results
		List<Npc> npcs = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT * FROM npc";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
						
			

			while(rs.next()){
				Npc npc = new Npc(
						rs.getInt(1),
						rs.getString("npc_name"),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5)
						);
				npcs.add(npc);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
			close(rs);
		}
		
		return npcs;
	}*/
}
