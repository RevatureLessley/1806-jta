package p0;

import java.util.ArrayList;
import java.io.Serializable;

public class AccountList implements Serializable
{
	/**
	 * A serializable class to store the active Admin, Banker, Loaner 
	 * and all currently acitve player characters as well as manage deletion 
	 * of accounts that are flagged for removal.
	 */
	private static final long serialVersionUID = 874709771452301087L;
	private ArrayList<PlayerAccount> activeSave = new ArrayList<PlayerAccount>();
	private ArrayList<PlayerAccount> waitingSave = new ArrayList<PlayerAccount>();
	private BankerAccount banker;
	private AdministratorAccount admin;
	private LoanerAccount loaner;
	private boolean worldFlagged = false;
	
	public AccountList()
	{
		super();
	}
	
	public AccountList(ArrayList<PlayerAccount> list, AdministratorAccount a, BankerAccount b, LoanerAccount l)
	{
		activeSave = list;
		admin = a;
		banker = b;
		loaner = l;
	}
	
	public ArrayList<PlayerAccount> getList()
	{
		return activeSave;
	}
	
	public BankerAccount getBanker() {
		return banker;
	}

	public void setBanker(BankerAccount banker) {
		this.banker = banker;
	}

	public AdministratorAccount getAdmin() {
		return admin;
	}

	public void setAdmin(AdministratorAccount admin) {
		this.admin = admin;
	}

	public LoanerAccount getLoaner() {
		return loaner;
	}

	public void setLoaner(LoanerAccount loaner) {
		this.loaner = loaner;
	}

	public void add(PlayerAccount a)
	{
		activeSave.add(a);
	}
	
	public void setWorldFlagged(boolean set)
	{
		worldFlagged = set;
	}
	
	public boolean getWorldFlagged()
	{
		return worldFlagged;
	}
	
	
	public void updateList()
	{
		ArrayList<PlayerAccount> temp = new ArrayList<PlayerAccount>();
		if(!worldFlagged)
		{
			for(PlayerAccount p: activeSave)
			{
				if(!p.getFlagged())
				{
					temp.add(p);
				}
			}
			activeSave.clear();
			for(PlayerAccount p:temp)
			{
				activeSave.add(p);
			}
		}
		else
			activeSave.clear();
	}
	
}