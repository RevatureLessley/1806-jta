package Tasks.RevatureAccounts;

import java.io.*;
import java.util.*;
import Tasks.RevatureAccounts.AccountAttributes.*;

public class AdminAccount extends Account implements Serializable {
 public static AdminAccount admin = new AdminAccount();

  private AdminAccount() {
  new Username(this);
  new Password(this);
  new FirstName(this);
  new LastName(this);
  status = AccountStatus.APPROVED;
 }

 @Override
 public void enter() {
  
 }
}
