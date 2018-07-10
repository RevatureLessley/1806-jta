package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Npc;
import com.revature.util.Connections;

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
			while(rs.next()){
				return new Npc(
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5)
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

	public Npc selectNpcByName(String name) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM npc WHERE npc_name = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()){
				return new Npc(
						rs.getInt(1),
						rs.getString(2),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5)
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
	
	
	/*
	 * Try-With-Resources will close any streams you create within the
	 * parenthesis' of the try blockm once the try-catch-finally has
	 * finished.
	 */
	public List<Npc> selectAllNpc() {
		
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
	}

	public Integer deleteNpcById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	public Integer updateNpc(Npc npc) {
		PreparedStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){
			String sql = "Update npc set npc_name = ?,npc_lvl = ?, " +
						"currency = ?, job_id = ? WHERE npc_id=?";
			stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, npc.getName());
			stmt.setInt(2, npc.getLvl());
			stmt.setInt(3, npc.getCurrency());
			stmt.setInt(4, npc.getJobClass());
			stmt.setInt(5, npc.getId());
			
			return stmt.executeUpdate(); //Returns amount rows effected;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}
		return 0;
	}

	@Override
	public Boolean insertNpcViaSp(Npc npc) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertIntoNpc(?,?,?,?)}");
			
			stmt.setString(1, npc.getName());
			stmt.setInt(2, npc.getLvl());
			stmt.setInt(3, npc.getCurrency());
			stmt.setInt(4, npc.getJobClass());

			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}

}
