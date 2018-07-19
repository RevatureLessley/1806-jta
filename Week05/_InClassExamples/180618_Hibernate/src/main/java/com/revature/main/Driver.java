package com.revature.main;

import com.revature.beans.Person;
import com.revature.daos.PersonDaoImpl;

public class Driver {

	public static void main(String[] args) {
		System.out.println(" =====Launching App=====");
		
		PersonDaoImpl pd = new PersonDaoImpl();
		pd.insertPerson(new Person("Bobbert", "Professional Bob", 1232000));
		pd.insertPerson(new Person("Ryan", "Lead Trainer", -16));
		pd.insertPerson(new Person("Mehrab", "Lead Trainer", 0));
	
		System.out.println(pd.getAllPersons());
		
		System.out.println("=====Terminating App=====");
		System.exit(0);
	}

}
