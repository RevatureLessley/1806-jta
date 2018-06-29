package com.revature.dao;

import static com.revature.utils.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.revature.beans.Npc;
import com.revature.utils.Connections;

public class NpcDaoImpl implements NpcDao{

	public void insertNpc(Npc npc) {
		// TODO Auto-generated method stub
		
	}

	public Npc selectNpcById(Integer id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM npc WHERE npc_id = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				return new Npc(rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();			
		}finally {
			close(rs);
			close(ps);
		}
		
		
		return null;
	}

	
	public List<Npc> selectAllNpc() {
		Statement stmt = null; //simple sql query to be executed
		Statement stmtClass = null;
		ResultSet rs = null;
		ResultSet rsClasses = null;
		List<Npc> ls = new ArrayList<Npc>();

		
		try(Connection conn = Connections.getConnection()) {
			String sql = "SELECT * FROM npc";
			String sqlClass = "SELECT * FROM job_class";
			
			stmt = conn.createStatement();
			stmtClass = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			rsClasses = stmtClass.executeQuery(sqlClass);
			
			
			
			Map<Integer, String> classes = new HashMap<>();
			
			while(rsClasses.next()) {
				classes.put(rsClasses.getInt(1), rsClasses.getString(2));
			}
			
			while(rs.next()) {
				Npc npc = new Npc(
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5)
						);		
				
				npc.setJobClassString(classes.get(npc.getJobClass()));
				
				ls.add(npc);
			}
			
			
		
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close(stmt);
			close(rs);
			close(stmtClass);
			close(rsClasses);
		}
		
		
		return ls;
	}

	public Integer deleteNpcById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateNpc(Npc npc) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "UPDATE npc SET npc_name = ?, npc_lvl = ?, currency = ? WHERE npc_id = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, npc.getName());
			ps.setInt(2, npc.getLvl());
			ps.setInt(3, npc.getCurrency());
			ps.setInt(4, npc.getId());
			
			rs = ps.executeQuery();
			
		}catch(SQLException e) {
			e.printStackTrace();			
		}finally {
			close(rs);
			close(ps);
		}
		
		
		return null;
	}

}
