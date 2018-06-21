package common.banking.application;

import java.util.Scanner;
import java.io.*;

public class Banking {
	static MainMenu menu = new MainMenu();
	static StringBuffer buffrog = new StringBuffer();
	static data_storage dada = new data_storage();
	static customer dum = new customer("sdf", "sdf", "sdf", buffrog);

	public static void main(String[] args) 
	{
		while(true){
		menu.start_menu(menu.login_menu());
		}
	}
}
