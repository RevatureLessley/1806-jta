package com.revature.day2.exceptions;

//Custom exceptions are simply classes that extend Exception or any children of.
public class RyanWasWrong extends Exception{
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return "Oh oh, Ryan was wrong, crash the app!";
	}
}
