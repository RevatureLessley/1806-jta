import java.io.*;

public abstract class AccountAttribute implements ConsoleReference, Serializable{
 	
	AccountAttribute(AdminAccount aa) {}
 	
	AccountAttribute(UserAccount ua) {}

	abstract String askUser();

 	abstract void print();

	abstract Integer getID();
}
