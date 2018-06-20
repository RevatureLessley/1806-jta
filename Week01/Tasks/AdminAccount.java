import java.io.*;
import java.util.*;

public class AdminAccount extends Account implements Serializable {
	public static AdminAccount admin = new AdminAccount();

 	private AdminAccount(){
 		new Username(admin);
 		new Password(admin);
 		new FirstName(admin);
		new LastName(admin);
 	}
}
