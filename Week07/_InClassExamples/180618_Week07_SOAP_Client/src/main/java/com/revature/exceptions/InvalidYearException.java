package com.revature.exceptions;

public class InvalidYearException extends Exception{
	public InvalidYearException(){
		
	}
	public InvalidYearException(String message){
		super(message);
	}
}
