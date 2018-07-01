import static org.junit.Assert.*;

import org.junit.Test;

import com.revature.service.Transactions;

public class BankTest {

	@Test
	public void addToAccount() {
		assertEquals(90, Transactions.addToAccount(40, 50));
	}
	@Test
	public void subFromAccount() {
		assertEquals(-10, Transactions.subFromAccount(40, 50));
	}

}
