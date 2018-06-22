package common.banking.application;

import java.io.*;
import java.nio.file.*;

public class data_storage {
	public data_storage() {}
	
	//Creates an account/new file to store the information
	public void create_client(customer client) {
		//Creates the file path
		String filename = "Clients\\" + client.getFirst_name()+client.getLast_name()+client.getSs_number()+".ser";
		File newfile = new File(filename);

		//Creates a Clients directory if one doesn't already exist
		File dir = new File("Clients");
		if(!dir.exists()) {dir.mkdir();}
		
		
		//Checks to see if the file exists, if it does then does nothing, if not it creates a file for the account
		if(newfile.exists()) {System.out.println("File exists, please try to login.");}
		else {
			try {
				//File is created
				newfile.createNewFile(); 
				System.out.println("New account created!");
				
				//Opening stream to serialize
				FileOutputStream file_stream = new FileOutputStream(filename);
				ObjectOutputStream out_stream = new ObjectOutputStream(file_stream);
				
				out_stream.writeObject(client);
				
				//Close COnnections
				out_stream.close();
				file_stream.close();
				System.out.println("Account serialized!");
				}
			catch (IOException e) {System.out.println("Exception occurred careful now. . . ");}
		}
	}

	//Gets account data and transfers them to a customer parameter
	public boolean open_account(customer client, String first_name, String last_name, String SS_number, StringBuffer pass_word) {
		//Creates file path
		String filename = "Clients\\" + first_name+last_name+SS_number+".ser";		
		File newfile = new File(filename);
		StringBuffer redudant = new StringBuffer();
		
		//Create a class carrier
		customer data_client = new customer("4","5","6",redudant);
		
		//Checks to see if file exists to open
		if(newfile.exists()){
		try {
			//Creating the stream 
			System.out.println("File found now opening. . . \n");
			FileInputStream file_stream = new FileInputStream(filename);
			ObjectInputStream in_stream = new ObjectInputStream(file_stream);
			
			data_client = (customer)in_stream.readObject();
			
			in_stream.close();
			file_stream.close();
			
		}catch(IOException e) {System.out.println("IO caught"); e.printStackTrace();}
		 catch(ClassNotFoundException e) {System.out.println("Class Exception caught");}
		}
		else {System.out.println("FILE CAN'T OPEN IT DOES NOT EXIST"); return false;}
		
		if (pass_word.toString().equals(data_client.getPassword().toString())) {
		client.setFirst_name(data_client.getFirst_name());
		client.setLast_name(data_client.getLast_name());
		client.setSs_number(data_client.getSs_number());
		client.setActivated(data_client.isActivated());
		client.setAdmin(data_client.isAdmin());
		client.setBalance(data_client.getBalance());
		return true;
		}
		
		else {System.out.println("Incorrect Password please try again . . .");
			return false;}
	}

	//Saves all data to account an closes
	public void close_account(customer client) {
		
		//Creates the file path
		String filename = "Clients\\" + client.getFirst_name()+client.getLast_name()+client.getSs_number()+".ser";
		File newfile = new File(filename);
		
		//Checks to see if the file exists, if it does then does nothing, if not it creates a file for the account
		if(newfile.exists()) {
			try {
				
				//Opening stream to serialize
				FileOutputStream file_stream = new FileOutputStream(filename);
				ObjectOutputStream out_stream = new ObjectOutputStream(file_stream);
				
				out_stream.writeObject(client);
				
				//Close COnnections
				out_stream.close();
				file_stream.close();
				System.out.println("Account updated logging you out . . .");
				}
			catch (IOException e) {System.out.println("Exception occurred careful now. . . ");}
		}
		
	}
	
	//Check if the account exists
	public boolean check_account (customer client) {
		//Creates the file path
			String filename = "Clients\\" + client.getFirst_name()+client.getLast_name()+client.getSs_number()+".ser";
			File newfile = new File(filename);
			return newfile.exists();
	}

	
	public void activate(String first_name, String last_name, String SS_number) {
		String filename = "Clients\\" + first_name+last_name+SS_number+".ser";		
		File newfile = new File(filename);
		StringBuffer redudant = new StringBuffer();

		
        customer data_client = new customer("4","5","6",redudant);
		
		//Checks to see if file exists to open
		if(newfile.exists()){
		try {
			//Creating the stream 
			System.out.println("File found now opening. . . \n");
			FileInputStream file_stream = new FileInputStream(filename);
			ObjectInputStream in_stream = new ObjectInputStream(file_stream);
			
			data_client = (customer)in_stream.readObject();
			
			in_stream.close();
			file_stream.close();
			
		}catch(IOException e) {System.out.println("IO caught"); e.printStackTrace();}
		 catch(ClassNotFoundException e) {System.out.println("Class Exception caught");}
		}
		else {System.out.println("Error file not found");}
		
		data_client.setActivated(true);
		close_account(data_client);
	}
}
