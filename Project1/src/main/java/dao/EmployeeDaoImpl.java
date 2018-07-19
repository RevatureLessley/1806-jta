package dao;

import static util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.employee;
import beans.person;
import beans.request;
import util.Connections;

public class EmployeeDaoImpl implements EmployeeDao {

	@Override
	public ArrayList<employee> getEmployeesBySupervisor(Integer id) {
		ArrayList<employee> temp = new ArrayList<employee>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT employee.employee_id, employee.max_reimbursement FROM person\r\n" + 
				"INNER JOIN employee\r\n" + 
				"on employee.person_id = person.person_id\r\n" + 
				"WHERE person.person_superior = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				PersonDaoImpl PDI = new PersonDaoImpl();
				
				temp.add(new employee(rs.getInt(1), rs.getDouble(2), PDI.getPersonByUserId(rs.getInt(3))));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		return temp;
	}

	@Override
	public ArrayList<person> getPeopleBySuperior(Integer id) {
		ArrayList<person> temp = new ArrayList<person>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "SELECT person.person_name, role_table.role_security_level, person.person_id," +
				"person.person_superior FROM person \r\n" + 
				"FULL OUTER JOIN role_table\r\n" + 
				"on person.roll_id = role_table.role_id\r\n" + 
				"WHERE person.person_superior = ?";
		
		try(Connection conn = Connections.getConnection()){
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()){
				temp.add (new person(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			close(rs);
			close(ps);
		}
		System.out.println("getPeopleBySuperior with id: " + id + " size=" +temp.size());
		return temp;
	}

	@Override
	public ArrayList<employee> getEmployeesByHead(Integer id) {
		ArrayList<employee> temp = new ArrayList<employee>();
		for(person p: getPeopleBySuperior(id)) {
			for(employee p2: getEmployeesBySupervisor(p.getPersonId())) {
				temp.add(p2);
			}
		}
		return temp;
	}

	@Override
	public ArrayList<employee> getEmployeesByHeadSupervisor(Integer id) {
		return getEmployeesBySupervisor(id);
	}

	@Override
	public ArrayList<employee> getEmployeesByCooperate(Integer id) {
		ArrayList<employee> temp = new ArrayList<employee>();
		for(person p: getPeopleBySuperior(id)) {
			for(employee p2: getEmployeesByHead(p.getPersonId())) {
				temp.add(p2);
			}
		}
		return temp;
	}

	public void updateEmployeeRequests(employee e) {
		e.clearRequests();
	}

}
