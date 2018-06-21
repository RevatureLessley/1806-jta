import java.io.*;
import java.util.*;

public class AdminAccount extends Account implements Serializable {
	public static AdminAccount admin = new AdminAccount();

 	private AdminAccount() {
		new Username(this);
		new Password(this);
		new FirstName(this);
		new LastName(this);
	}
}
