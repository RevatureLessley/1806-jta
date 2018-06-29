package Project0_PartII.RevatureDatabase;

import java.io.*;
import Project0_PartII.*;

/**
 * FromDisk encapsulates the logic of reading from persistent storage.
 */
public class FromDisk implements LogReference {
	/**
	 * connection contains a reference to persistent storage.
	 */
	private FileInputStream connection;
	/**
	 * record contains the data read from persistent storage.
	 */
	private ObjectInputStream record;

	/**
	 * This constructor opens up a connection to persistent storage.
	 * 
	 * @param filename name of the persistent storage file.
	 * @throws FileNotFoundException
	 */
	public FromDisk(String filename) throws FileNotFoundException {
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
	                 "Constructing FromDisk().");
		connection = new FileInputStream(filename);
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
		             "Constructed FromDisk().");
	}

	public Object read() {
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
					 "Entered read().");
		
		try{
			record = new ObjectInputStream(connection);
			Object a = record.readObject();
			record.close();
			return a;
		}

		catch(IOException ioe){
			logger.error("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
		                 "Reading from persistent storage failed!");
			ioe.printStackTrace();
		}

		catch(ClassNotFoundException cnfe){
			logger.error("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
		                 "Data in persistent storage corrupted!");
			cnfe.printStackTrace();
		}
		
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
		             "Exiting read().");
		
		return null;
	}

	/**
	 * close() closes the connection to persistent storage.
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
					 "Entered close().");
		connection.close();
		logger.debug("Project0_PartII/RevatureDatabase/FromDisk.java: " + 
		             "Exiting close().");
	}
}
