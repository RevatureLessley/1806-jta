package com.revature.service;


public interface UserServiceInterface {

	/**
	 * Gets a user by name
	 * 
	 * @param name
	 * @return User associated with the given name
	 */
	Integer getUser(String name);

	/**
	 * Checks whether any users have already taken the given name.
	 * 
	 * @param name
	 * @return
	 */
	boolean checkUsernameAvailable(String name);

	/**
	 * Creates a new User and adds it to the controller.
	 * 
	 * @param name
	 * @param password
	 * @return new User instance
	 */
	Integer addUser(String name, String password);

	/**
	 * Creates a string which contains all user names. Each user name is followed by
	 * the sum of balances in all the user's accounts.
	 * 
	 * @return
	 */
	String summarizeAllUsers();

}