package project01util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;

import org.junit.Test;

import project01Dao.RequestDaoImpl;
import project01Dao.RoleAssignmentDaoImpl;
import project01Dao.UsernameAndPasswordDaoImpl;

public class Junit {

	@Test
	public void testTest() throws Exception{
		Connection conn = Connections.getConnection();
		conn.setAutoCommit(false);
		
		RequestDaoImpl             reqDao  = new RequestDaoImpl();
		RoleAssignmentDaoImpl      roleDao = new RoleAssignmentDaoImpl();
		UsernameAndPasswordDaoImpl unpDao  = new UsernameAndPasswordDaoImpl();
		
		unpDao.createAccount("username", "password");
		assertEquals(true,unpDao.checkPassword("username", "password"));
		assertEquals(true,unpDao.checkExistence("username", "password"));
		assertEquals(false,unpDao.checkPassword("username", "passPass"));
		assertEquals(false, unpDao.checkExistence("Use", "Pass"));
		
		assertEquals(0, roleDao.getRole("username"));
		assertNotEquals(1, roleDao.getRole("username"));
		assertNotEquals(2, roleDao.getRole("username"));
		assertNotEquals(3, roleDao.getRole("username"));

		
		conn.rollback();
		conn.close();
	}
}
