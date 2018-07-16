package com.trms.unittests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.trms.util.Connections;

public class DBConnectionTest {
	@Test()
	public void dbConnectionTest() {
		assertNotNull("CONNECTION FAILED", Connections.getConnection());
	}
}
