public final class SuperUser {

    private final static String username = "superuser";
    private final static String password = "iownthis99!";

    /**
     * Checks if the username and password are valid to log in as SuperUser
     * @param account
     * @return true if account info is valid
     * @return false if account info is not valid
     */
    public static boolean validate(Account account)
    {
        Account superuser = new Account(username, password);
        if(superuser.equals(account))
            return true;

        return false;
    }
}
