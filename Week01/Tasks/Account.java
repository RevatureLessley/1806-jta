import java.util.*;

public /*abstract*/ class Account {
	ArrayList<AccountAttribute> attributes = new ArrayList<AccountAttribute>();

	Account(){
		new Username(this);
		new Password(this);
		new FirstName(this);
		new LastName(this);
	};

	public void addAttribute(AccountAttribute aa) {
		attributes.add(aa);
	}

	public void print(){
		attributes.stream().forEach(a->a.print());
	}
}
