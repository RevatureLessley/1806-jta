package com.revature.project0;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class AccountTest {
	static Account acc = null;
	@Before
	public void setUp() throws Exception {
		acc = new Account(UUID.randomUUID().toString(),100);
	}
	@After
	public void tearDown() throws Exception {
		acc = null;
	}

	@Test
	public void withdraw() {
		assertEquals(0,acc.withdraw(acc.getBal()),0);
	}
	@Test
	public void deposit() {
		assertEquals(200,acc.deposit(100),0);
	}
	@Test
	public void getBalance() {
		assertEquals(100,acc.getBal(),0);
	}
}
