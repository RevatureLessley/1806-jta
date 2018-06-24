package com.crypt.filemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class FileManager<T> implements Serializable {
	private T t = null;
	/**
	 * 
	 */
	private static final long serialVersionUID = -6892556658114857048L;
	FileOutputStream fos;
	FileInputStream fis;	
	ObjectOutputStream oos;
	ObjectInputStream ois;
	private static String fileName;
	
	public FileManager(String fileName) {
		super();
		FileManager.fileName = fileName;
	}
	
	void crossTheStreams() throws FileNotFoundException, IOException {
		fos = new FileOutputStream(fileName);
		oos = new ObjectOutputStream(fos);
		fis = new FileInputStream(fileName);
		ois = new ObjectInputStream(fis);
	}
	void closeTheStreams() throws IOException{
		fos.close();
		oos.close();
		fis.close();
		ois.close();
	}
	
	void writeObject(T obj){
		try {
			crossTheStreams();
			oos.writeObject(obj);
			closeTheStreams();
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); } 
	}
	T readObject(){
		try {
			crossTheStreams();
			if(ois.available() > 0) t = (T)ois.readObject();
			closeTheStreams();
		} 
		catch (FileNotFoundException e) { e.printStackTrace(); } 
		catch (IOException e) { e.printStackTrace(); }
		catch (ClassNotFoundException e) { e.printStackTrace(); }
		
		return t;
	}
	
	public boolean fileExists(){ return new File(fileName).exists(); }
	
}
