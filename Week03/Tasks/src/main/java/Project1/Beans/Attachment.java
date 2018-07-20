package Project1.Beans;

import java.io.*;
import java.math.*;

public class Attachment {
	private String filename;
	private BigInteger filesize;
	private InputStream file;
	
	/**
	 * @param filename
	 * @param file
	 */
	public Attachment(String filename, BigInteger filesize, InputStream file) {
		this.filename = filename;
		this.filesize = filesize;
		this.file = file;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public BigInteger getFilesize() {
		return filesize;
	}

	public void setFilesize(BigInteger filesize) {
		this.filesize = filesize;
	}

	public InputStream getFile() {
		return file;
	}

	public void setFile(InputStream file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "Attachment [filename=" + filename + ", filesize=" + filesize + ", file=" + file + "]";
	}
}