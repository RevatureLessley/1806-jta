package com.crypt.filemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class FileManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6892556658114857048L;
	FileOutputStream fos;
	FileInputStream fis;	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	private static String filePath;
	
	public FileManager(String fileName) {
		super();
		FileManager.filePath = fileName;
	}
	
	void crossTheStreams() throws FileNotFoundException, IOException {
		fos = new FileOutputStream(filePath);
		oos = new ObjectOutputStream(fos);
		fis = new FileInputStream(filePath);
		ois = new ObjectInputStream(fis);
	}
	void closeTheStreams() throws IOException{
		fos.close();
		oos.close();
		fis.close();
		ois.close();
	}
	
	public void writeObject(byte[] b){
		try {
			crossTheStreams();
			oos.write(b);
			closeTheStreams();
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); } 
	}
	public byte[] readObject(byte[] t){
		try {
			crossTheStreams();
			if(ois.available() > 0) ois.read(t);
			closeTheStreams();
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
		
		return t;
	}
	
	public boolean fileExists(){ return new File(filePath).exists(); }
	
}
