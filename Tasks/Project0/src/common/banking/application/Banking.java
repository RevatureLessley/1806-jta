package common.banking.application;

import java.util.Scanner;
import java.io.*;

public class Banking {

	public static void main(String[] args) 
	{
		customer dummy = new customer("fname","lname","socialsecuritynumber");
		data_storage creator = new data_storage();
		dummy.statement();
		creator.create_client(dummy);
		customer dummy_copy = new customer("WRONG","WRONG","WRONG");
		creator.open_account(dummy_copy, "fname", "lname", "socialsecuritynumber");
		dummy_copy.statement();
		//MainMenu startmenu = new MainMenu(); 
		//startmenu.start_menu();
	}
}
