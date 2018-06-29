package Project0.RevatureDatabase;

import java.io.*;
import Project0.*;

/**
 * ToDisk encapsulates the logic of writing to persistent storage.
 */
public class ToDisk implements LogReference{
	/**
	 * connection contains a reference to persistent storage.
	 */
	private FileOutputStream connection;
	/**
	 * record contains the data written to persistent storage.
	 */
	private ObjectOutputStream record;

	/**
	 * This constructor opens up a connection to persistent storage.
	 * 
	 * @param filename name of the persistent storage file.
	 * @throws FileNotFoundException
	 */
	public ToDisk(String filename) throws FileNotFoundException {
		logger.debug("Project0/RevatureDatabase/ToDisk.java: " + 
	                 "Constructing ToDisk().");
		connection = new FileOutputStream(filename);
		logger.debug("Project0/RevatureDatabase/ToDisk.java: " + 
		             "Constructed ToDisk().");
	}

	public void write(Object o) {
		logger.debug("Project0/RevatureDatabase/ToDisk.java: " + 
	                 "Entered write().");
		
		try{
			record = new ObjectOutputStream(connection);
			record.writeObject(o);
			record.close();
		}

		catch(IOException ioe){
			logger.error("Project0/RevatureDatabase/ToDisk.java: " + 
		                 "Writing to persistent storage failed!");
			ioe.printStackTrace();
		}
		
		logger.debug("Project0/RevatureDatabase/ToDisk.java: " + 
		             "Exiting write().");
	}
	
	/**
	 * close() closes the connection to persistent storage.
	 * 
	 * @throws IOException
	 */
	public void close() throws IOException {
		logger.debug("Project0/RevatureDatabase/ToDisk.java: " + 
					 "Entered close().");
		connection.close();
		logger.debug("Project0/RevatureDatabase/ToDisk.java: " + 
					 "Exiting close().");
	}
}
