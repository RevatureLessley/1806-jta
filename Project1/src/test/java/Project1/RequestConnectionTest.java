package Project1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import beans.request;
import dao.RequestDaoImpl;

public class RequestConnectionTest {

	static RequestDaoImpl RDI = new RequestDaoImpl();
	static ArrayList<request> test = new ArrayList<request>();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		for(request r: RDI.getRequestsByPlayerId(1)) {
			test.add(r);
		}
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
	public void RequestDataTest() {
		assertTrue(test.size() ==2);
	}
	@Test
	public void RequestInfoCountTest() {
		assertTrue(test.get(1).getInfos().size()==2);
	}

}
