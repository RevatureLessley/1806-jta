package Tasks.RevatureAccounts;

import java.io.*;
import java.util.*;
import Tasks.RevatureAccounts.AccountAttributes.*;

public class UserAccount extends Account implements Serializable {

 	public UserAccount() {
		new Username(this);
		new Password(this);
		new FirstName(this);
		new LastName(this);
		status = AccountStatus.PENDING;
	}
}
