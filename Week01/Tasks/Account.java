import java.io.*;
import java.util.*;

public class Account implements Serializable {
	HashMap<String, AccountAttribute> attributes = new HashMap<>();

 	Account(){
 		new Username(this);
 		new Password(this);
 		new FirstName(this);
		new LastName(this);
 	};

 	public void addAttribute(String field, AccountAttribute aa) {
  		attributes.put(field, aa);
 	}

	public Integer getID(){
		Integer index = attributes.get("Username").getID() + 
				attributes.get("Password").getID();
		return index.hashCode();
	}

 	public void print(){
		for(AccountAttribute aa : attributes.values())
			aa.print();
 	}
}
