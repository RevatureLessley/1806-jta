package p0.beans;

import java.util.ArrayList;

//import p0.Player;

public class WaitingList {

	private ArrayList<Player> save = new ArrayList<Player>();

	public ArrayList<Player> getSave() {
		return save;
	}

	public void setSave(ArrayList<Player> save) {
		this.save = save;
	}

	public void add(Player p)
	{
		save.add(p);
	}
}
