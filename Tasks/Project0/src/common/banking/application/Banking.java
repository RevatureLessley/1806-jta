package common.banking.application;

import java.util.Scanner;

import org.apache.log4j.Logger;

import common.banking.dao.*;



public class Banking {
	final static Logger logger = Logger.getLogger(Banking.class);
	public static int TURNON = 0; //Change this to 1 to start logging
	static Main_Menu menu = new Main_Menu();
	static Scanner s = new Scanner(System.in);
	public static boolean exit = false;
	public static ClientDaoImpl clientsql = new ClientDaoImpl();
	public static AccountDaoImpl accountsql = new AccountDaoImpl();
	public static ContactDaoImpl contactsql = new ContactDaoImpl();
	public static PersonalinfoDaoImpl personalsql = new PersonalinfoDaoImpl();
	

	public static void main(String[] args) 
	{	
		while(true)
		{
			menu.start_menu(menu.login_menu());
			if(exit) {break;};
			if(TURNON == 1)logger.info("Exited out of menu successfully!");
		}
		if(TURNON == 1)logger.info("Quitting the application");
	}
}



