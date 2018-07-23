package com.revature.utils;

import java.util.HashMap;

import org.apache.log4j.Logger;

public class MasterLogger {

	private static HashMap<Class<?>, Logger> loggerMap;

	public static void trace(Class<?> clazz, String message) {
		getLogger(clazz).trace(message);
	}

	public static void info(Class<?> clazz, String message) {
		getLogger(clazz).info(message);
	}

	public static void debug(Class<?> clazz, String message) {
		getLogger(clazz).debug(message);
	}

	public static void warn(Class<?> clazz, String message) {
		getLogger(clazz).warn(message);
	}

	public static void error(Class<?> clazz, String message) {
		getLogger(clazz).error(message);
	}

	public static void fatal(Class<?> clazz, String message) {
		getLogger(clazz).fatal(message);
	}

	private static Logger getLogger(Class<?> clazz) {
		loggerMap = getLoggerMap();

		Logger l;

		if (loggerMap.containsKey(clazz)) {
			l = loggerMap.get(clazz);
		} else {
			l = Logger.getLogger(clazz);
			loggerMap.put(clazz, l);
		}

		return l;
	}

	private static HashMap<Class<?>, Logger> getLoggerMap() {
		if (loggerMap == null)
			loggerMap = new HashMap<>();
		
		return loggerMap;
	}
}
