package Project1.Beans;

import java.sql.Blob;

public class Attachment {
	private String filename;
	private Blob file;
	
	/**
	 * @param filename
	 * @param file
	 */
	public Attachment(String filename, Blob file) {
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