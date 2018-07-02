package com.crypt.util;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.crypt.util.Connections;

public class DBConnectionTest {

	@Test()
	public void dbConnectionTest() {
		assertNotNull("CONNECTION FAILED", Connections.getConnection());
	}

}
