import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

public class AccountsRecord implements Serializable
{
    //accountRecords <Username, AccountClass>
    HashMap<String, Account> accountsStore;

    public AccountsRecord()
    {
        accountsStore = new HashMap<>();
    }

    public void addAccount(Account account)
    {
        accountsStore.put(account.getUsername(), account);
    }

    public void deleteAccount(Account account)
    {
        accountsStore.remove(account.getUsername());
    }

    public Account getAccount(String username)
    {
        if(contains(username))
            return accountsStore.get(username);

        return null;
    }

    public void updateAccount(Account account)
    {
        accountsStore.replace(account.getUsername(), account);
    }

    public boolean contains(String username)
    {
        if(accountsStore.containsKey(username))
            return true;

        return false;
    }

    /**
     * Checks to see if account actually exists and whether the login attempt is valid
     * @param enteredAccount
     * @return true if username and password match
     * @return false if username and password don't match
     */
    public boolean checkValidity(Account enteredAccount)
    {
        String username = enteredAccount.getUsername();
        if(contains(username))
        {
            Account possibleAccount = getAccount(username);
            if(possibleAccount.equals(enteredAccount))
                return true;
        }

        return false;
    }

    /**
     * Used to display a list of ALL accounts. Used by admin and SuperUser
     * @param isSuperAdmin a flag to see if the caller is the SuperAdmin, so that it can list all admins as well
     */

    public void debugAccounts(boolean isSuperAdmin)
    {
        System.out.println("========================================\n" +
                "~Displaying all accounts~\n");
        Set<String> keys = accountsStore.keySet();

        if(isSuperAdmin)
        {
            for(String key: keys)
            {
                System.out.println(key + " | " + (accountsStore.get(key).isAdmin()?"Admin":"User"));
            }
        }
        else
        {
            Account tempAccount;
            for(String key: keys)
            {
                tempAccount = accountsStore.get(key);
                if(!tempAccount.isAdmin())
                System.out.println(key + " | " + "User" + " | " + (tempAccount.isApproved()?"Approved": "PENDING"));
            }
        }

        System.out.println("\n~End of Accounts~\n" +
                "==========================================");
    }

    /**
     * Method used to flag all pending accounts as approved.
     */

    public void approveAllAccounts()
    {
        Set<String> keys = accountsStore.keySet();
        Account tempAccount;
        for(String key: keys)
        {
            accountsStore.get(key).setApproved(true);
        }
    }
}
