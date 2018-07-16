package Project1;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import dao.PersonDaoImpl;
import util.Connections;

public class ServiceConnectionsTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void ConnectionsTest() {
		assertTrue(Connections.getConnection() != null);
	}
	
	@Test
	public void LoginTest() {
		PersonDaoImpl PDI = new PersonDaoImpl();
		assertTrue(PDI.checkPassword("SalaraElris", "Password") == 1);
	}
	@Test
	public void FailedLoginTest() {
		PersonDaoImpl PDI = new PersonDaoImpl();
		assertTrue(PDI.checkPassword("Salaraelris", "Password") == -1);
	}
	
}
