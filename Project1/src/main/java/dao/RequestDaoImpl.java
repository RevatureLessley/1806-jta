package dao;

import static util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.file;
import beans.info;
import beans.request;
import util.Connections;

public class RequestDaoImpl implements RequestDao {

	public ArrayList<request> getRequestsByPlayerId(Integer id){
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
						rs.getDate("auto_approval"),
						rs.getDate("created_date"),
						rs.getDate("event_date"),
						rs.getString("grading_format"),
						rs.getString("event_description"),
						rs.getString("event_justification"),
						rs.getString("event_location"),
						rs.getDouble("type_value"),
						rs.getString("type_name")
						);
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
}
