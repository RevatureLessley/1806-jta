package Project1.DAOs;

import java.sql.*;
import Project1.*;
import Project1.Beans.*;

public class EventDAOImp implements LogReference {
	
	public Event selectEvent(ResultSet rs)
	{
		Event event = null;
		
		try {
			event =  new Event(rs.getString("E_EventType"),
							   rs.getDouble("E_PercentCoverage"),
							   rs.getBigDecimal("E_Cost"),
							   rs.getTimestamp("E_Date"),
							   rs.getString("E_Description"),
							   rs.getString("E_Location"),
							   rs.getString("E_WorkTimeMissed")
						 );
			
			return event;
		}
		
		catch(SQLException e) {
			DatabaseConnection.close(rs);
			e.printStackTrace();
		}

		return event;
	}
}
