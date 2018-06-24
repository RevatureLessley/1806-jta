package Project0.RevatureDatabase;

import java.io.*;
import Project0.*;

public class FromDisk implements LogReference {
	private FileInputStream connection;
	private ObjectInputStream record;

	public FromDisk(String filename) throws FileNotFoundException {
		logger.debug("Project0/RevatureDatabase/FromDisk.java: " + 
	                 "Constructing FromDisk().");
		connection = new FileInputStream(filename);
		logger.debug("Project0/RevatureDatabase/FromDisk.java: " + 
		             "Constructed FromDisk().");
	}

	public Object read() {
		logger.debug("Project0/RevatureDatabase/FromDisk.java: " + 
					 "Entered read().");
		
		try{
			record = new ObjectInputStream(connection);
			Object a = record.readObject();
			record.close();
			return a;
		}

		catch(IOException ioe){
			logger.error("Project0/RevatureDatabase/FromDisk.java: " + 
		                 "Reading from persistent storage failed!");
			ioe.printStackTrace();
		}

		catch(ClassNotFoundException cnfe){
			logger.error("Project0/RevatureDatabase/FromDisk.java: " + 
		                 "Data in persistent storage corrupted!");
			cnfe.printStackTrace();
		}
		
		logger.debug("Project0/RevatureDatabase/FromDisk.java: " + 
		             "Exiting read().");
		
		return null;
	}

	public void close() throws IOException {
		logger.debug("Project0/RevatureDatabase/FromDisk.java: " + 
					 "Entered close().");
		connection.close();
		logger.debug("Project0/RevatureDatabase/FromDisk.java: " + 
		             "Exiting close().");
	}
}
