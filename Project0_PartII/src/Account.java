import java.io.Serializable;

public class Account implements Serializable
{

    private String username;
    private String password;
    private boolean isApproved;

    private boolean isAdmin;

    private double accountBalance;


    private int creditScore;

    /**
     * Creates a normal user
     * @param username
     * @param password
     */
    public Account(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.isApproved = false;
        this.accountBalance = 0;
    }


    /**
     * Creates an admin user
     * @param username
     * @param password
     * @param isAdmin
     */
    public Account(String username, String password, boolean isAdmin)
    {
        this.username = username;
        this.password = password;
        this.isApproved = true;
        this.isAdmin = isAdmin;
        this.accountBalance = 0;
    }

    public Account(String username, String password, boolean isAdmin, boolean isApproved)
    {
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
        this.isApproved = isApproved;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double balance)
    {
        this.accountBalance = balance;
    }

    public void withdraw(double amount)
    {
        this.accountBalance -= amount;
    }

    public void deposit(double amount)
    {
        this.accountBalance += amount;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    /**
     * Prints accounts details when needed
     */
    public void printAccountDetails()
    {
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
        System.out.println("Approval Status: " + isApproved);
        System.out.println("Role: " + (isAdmin?"Admin":"User"));
        System.out.println("Account Balance: " + accountBalance);
    }

    /**
     * Checks if one account is equal to another account
     * @param enteredAccount
     * @return true if the accounts are equal
     * @return false if accounts aren't equal
     */

    public boolean equals(Account enteredAccount)
    {
        if(this.password.equals(enteredAccount.getPassword()))
            return true;

        return false;
    }
}
