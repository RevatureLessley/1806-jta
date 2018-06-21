import java.io.*;

public abstract class AccountAttribute implements ConsoleReference, Serializable{
	//protected static Console console = System.console();
 
 	AccountAttribute(AdminAccount aa) {}
 	AccountAttribute(UserAccount ua) {}

 	abstract void print();

	abstract Integer getID();
}
