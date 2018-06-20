package RevatureDatabase;

import java.io.*;
import java.lang.*;

public class FromDisk{
	private FileInputStream connection;
	private ObjectInputStream record;

	public FromDisk(String filename) throws FileNotFoundException {
		connection = new FileInputStream(filename);
	}

	public Object read() {

		try{
			record = new ObjectInputStream(connection);
			Object a = record.readObject();
			record.close();
			return a;
		}

		catch(IOException ioe){
			System.err.println("Reading from persistent storage failed!");
		}

		catch(ClassNotFoundException cnfe){
			System.err.println("Data in persistent storage corrupted!");
		}
		
		return null;
	}

	public void close() throws IOException {
		connection.close();
	}
}
