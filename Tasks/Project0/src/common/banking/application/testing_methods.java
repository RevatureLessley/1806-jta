package common.banking.application;

//Junit testing
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class testing_methods {
	customer client = new customer("FIRST NAME", "LAST NAME", "111111111", 
            new String("password"), new String("addresss"), 
            new String("651-123-4567"));
	
	@Test
	public void activatedCheck() {
		assertEquals(false, client.isActivated());
	}

	@Test
	public void nameCheck() {
		assertEquals("First",client.getFirst_name() );
	}
	
	@Test
	public void lastNameCheck() {
		assertEquals("Last", client.getLast_name());
	}
	
	@Before
	public void depositCheckSetup() {
		client.deposit(500);
	}
	
	@Test
	public void depositCheck() {

		assertEquals(500,(int)client.getBalance());
	}
	
}
