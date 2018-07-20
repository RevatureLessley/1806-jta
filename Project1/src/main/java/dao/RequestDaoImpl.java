package dao;

import static util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import beans.file;
import beans.info;
import beans.request;
import util.Connections;

public class RequestDaoImpl implements RequestDao {

	public ArrayList<request> getRequestsByEmployeeId(Integer id){
		ArrayList<request> list = new ArrayList<request>();
		PreparedStatement ps = null;
		PreparedStatement psI = null;
		PreparedStatement psF = null;
		ResultSet rs = null;
		ResultSet rsI = null;
		ResultSet rsF = null;
		
		String sql = "SELECT * FROM reimbursement_list\r\n" + 
				"INNER JOIN reimbursement\r\n" + 
				"on reimbursement_list.reimbursement_id = reimbursement.reimbursement_id\r\n" + 
				"INNER JOIN reimbursement_type\r\n" + 
				"on reimbursement.type_id = reimbursement_type.type_id\r\n" + 
				"WHERE reimbursement_list.employee_id = ?";
		String sqlI = "SELECT * FROM info_list\r\n" + 
				"INNER JOIN info\r\n" + 
				"on info_list.info_id = info.info_id\r\n" + 
				"WHERE info_list.reimbursement_id = ?";
		String sqlF = "SELECT * FROM file_list\r\n" + 
				"INNER JOIN file_table\r\n" + 
				"on file_list.file_table_id = file_table.file_table_id\r\n" + 
				"WHERE file_list.reimbursement_id = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				request temp = new request(
						rs.getInt("reimbursement_id"),
						rs.getDouble("full_ammount"),
						rs.getDouble("cooperate_ammount"),
						rs.getInt("status"),
						addDays(rs.getDate("auto_approval"),0),
						addDays(rs.getDate("created_date"),0),
						addDays(rs.getDate("event_date"),0),
						rs.getString("grading_format"),
						rs.getString("event_description"),
						rs.getString("event_justification"),
						rs.getString("event_location"),
						rs.getDouble("type_value"),
						rs.getString("type_name"),
						rs.getInt("type_id"));
				psI = conn.prepareStatement(sqlI);
				psI.setInt(1, rs.getInt("reimbursement_id"));
				rsI = psI.executeQuery(); 

				ArrayList<info> infos = new ArrayList<info>();

				while(rsI.next()) {
					infos.add(new info(rsI.getString("info_data")));
				}
				for(info i: infos) {
					temp.addInfo(i);
				}

				psF = conn.prepareStatement(sqlF);
				psF.setInt(1, rs.getInt("reimbursement_id"));
				rsF = psF.executeQuery(); 
				ArrayList<file> files = new ArrayList<file>();

				while(rsF.next()) {
					files.add(new file(rsF.getString("file_table_address")));
				}
				for(file f: files) {
					temp.addFile(f);
				}
				
				list.add(temp);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		
		
		
		return list;
	}

	@Override
	public void updateRequest(request r) {
		PreparedStatement ps = null;
		
		try(Connection conn = Connections.getConnection()){

			String sql = "UPDATE request SET "
					+ "status = ?"
					+ "cooperate_ammount = ?"
					+ "auto_approval = ?"
					+ "WHERE request.request_id = ?";
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1,r.getStatus());
			ps.setDouble(2, r.getCooperateAmmount());
			if(r.getStatus()==2 ||r.getStatus() == 3) {
				java.sql.Date temp = new java.sql.Date(addDays(newRequestDate(), 3).getTime());
				ps.setDate(3, temp);
			}
			else {
				ps.setDate(3, null);
			}
			ps.setInt(4, r.getId());
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(ps);
		}
	}

	@Override
	public void createNewRequest(request r, Integer empID) {
		CallableStatement cs = null;
		CallableStatement cs2 = null;
		String sql = "INSERT INTO reimbursement VALUES (NULL, ?, NULL,? ,2, ?, ?, ?, ?, ?,?, ?)";
		String sql2 = "Call insertIntoReimbursementList(?)";
		try(Connection conn = Connections.getConnection()){
			cs = conn.prepareCall(sql);
			
			cs.setDouble(1, r.getFullAmmount());
			cs.setInt(2, r.getTypeId());
			cs.setDate(3, newRequestDate());
			cs.setDate(4, convertDate(r.getCreationDate()));
			cs.setDate(5, convertDate(r.getEventDate()));
			cs.setString(6, r.getGradingFormat());
			cs.setString(7, r.getEventDescription());
			cs.setString(8, r.getEventJustification());
			cs.setString(9, r.getEventLocation());

			cs.execute();
			
			cs2 = conn.prepareCall(sql2);
			cs2.setInt(1, empID);
			cs2.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(cs);
		}
	}
	
	public static java.sql.Date convertDate(Date date){
		return new java.sql.Date(date.getTime());
		
	}
	public static java.sql.Date newRequestDate() {
		java.sql.Date temp = new java.sql.Date(addDays(newRequestDate(), 3).getTime());
		return temp;
	}
	public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

	@Override
	public void createNewFile(String info, Integer reqID) {
		CallableStatement cs = null;
		CallableStatement cs2 = null;
		String sql = "INSERT INTO File VALUES (NULL, ?)";
		String sql2 = "Call insertIntoFileList(?)";
		try(Connection conn = Connections.getConnection()){
			cs = conn.prepareCall(sql);
			
			cs.setString(1, info);

			cs.execute();
			
			cs2 = conn.prepareCall(sql2);
			cs2.setInt(1, reqID);
			cs2.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(cs);
		}
	}

	@Override
	public void createNewInfo(String info, Integer reqID) {
		CallableStatement cs = null;
		CallableStatement cs2 = null;
		String sql = "INSERT INTO info VALUES (NULL, ?)";
		String sql2 = "Call insertIntoInfoList(?)";
		try(Connection conn = Connections.getConnection()){
			cs = conn.prepareCall(sql);
			
			cs.setString(1, info);

			cs.execute();
			
			cs2 = conn.prepareCall(sql2);
			cs2.setInt(1, reqID);
			cs2.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(cs);
		}
	}

}
