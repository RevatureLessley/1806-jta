package p0;

import java.util.ArrayList;
import java.io.Serializable;

public class AccountList implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 874709771452301087L;
	ArrayList<Account> save = new ArrayList<Account>();
	
	public AccountList()
	{
		super();
	}
	
	public AccountList(ArrayList<Account> list)
	{
		save = list;
	}
	
	public void updateList()
	{
		ArrayList<Account> temp = new ArrayList<Account>();
		for(Account a: save)
		{
			if(!a.getFlagged())
			{
				temp.add(a);
			}
		}
		save.clear();
		for(Account a:temp)
		{
			save.add(a);
		}
	}
	
}