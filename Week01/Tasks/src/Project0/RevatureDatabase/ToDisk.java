package Project0.RevatureDatabase;

import java.io.*;
import Project0.*;

public class ToDisk implements LogReference{
	private FileOutputStream connection;
	private ObjectOutputStream record;

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

	public void close() throws IOException {
		logger.debug("Project0/RevatureDatabase/ToDisk.java: " + 
					 "Entered close().");
		connection.close();
		logger.debug("Project0/RevatureDatabase/ToDisk.java: " + 
					 "Exiting close().");
	}
}
