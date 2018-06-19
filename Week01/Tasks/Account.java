import java.util.*;

public abstract class Account {
	ArrayList<AccountAttribute> attributes = new ArrayList<AccountAttribute>();
	

	Account() {};

	public void addAttribute(AccountAttribute aa) {
		attributes.add(aa);
	}
}
