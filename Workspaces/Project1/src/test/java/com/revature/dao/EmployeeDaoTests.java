package com.revature.dao;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.bean.Employee;

public class EmployeeDaoTests {

	@Test
	public void selectEmployeeByEmailTest() {
		EmployeeDaoImpl ed = new EmployeeDaoImpl();
		
		Employee e = ed.selectEmployeeByEmail("test@test.com");
		
		assertEquals(e.getEmail(), "test@test.com");
		assertEquals(e.getFirstName(), "fname");
		assertEquals(e.getLastName(), "lname");
		assertEquals(e.getId(), 1);
		assertEquals(e.getRole(), 1);
		assertEquals(e.getPassword(), "test");
	}
	
	@Test
	public void selectEmployeeByIdTest() {
		EmployeeDaoImpl ed = new EmployeeDaoImpl();
		
		Employee e = ed.selectEmployeeById(1);
		
		assertEquals(e.getEmail(), "test@test.com");
		assertEquals(e.getFirstName(), "fname");
		assertEquals(e.getLastName(), "lname");
		assertEquals(e.getId(), 1);
		assertEquals(e.getRole(), 1);
		assertEquals(e.getPassword(), "test");
	}

}
