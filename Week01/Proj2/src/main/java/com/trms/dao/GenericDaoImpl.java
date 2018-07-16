package com.trms.dao;

import static com.trms.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import org.apache.commons.dbutils.ResultSetIterator;

import com.trms.util.Connections;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
	@Override
	public List<T> select(String sql, Function<Object[], T> objectReturnBehaviour) {
		Statement stmt = null; // Simple SQL query to be executed
		List<T> ts = new ArrayList<>();
		try(Connection conn = Connections.getConnection()){
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE, ResultSet.HOLD_CURSORS_OVER_COMMIT);
			ResultSetIterator.iterable(stmt.executeQuery(sql)).forEach(item -> ts.add(objectReturnBehaviour.apply(item)));
			System.out.println("GenericDaoImpl:select: " + ts);
			return ts;
		}catch(SQLException e){	e.printStackTrace(); }
		finally{ close(stmt); }
		return null;
	}

	private CallableStatement procedureStmt = null; 

	private List<Object> objectAttributes = new ArrayList<>();

	private Consumer<Object> setStatementObjects = 
			object -> {//...setObject(the index of this object + 1, and the object)
				try { 
					procedureStmt.setObject(
							objectAttributes.indexOf(object) + 1, 
							object); }
				catch (SQLException e1) {	e1.printStackTrace(); }
			};
			
	@Override
	public Boolean callProcedure(T incomingGeneric, String sql, Function<T, List<Object>> loadingBehaviour) {		
		System.out.println("CallProcedure: " + incomingGeneric);
		System.out.println("CallProcedure: " + sql);
		try(Connection conn = Connections.getConnection()){
			procedureStmt = conn.prepareCall(sql);
			objectAttributes = loadingBehaviour.apply(incomingGeneric);

			objectAttributes.forEach(item -> setStatementObjects.accept(item));

			return procedureStmt.execute();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(procedureStmt);
		}		
		return true;
	}

	@Override
	public Boolean delete(String tableName, String idField, Object id) {
		PreparedStatement stmt = null;
		try(Connection conn = Connections.getConnection()){
			String sql = "DELETE FROM " + tableName + " WHERE LOWER(" + idField + ") = LOWER('" + id.toString() + "')";
			stmt = conn.prepareStatement(sql);
			return stmt.execute();			
		}catch(SQLException e){	e.printStackTrace(); }
		finally{ close(stmt); }		
		return true;
	}
}
