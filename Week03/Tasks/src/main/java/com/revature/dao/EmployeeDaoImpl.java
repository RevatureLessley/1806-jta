package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.Employee;
import com.revature.beans.RForm;
import com.revature.util.Connections;
public class EmployeeDaoImpl {
	public Boolean insertEmployeeViaSp(Employee employee) {
		CallableStatement stmt = null; 
		
		try(Connection conn = Connections.getConnection()){

			stmt = conn.prepareCall("{call insertNewEmployee(?,?,?,?,?,?,?)}");
			
			stmt.setString(1, employee.getUserN());
			stmt.setString(2, employee.getPassW());
			stmt.setString(3, employee.getFirstN());
			stmt.setString(4, employee.getLastN());
			stmt.setInt(5, employee.getDirSupId());
			stmt.setInt(6, employee.getDepId());
			stmt.setInt(7,employee.getEmpType());
			

			
			stmt.execute(); //Returns amount rows effected;
			return true;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(stmt);
		}		
		return false;
	}
	
	public Employee selectEmployeeByUserN(String userN) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Employee WHERE username = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setString(1, userN);
			rs = ps.executeQuery();
			while(rs.next()){
				return new Employee(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getDouble(8),
						rs.getDouble(9),
						rs.getInt(10),
						rs.getDouble(11)
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
	
	public Employee selectEmployeeById(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Employee WHERE emp_id = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				return new Employee(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getDouble(8),
						rs.getDouble(9),
						rs.getInt(10),
						rs.getDouble(11)
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
	
	public List<Employee> selectEmployeeByDep(int dep) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM Employee WHERE dep_id = ? AND emp_type_id >= 1";
		List<Employee> emps = new ArrayList<>();
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dep);
			rs = ps.executeQuery();
			while(rs.next()){
				emps.add(new Employee(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getInt(7),
						rs.getDouble(8),
						rs.getDouble(9),
						rs.getInt(10),
						rs.getDouble(11)
						));
			}
			return emps;
			
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}
	
	public List<RForm> selectRformByEmployeeId(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM RForm WHERE emp_id = ?";
		List<RForm> rforms = new ArrayList<>();
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				rforms.add(new RForm(
						rs.getInt(1),
						rs.getInt(2),
						rs.getDate(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getDouble(15),
						rs.getString(16),
						rs.getInt(17),
						rs.getInt(18),
						rs.getInt(19),
						rs.getInt(20),
						rs.getInt(21)
						));
			}
			return rforms;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}
	
	public List<RForm> selectRformBySupId(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM RForm WHERE dir_sup_id= ? AND app_lvl = 0";
		List<RForm> rforms = new ArrayList<>();
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				rforms.add(new RForm(
						rs.getInt(1),
						rs.getInt(2),
						rs.getDate(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getDouble(15),
						rs.getString(16),
						rs.getInt(17),
						rs.getInt(18),
						rs.getInt(19),
						rs.getInt(20),
						rs.getInt(21)
						));
			}
			return rforms;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}
	
	public List<RForm> selectRformHead(int id, int depid) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM RForm WHERE emp_id != ? AND app_lvl = 1 AND dep_id = ?";
		List<RForm> rforms = new ArrayList<>();
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2,depid);
			rs = ps.executeQuery();
			while(rs.next()){
				rforms.add(new RForm(
						rs.getInt(1),
						rs.getInt(2),
						rs.getDate(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getDouble(15),
						rs.getString(16),
						rs.getInt(17),
						rs.getInt(18),
						rs.getInt(19),
						rs.getInt(20),
						rs.getInt(21)
						));
			}
			return rforms;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}
	
	public List<RForm> selectRformBen(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM RForm WHERE emp_id != ? AND app_lvl = 2";
		List<RForm> rforms = new ArrayList<>();
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				rforms.add(new RForm(
						rs.getInt(1),
						rs.getInt(2),
						rs.getDate(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getDouble(15),
						rs.getString(16),
						rs.getInt(17),
						rs.getInt(18),
						rs.getInt(19),
						rs.getInt(20),
						rs.getInt(21)
						));
			}
			return rforms;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}
	public List<RForm> selectRformBenHead(int id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM RForm WHERE emp_id != ? AND app_lvl = 3";
		List<RForm> rforms = new ArrayList<>();
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				rforms.add(new RForm(
						rs.getInt(1),
						rs.getInt(2),
						rs.getDate(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getDouble(15),
						rs.getString(16),
						rs.getInt(17),
						rs.getInt(18),
						rs.getInt(19),
						rs.getInt(20),
						rs.getInt(21)
						));
			}
			return rforms;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}
	
	public List<RForm> selectRformGraded(int id,List<RForm> rforms) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM RForm WHERE emp_id != ? AND app_lvl = 5 AND grade_format = 1";
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				rforms.add(new RForm(
						rs.getInt(1),
						rs.getInt(2),
						rs.getDate(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getDouble(15),
						rs.getString(16),
						rs.getInt(17),
						rs.getInt(18),
						rs.getInt(19),
						rs.getInt(20),
						rs.getInt(21)
						));
			}
			return rforms;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}
	
	public List<RForm> selectRformPresentation(int id,List<RForm> rforms) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM RForm WHERE dir_sup_id = ? AND app_lvl = 4 AND grade_format = 0";
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				rforms.add(new RForm(
						rs.getInt(1),
						rs.getInt(2),
						rs.getDate(3),
						rs.getString(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getString(7),
						rs.getString(8),
						rs.getInt(9),
						rs.getInt(10),
						rs.getInt(11),
						rs.getInt(12),
						rs.getInt(13),
						rs.getInt(14),
						rs.getDouble(15),
						rs.getString(16),
						rs.getInt(17),
						rs.getInt(18),
						rs.getInt(19),
						rs.getInt(20),
						rs.getInt(21)
						));
			}
			return rforms;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return null;
	}
	
	public Boolean updatePending(double amount,int empid) {
		PreparedStatement ps = null;
		String sql = "UPDATE Employee SET pending_reim = ? WHERE emp_id = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setInt(2, empid);
			ps.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(ps);
		}
		
		return false;
	}
	
	public Boolean updateAvailableReim(double amount,int empid) {
		PreparedStatement ps = null;
		String sql = "UPDATE Employee SET available_reim = ? WHERE emp_id = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setInt(2, empid);
			ps.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(ps);
		}
		
		return false;
	}
	
	public Boolean updateAwardedReim(double amount,int empid) {
		PreparedStatement ps = null;
		String sql = "UPDATE Employee SET awarded_reim = ? WHERE emp_id = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setDouble(1, amount);
			ps.setInt(2, empid);
			ps.executeUpdate();
			return true;
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(ps);
		}
		
		return false;
	}
}
