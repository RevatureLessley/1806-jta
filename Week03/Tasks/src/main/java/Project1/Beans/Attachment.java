package Project1.Beans;

import java.math.*;
import java.sql.*;
import org.codehaus.jackson.annotate.*;

public class Attachment {
	private String category;
	private String mime;
	private String filename;
	private BigInteger filesize;
	@JsonIgnore
	private Blob file;
	
	/**
	 * @param filename
	 * @param file
	 */
	
	public Attachment(String category, String mime, String filename,
					  BigInteger filesize, Blob file) {
		this.category = category;
		this.mime = mime;
		this.filename = filename;
		this.filesize = filesize;
		this.file = file;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public String getMime() {
		return mime;
	}

	public void setMime(String mime) {
		this.mime = mime;
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

	public Blob getFile() {
		return file;
	}

	public void setFile(Blob file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "Attachment [category=" + category + ", mime=" + mime + ", filename=" + filename + ", filesize="
				+ filesize + ", file=" + file + "]";
	}
}