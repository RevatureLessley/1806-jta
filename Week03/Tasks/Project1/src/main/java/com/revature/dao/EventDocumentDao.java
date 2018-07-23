package com.revature.dao;

import static com.revature.utils.CloseStreams.close;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.bean.EventDocument;
import com.revature.utils.Connections;

public class EventDocumentDao {

	/**
	 * Add a document to the eventDocument table. Documents are associated with
	 * specific event. They also include an InputStream which is converted to a
	 * blob; the input stream is byte data of an uploaded file
	 * 
	 * @param evId
	 * @param docName
	 * @param fileData
	 * @param isApproval
	 * @return
	 */
	public boolean addDocument(Integer evId, String docName, InputStream fileData, Integer isApproval) {
		CallableStatement stmt = null;

		try (Connection conn = Connections.getConnection()) {

			stmt = conn.prepareCall("{CALL ADD_DOCUMENT(?, ?, ?, ?)}");

			stmt.setInt(1, evId);
			stmt.setString(2, docName);
			stmt.setBlob(3, fileData);
			stmt.setInt(4, isApproval);

			stmt.execute();
			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(stmt);
		}
		return false;
	}

	/**
	 * Select All documents associated with the given event id. Does not read the
	 * blob into EventDocument bean.
	 * 
	 * @param eventId
	 * @return
	 */
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

	/**
	 * Accesses the database and gets an array of bytes associated with the blob
	 * stored for the specified event document.
	 * 
	 * @param evdocId
	 * @return
	 */
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
