package Project1;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import beans.employee;
import dao.EmployeeDaoImpl;

public class EmployeeConnectionTest {

	static ArrayList<employee> test = new ArrayList<employee>();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		for(employee e: edi.getEmployeesByCooperate(1)) {
			System.out.println(e.getEmpId());
			test.add(e);
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
	public void test() {
		System.out.println(test.size());
		assertTrue(test.size()== 8);
	}

}
