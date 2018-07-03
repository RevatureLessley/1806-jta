package com.revature.dao;

import static com.revature.utils.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import com.revature.beans.BInterestStamp;
import com.revature.utils.Connections;

public class InterestStampDao {

	public void insert(BInterestStamp t) {

		PreparedStatement ps = null;
		ResultSet rs = null;

		String sql = "INSERT INTO interest_table (it_time) VALUES (TO_TIMESTAMP(?, 'YYYY-MM-DD HH24:MI:SS.FF')) ";

		try (Connection conn = Connections.getConnection()) {
			ps = conn.prepareStatement(sql);

			ps.setString(1, getTimestamp(t.getLocalDateTime()));

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs);
			close(ps);
		}

	}


	public BInterestStamp selectNewest() {
		Statement stmt = null; // simple sql query to be executed
		ResultSet rs = null;

		String sql = "SELECT * FROM interest_table WHERE it_id IN (SELECT MAX(it_id) FROM interest_table)";
		BInterestStamp bInterestStamp = null;

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				LocalDateTime ldt = getLocalDateTime(rs.getString(2));
				bInterestStamp = new BInterestStamp(rs.getInt(1), ldt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return bInterestStamp;
	}


	private LocalDateTime getLocalDateTime(String s){
		s = s.replaceAll(" ", "T");

		LocalDateTime ldt = LocalDateTime.parse(s);
		return ldt;
	}
	
	
	private String getTimestamp(LocalDateTime t) {
		t.truncatedTo(ChronoUnit.MINUTES);
		String stamp = t.toString();
		stamp = stamp.replaceAll("T", " ");

		
		return stamp;
	}

}
