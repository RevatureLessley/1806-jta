package Project1.DAOs;

import java.sql.*;
import Project1.*;

public class EventTypeDAOImp {
	
	public void selectEventTypes() {
		Statement s = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * " + 
						  "FROM Event_Type";
		
		try(Connection connection = DatabaseConnection.connect()) {
			s = connection.createStatement();
			rs = s.executeQuery(sqlQuery);
			
			while(rs.next()) {
				EventType.insertType(rs.getInt("eve_typ_id"), 
									rs.getString("eve_typ_value"),
									rs.getDouble("eve_typ_coverage"));
			}
			
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		finally {
			DatabaseConnection.close(s);
			DatabaseConnection.close(rs);
		}
	
	}
}
