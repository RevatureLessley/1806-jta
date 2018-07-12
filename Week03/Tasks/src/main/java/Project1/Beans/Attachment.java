package Project1.Beans;

import java.io.*;

public class Attachment {
	private String filename;
	private InputStream file;
	
	/**
	 * @param filename
	 * @param file
	 */
	public Attachment(String filename, InputStream file) {
		this.filename = filename;
		this.file = file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public InputStream getFile() {
		return file;
	}

	public void setFile(InputStream file) {
		this.file = file;
	}
}