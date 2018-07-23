package com.revature.dao;

import static com.revature.utils.CloseStreams.close;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.EventDocument;
import com.revature.utils.Connections;

public class EventDocumentDao {

	public List<EventDocument> selectAllEventDocuments(Integer eventId) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT evdoc_id, ev_id, evdoc_name, evdoc_type FROM event_document WHERE ev_id = ?";

		List<EventDocument> ls = new ArrayList<EventDocument>();

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, eventId);

			rs = stmt.executeQuery();

			while (rs.next()) {
				EventDocument a = new EventDocument(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4));

				ls.add(a);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return ls;
	}

	public byte[] selectDocumentOutputSream(Integer evdocId) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT evdoc_file FROM event_document WHERE evdoc_id = ?";

		byte[] stream = null;

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareStatement(sql);

			stmt.setInt(1, evdocId);

			rs = stmt.executeQuery();

			if (rs.next()) {
				Blob blob = rs.getBlob(1);
				stream = blob.getBytes(1, (int) blob.length());
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
			close(rs);
		}

		return stream;
	}
}
