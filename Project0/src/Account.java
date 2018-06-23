public class Account
{

    private String username;
    private String password;
    private boolean isApproved;
    private boolean isAdmin;

    private int accountBalance;


    private int creditScore;

    public Account(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.isApproved = false;
        this.accountBalance = 0;
    }
    public Account(String username, String password, boolean isAdmin)
    {
        this.username = username;
        this.password = password;
        this.isApproved = true;
        this.isAdmin = isAdmin;
        this.accountBalance = 0;
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

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }
}
