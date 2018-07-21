package Project1.DAOs;

import java.math.*;
import java.sql.*;
import java.util.HashMap;

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
			
			AttachmentDAOImp adi = new AttachmentDAOImp();
			HashMap<BigInteger, Attachment> attachments = 
					adi.selectAttachment(
							new BigInteger(rs.getString("E_ReimbursementID")),  
							"EVENT"
					);
			event.setAttachments(attachments);
			
			return event;
		}
		
		catch(SQLException e) {
			DatabaseConnection.close(rs);
			e.printStackTrace();
		}

		return event;
	}
}
