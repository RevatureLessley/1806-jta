import java.io.*;
import java.util.*;

public class UserAccount extends Account implements Serializable {

 	UserAccount() {
		new Username(this);
		new Password(this);
		new FirstName(this);
		new LastName(this);
	}
}
