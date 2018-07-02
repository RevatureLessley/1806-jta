import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    AccountsRecord testAccountsRecord;
    Account testAccount;
    public AppTest()
    {
        testAccountsRecord = new AccountsRecord();
        testAccount = new Account("testUser1023912039", "nothingisavalidpassword");

    }

    @Test
    public void accountContainsTest()
    {
        assertFalse(testAccountsRecord.contains("not a user at all 1234566"));
    }

    @Test
    public void accountAdminTest()
    {
        assertFalse(testAccount.isAdmin());
    }

    @Test
    public void accountEqualsTest()
    {
        Account testAccount2 = new Account("falseuser82342034", "passwordisavalidnothing");
        assertFalse(testAccount.equals(testAccount2));

    }

}
