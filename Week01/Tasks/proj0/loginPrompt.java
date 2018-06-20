package p0;
public class loginPrompt{
	public void inputLogin(){
		java.io.Console console = System.console();
		String username = console.readLine("Username: ");
		String password = new String(console.readPassword("Password: "));
		System.out.print(username + " " + password);
	}
}