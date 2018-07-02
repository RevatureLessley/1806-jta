package com.revature.week1.tasks;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;




public class JUnitTests {
	
	ArrayList<User> users = new ArrayList<User>();
	
	
	@Before
	public void setup () throws Exception
	{
		
		users.add(new User(123,"toby", "pass", 3500, false, true));
		//Bank b = new Bank(users);
	}
	
	@Test
	public void test()
	{
		assertNotNull(users.get(0).getBalance());
	}
	
	@Test
	public void test2()
	{
		assertNotNull(users.get(0).getPassword());
	}
	
	@Test
	public void test3()
	{
		assertNotEquals(0, users.get(0).getBalance());
	}
	
		
	
	

}
