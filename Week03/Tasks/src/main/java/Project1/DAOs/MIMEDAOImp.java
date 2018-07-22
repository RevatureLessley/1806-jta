package Project1.DAOs;

import java.sql.*;
import Project1.*;

public class MIMEDAOImp {
	
	public void selectMIMETypes() {
		Statement s = null;
		ResultSet rs = null;
		String sqlQuery = "SELECT * " + 
						  "FROM MIME_Type";
		
		try(Connection connection = DatabaseConnection.connect()) {
			s = connection.createStatement();
			rs = s.executeQuery(sqlQuery);
			
			while(rs.next()) {
				MIMEType.insertType(rs.getInt("mim_typ_id"), 
									rs.getString("mim_typ_extension"),
									rs.getString("mim_typ_value"));
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
