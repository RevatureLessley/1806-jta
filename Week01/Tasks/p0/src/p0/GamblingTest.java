/**
 * 
 */
package p0;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Nate
 *
 */
public class GamblingTest {

	Gambling g = null;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		g = new Gambling("");
		g.Users.add(new User("asdftest","pass",1));
	}

	/*
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		g = null;
	}

	@Test
	public void verifyWithdrawalOverdraw() {
		assertEquals(-1,g.verifyWithdrawal("150",g.Users.get(1)),0);
	}	
	
	@Test
	public void userExistsTest() {
		assertEquals(1,g.userExists("asdftest"));
	}
	
	@Test
	public void userDoesNotExistTest() {
		assertEquals(-1,g.userExists("imnotreal"));
	}

}
