package Tasks.RevatureDatabase;

import java.io.*;

public class ToDisk{
	private FileOutputStream connection;
	private ObjectOutputStream record;

	public ToDisk(String filename) throws FileNotFoundException {
		connection = new FileOutputStream(filename);
	}

	public void write(Object o) {
		
		try{
			record = new ObjectOutputStream(connection);
			record.writeObject(o);
			record.close();
		}

		catch(IOException ioe){
			System.err.println("Writing to persistent storage failed!");
			ioe.printStackTrace();
		}
	}

	public void close() throws IOException {
		connection.close();
	}
}
