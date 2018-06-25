package com.bank.main;

import org.apache.log4j.Logger;
import java.io.File;
import java.util.Scanner;

public class Bank {
	final static Logger logger = Logger.getRootLogger();
	static final Scanner sc = new Scanner(System.in);
	public static File file = new File("users.ser");
	static GeneralMethods generalMethods = new GeneralMethods();
	static UserMethods userMethods = new UserMethods();
	static AdminMethods adminMethods = new AdminMethods();
	
	public static void main(String[] args) {
		logger.info("Starting program");
		generalMethods.runProgram();
		logger.info("End of program.");
	}

}
