public final class SuperUser {

    private final static String username = "superuser";
    private final static String password = "iownthis99!";

    public static boolean validate(Account account)
    {
        Account superuser = new Account(username, password);
        if(superuser.equals(account))
            return true;

        return false;
    }
}
