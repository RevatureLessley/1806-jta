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

    public void debugAccounts(boolean isSuperAdmin)
    {
        System.out.println("Displaying all accounts");
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
    }

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
