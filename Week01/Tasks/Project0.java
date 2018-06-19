public class Project0{
	public static void main(String args[]){
		Account a = new Account();
		Username u = new Username(a);
		Password p = new Password(a);
		FirstName f = new FirstName(a);
		LastName l = new LastName(a);
		System.out.println(u.get());
		System.out.println(p.get());
		System.out.println(f.get());
		System.out.println(l.get());

	}
}
