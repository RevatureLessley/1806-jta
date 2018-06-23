package p0;

import java.util.ArrayList;
import java.io.Serializable;

public class WaitingList implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 874709771452301087L;
	private ArrayList<Player> save = new ArrayList<Player>();
	private boolean worldFlagged = false;
	
	public WaitingList()
	{
		save = new ArrayList<Player>();
	}
	
	public ArrayList<Player> getList()
	{
		return save;
	}
	
	public void add(Player a)
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
		ArrayList<Player> temp = new ArrayList<Player>();
		if(!worldFlagged)
		{
			for(Player p: save)
			{
				if(!p.getFlagged())
				{
					temp.add(p);
				}
			}
			save.clear();
			for(Player p:temp)
			{
				save.add(p);
			}
		}
		else
			save.clear();
	}
	
}