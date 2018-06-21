import java.io.*;

public abstract class AccountAttribute implements ConsoleReference, Serializable{
	//protected static Console console = System.console();
 
 	AccountAttribute(Account a) {}

 	abstract void print();

	abstract Integer getID();
}
