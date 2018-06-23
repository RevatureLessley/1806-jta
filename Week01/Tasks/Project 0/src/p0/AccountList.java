package p0;

import java.util.ArrayList;
import java.io.Serializable;

public class AccountList implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 874709771452301087L;
	private ArrayList<Player> activeSave = new ArrayList<Player>();
	private ArrayList<Player> waitingSave = new ArrayList<Player>();
	private Banker banker;
	private Administrator admin;
	private Loaner loaner;
	private boolean worldFlagged = false;
	
	public AccountList()
	{
		super();
	}
	
	public AccountList(ArrayList<Player> list, Administrator a, Banker b, Loaner l)
	{
		activeSave = list;
		admin = a;
		banker = b;
		loaner = l;
	}
	
	public ArrayList<Player> getList()
	{
		return activeSave;
	}
	
	public Banker getBanker() {
		return banker;
	}

	public void setBanker(Banker banker) {
		this.banker = banker;
	}

	public Administrator getAdmin() {
		return admin;
	}

	public void setAdmin(Administrator admin) {
		this.admin = admin;
	}

	public Loaner getLoaner() {
		return loaner;
	}

	public void setLoaner(Loaner loaner) {
		this.loaner = loaner;
	}

	public void add(Player a)
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
		ArrayList<Player> temp = new ArrayList<Player>();
		if(!worldFlagged)
		{
			for(Player p: activeSave)
			{
				if(!p.getFlagged())
				{
					temp.add(p);
				}
			}
			activeSave.clear();
			for(Player p:temp)
			{
				activeSave.add(p);
			}
		}
		else
			activeSave.clear();
	}
	
}