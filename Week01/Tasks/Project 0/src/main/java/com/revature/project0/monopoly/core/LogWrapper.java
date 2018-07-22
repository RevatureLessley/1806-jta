package com.revature.project0.monopoly.core;


import org.apache.log4j.Logger;

/**
 * This class wraps around the Log4J Logger to allow it to provide useful logging info about
 * the class initiating the log, instead of creating a Logger instance per class.
 */
public class LogWrapper {
    public enum Severity {
        FATAL, ERROR, WARN, INFO, DEBUG, TRACE
    }

    /**
     * This method will log the message in the log defined by log4j.properties
     * @param callingClass the class initiating the log call
     * @param message the message to be printed to the log
     * @param level the severity level of the log, can be one of: FATAL, ERROR, WARN, INFO, DEBUG, TRACE
     */
    static void log(Class callingClass, String message, Severity level){
        Logger logger = Logger.getLogger(callingClass);    //TODO: Efficiency? Is there a method to simply change the Class type?
        switch (level){
            case FATAL: logger.fatal(message); break;
            case ERROR: logger.error(message); break;
            case WARN: logger.warn(message); break;
            case INFO: //case 4 is also the default.
            default: logger.info(message); break;
            case DEBUG: logger.debug(message); break;
            case TRACE: logger.trace(message); break;
        }
    }

    /**
     * This method will log the message in the log defined by log4j.properties, with a Severity level of INFO
     * @param callingClass the class initiating the log call
     * @param message the message to be printed to the log
     */
    static void log(Class callingClass, String message){
        log(callingClass, message, Severity.INFO);
    }

    /**
     * This method is specifically for logging exceptions, which will use the ERROR severity level.
     * @param callingClass The class calling the Logger
     * @param e the Exception being thrown
     */
    static void log(Class callingClass, Exception e){
        Logger logger = Logger.getLogger(callingClass);
        StringBuilder sb = new StringBuilder();
        sb.append(e.getClass().getSimpleName()).append(" encountered: \"").append(e.getMessage()).append("\"\n");
        for (StackTraceElement element : e.getStackTrace()){
            if (!"java.base".equals(element.getModuleName())) {
                sb.append(String.format("%25s","at: (")).append(element.getFileName());
                sb.append(":").append(element.getLineNumber());
                sb.append(") in ").append(element.getMethodName()).append("()\n");
            }
        }
        logger.error(sb.toString());
    }
}