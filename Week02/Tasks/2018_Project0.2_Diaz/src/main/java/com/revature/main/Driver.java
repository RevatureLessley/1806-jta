package com.revature.main;

import java.sql.Connection;
import java.util.Scanner;

import com.revature.util.BankMenu;
import com.revature.util.Connections;

/**
 * Class1 Diver starts the application
 * @author Zachary Diaz
 *
 */
public class Driver {
	public static void main(String[] args)  {
		Scanner in = new Scanner(System.in);
		Connection conn = Connections.getConnection();
		
		BankMenu.menu();

		
	}

	
}
