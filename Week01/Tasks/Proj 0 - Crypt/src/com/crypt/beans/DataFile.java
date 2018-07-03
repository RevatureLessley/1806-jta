package com.crypt.beans;

import java.io.File;

public class DataFile {
	private Integer id;
	private Integer ownerId;
	private File filepath;
	private String fileName;
	private byte[] uploadedData;

	public DataFile() {
		super();
	}

	public DataFile(Integer id, Integer ownerId, String fileName, File filepath, byte[] uploadedData) {
		super();
		this.id = id;
		this.ownerId = ownerId;
		this.filepath = filepath;
		this.fileName = fileName;
		this.uploadedData = uploadedData;
	}

	public DataFile(Integer ownerId, File filepath, String fileName, byte[] uploadedData) {
		super();
		this.ownerId = ownerId;
		this.filepath = filepath;
		this.fileName = fileName;
		this.uploadedData = uploadedData;
	}

	public DataFile(File file, String name, byte[] read) {
		this.filepath = file;
		this.fileName = name;
		uploadedData = read;
	}

	public byte[] getUploadedData() {
		return uploadedData;
	}

	public void setUploadedData(byte[] uploadedData) {
		this.uploadedData = uploadedData;
	}

	public File getFilepath() {
		return filepath;
	}

	public void setFilepath(File filepath) {
		this.filepath = filepath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "DataFile [uploadedData=" + uploadedData + ", filepath=" + filepath + ", fileName=" + fileName + "]";
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getOwnerId() {
		return ownerId;
	}


	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}


}
