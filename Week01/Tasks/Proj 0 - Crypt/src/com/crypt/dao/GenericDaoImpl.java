package com.crypt.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crypt.util.Connections;
import static com.crypt.util.CloseStreams.*;

public abstract class GenericDaoImpl<T, K> implements GenericDao<T, K> {
	
	@Override
	public List<T> selectAll() {
		Statement stmt = null; // Simple SQL query to be executed
		ResultSet rs = null; //Object that holds query results
		List<T> npcs = new ArrayList<>();
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT * FROM npc";
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
						
			

			while(rs.next()){
				T t = new T(
						rs.getInt(1),
						rs.getString("npc_name"),
						rs.getInt(3),
						rs.getInt(4),
						rs.getInt(5)
						);
				npcs.add(t);
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
			close(rs);
		}
		
		return npcs;
		return null;
	}

	@Override
	public void insert(T t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T selectById(K id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer deleteById(K id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateById(K id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertViaSp(T t) {
		// TODO Auto-generated method stub
		return false;
	}

}
