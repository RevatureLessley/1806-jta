package common.banking.application;

import java.io.*;
import java.nio.file.*;

public class data_storage {
	public data_storage() {}
	
	//Creates an account/new file to store the information
	/**
	 * Creates the a SER file and stores it in the clients folder.
	 * */
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
	/**Opens the ser file and passes the information of the file to the customer parameter for editing and use*/
	public boolean open_account(customer client, String first_name, String last_name, String SS_number, String pass_word) 
	{
		//Creates file path
		String filename = "Clients\\" + first_name+last_name+SS_number+".ser";		
		File newfile = new File(filename);
		String redudant = new String();
		
		//Create a class carrier
		customer data_client = new customer("FIRST NAME", "LAST NAME", "111111111", 
                new String("password"), new String("addresss"), 
                new String("651-123-4567"));
		
		//Checks to see if file exists to open
		if(newfile.exists())
		{
		try {
			//Creating the stream 
			System.out.println("File found now opening. . . \n");
			FileInputStream file_stream = new FileInputStream(filename);
			ObjectInputStream in_stream = new ObjectInputStream(file_stream);
			
			data_client = (customer)in_stream.readObject();
			
			in_stream.close();
			file_stream.close();
		    }
		    catch(IOException e) {System.out.println("IO caught"); e.printStackTrace();}
		    catch(ClassNotFoundException e) {System.out.println("Class Exception caught");}
		}
		else 
		{System.out.println("FILE CAN'T OPEN IT DOES NOT EXIST"); return false;}
		
		if (pass_word.toString().equals(data_client.getPassword().toString())) 
		{
		client = update(client, data_client);
		return true;
		}
		else {System.out.println("Incorrect Password please try again . . .");return false;}
	}

	//Saves all data to account an closes
	/**
	 * Sends the information from the customer parameter to the designated SER file and updates the information
	 * and saves it. 
	 * */
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
				System.out.println("Account updated . . .");
				}
			catch (IOException e) {System.out.println("Exception occurred careful now. . . ");}
		}
		
	}
	
	//Check if the account exists
	/**Checks to see if the account exists returns a boolean*/
	public boolean check_account (customer client) {
		//Creates the file path
			String filename = "Clients\\" + client.getFirst_name()+client.getLast_name()+client.getSs_number()+".ser";
			File newfile = new File(filename);
			return newfile.exists();
	}

	//Activate an account
	/**This activates accounts only admin have access to this method*/
	public void activate(String first_name, String last_name, String SS_number) {
		String filename = "Clients\\" + first_name+last_name+SS_number+".ser";		
		File newfile = new File(filename);
		String redudant = new String();

		
        customer data_client = new customer("FIRST NAME", "LAST NAME", "111111111", 
                new String("password"), new String("addresss"), 
                new String("651-123-4567"));
		
		//Checks to see if file exists to open
		if(newfile.exists()){
			try 
			{
				//Creating the stream 
				System.out.println("File found now opening. . . \n");
				FileInputStream file_stream = new FileInputStream(filename);
				ObjectInputStream in_stream = new ObjectInputStream(file_stream);
				
				data_client = (customer)in_stream.readObject();
				
				in_stream.close();
				file_stream.close();
				
			}
			catch(IOException e) {System.out.println("IO caught"); e.printStackTrace();}
			catch(ClassNotFoundException e) {System.out.println("Class Exception caught");}
		}
		else {System.out.println("Error file not found");}
		
		data_client.setActivated(1);
		close_account(data_client);
	}


	//Updates an account and returns it
	/**This is used to take two customer objects and updates clienta and returns it*/
	public customer update(customer clienta, customer clientb) {
		clienta.setFirst_name(clientb.getFirst_name());
		clienta.setLast_name(clientb.getLast_name());
		clienta.setSs_number(clientb.getSs_number());
		clienta.setActivated(clientb.isActivated());
		clienta.setAdmin(clientb.isAdmin());
		clienta.setBalance(clientb.getBalance());
		return clienta;
	}
}
