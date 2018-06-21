package proj0;

public class loginPrompt{
	/**
	* Prompts the user for their login info
	* @return None
	*/
	public void inputLogin(){
		java.io.Console console = System.console();
		String username = console.readLine("Username: ");
		String password = new String(console.readPassword("Password: "));
		
		
		System.out.print(username + " " + password);
	}
	public void verifyLogin() {
			
	}
}