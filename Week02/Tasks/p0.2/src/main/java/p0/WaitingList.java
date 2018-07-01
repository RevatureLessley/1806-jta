package p0;

import java.util.ArrayList;
import java.io.Serializable;

public class WaitingList implements Serializable
{
	/**
	 * Serializable container class for accounts that are waiting.
	 * Separate from ActiveList class since a second copy of Admin,
	 * Banker and Loaner were not needed.
	 */
	private static final long serialVersionUID = 874709771452301087L;
	private ArrayList<PlayerAccount> save = new ArrayList<PlayerAccount>();
	private boolean worldFlagged = false;
	
	public WaitingList()
	{
		save = new ArrayList<PlayerAccount>();
	}
	
	public ArrayList<PlayerAccount> getList()
	{
		return save;
	}
	
	public void add(PlayerAccount a)
	{
		save.add(a);
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
			for(PlayerAccount p: save)
			{
				if(!p.getPlayerInfo().isAccountFlagged())
				{
					temp.add(p);
				}
			}
			save.clear();
			for(PlayerAccount p:temp)
			{
				save.add(p);
			}
		}
		else
			save.clear();
	}
	
}