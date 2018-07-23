package com.revature.dao;

import static com.revature.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.util.Connections;

/**
 * Event Data Access Object
 * <br>Used to grab information regarding 
 * events from the database.
 * <br>
 * @author Logan Brewer
 *
 */
public class EventDao 
{
	/**
	 * Grab the reimbursement percent for an event given
	 * an events ID.
	 * @param eventId
	 * @return
	 */
	public Integer getReimbursementPercent(Integer eventId)
	{
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT reimbursement_percent FROM event WHERE event_id = ?";
		
		try(Connection conn = Connections.getConnection())
		{
			ps = conn.prepareStatement(sql);
			ps.setInt(1, eventId);
			rs = ps.executeQuery();
			while(rs.next())
			{
				return rs.getInt(1);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			close(rs);
			close(ps);
		}
		return 0;
	}
}
