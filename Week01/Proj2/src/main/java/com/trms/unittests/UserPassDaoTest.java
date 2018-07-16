package com.trms.unittests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.trms.beans.UserPass;
import com.trms.dao.Dao;
import com.trms.dao.UserPassDaoImpl;
import com.trms.util.Connections;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserPassDaoTest {
	Dao<UserPass, String> dao;
	Integer doX = 1;
	@Before
	public void before() { dao = new UserPassDaoImpl(); }

	@After
	public void after() { dao = null; }

	@Test
	public void a_testDBConnection() { assertNotNull(Connections.getConnection()); }

	@Test()
	public void b_testInsert() { for(int i = 0; i<doX; i++) {assertFalse(dao.insertNew(new UserPass("Test" + i, "Test" + (i+1) )));} }

	@Test
	public void c_testSelectAll() { assertNotNull("UserPass select all test failed.", dao.selectAll()); }

	@Test
	public void d_testSelectAllx20() { for(int i=0; i<doX; i++) {assertNotNull("UserPass select all test failed.", dao.selectAll()); }}

	@Test
	public void e_testSelectById() { assertNotNull("UserPass select by id test failed.", dao.selectByID("Test0")); }

	@Test
	public void f_testDeleteUserPass() { for(int i=0; i<doX; i++) { assertTrue("UserPass deletion test failed.", dao.deleteByID("Test" + i));} }	
}