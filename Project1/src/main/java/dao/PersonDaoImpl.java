package dao;

import static util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.person;
import util.Connections;

public class PersonDaoImpl implements PersonDao{

	public person getPersonByUserId(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT person.person_name, role_table.role_security_level, person.person_id," +
				"person.person_superior FROM person \r\n" + 
				"FULL OUTER JOIN role_table\r\n" + 
				"on person.roll_id = role_table.role_id\r\n" + 
				"WHERE person.person_id = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				return new person(
						rs.getString(1),
						rs.getInt(2),
						rs.getInt(3),
						rs.getInt(4)
						);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		
		return null;
	}
	

	public Integer checkPassword(String uName, String pWord) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT person.person_password, person.person_id FROM person WHERE person.person_username = ?";
		
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, uName);
			rs = ps.executeQuery();
			while(rs.next()) {
				if(pWord.equals(rs.getString(1))) {
					return rs.getInt(2);
				}
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return -1;
	}
}
