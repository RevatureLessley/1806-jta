package project01Dao;

public interface UsernameAndPasswordDao {
	Boolean checkPassword(String username, String password);
	Boolean checkExistence(String username, String password);
	 String getUsername();
	 String getPassword();
	   void setPassword();
	   void setUsername();
	   void createAccount(String username, String password);
}
