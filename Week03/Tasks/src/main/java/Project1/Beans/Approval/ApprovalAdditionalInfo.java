package Project1.Beans.Approval;

import java.sql.*;

public class ApprovalAdditionalInfo {
	private String filename;
	private Blob file;
	
	/**
	 * @param filename
	 * @param file
	 */
	public ApprovalAdditionalInfo(String filename, Blob file) {
		this.filename = filename;
		this.file = file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Blob getFile() {
		return file;
	}

	public void setFile(Blob file) {
		this.file = file;
	}
}