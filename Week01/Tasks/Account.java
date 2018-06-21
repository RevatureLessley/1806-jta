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

 	public void addAttribute(AccountAttribute aa) {
  		attributes.add(aa);
 	}

	public Integer getID(){

	}

 	public void print(){
  		attributes.stream().forEach(a->a.print());
 	}
}
