package com.trms.beans;

import java.io.File;
import java.util.Arrays;

public class DataFile {
	//    fileID number(6),
	//    requestID number(6),
	//    fileName varchar2(500),
	//    fileType varchar2(15),
	//    filePath varchar2(1024),
	//    data Blob,
	private int fileID, requestID;
	private File file;
	private String fileType;
	private byte[] data;
	
	public DataFile() {
		super();
	}

	public DataFile(int fileID, int requestID, String fileType, String filePath, byte[] data) {
		this.fileID = fileID;
		this.requestID = requestID;
		this.file = new File(filePath);
		this.fileType = fileType;
		this.data = data;
	}
	
	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public int getFileID() {
		return fileID;
	}

	public void setFileID(int fileID) {
		this.fileID = fileID;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataFile [fileID=" + fileID + ", requestID=" + requestID + ", file=" + file + ", fileType=" + fileType
				+ ", data=" + Arrays.toString(data) + "]";
	}
}
