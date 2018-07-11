package com.revature.main;

import com.revature.services.UserService;


public class Driver {

	public static void main(String[] args) {
		
		UserService.createUser("branchmanager", 100, "Matthew", "Beck", 5555555, 
				"mbeck@email.com", "100 way dr", "hamptons", "mbeck", "password");
	}

}
