package com.revature.day5.logging;

import org.apache.log4j.Logger;

public class Driver {

	final static Logger logger = Logger.getRootLogger();
	
	public static void main(String[] args) {
		Driver d = new Driver();
		d.logStuff("Yeah! My logger is configured properly!");
	}

	public void logStuff(String message){
		logger.trace(message);
		logger.debug(message);
		logger.info(message);
		logger.warn(message);
		logger.error(message);
		logger.fatal(message);
		
		
	}
}
