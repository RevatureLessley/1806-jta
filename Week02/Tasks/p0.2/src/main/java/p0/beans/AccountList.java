package p0.beans;

import java.util.ArrayList;
//
//import p0.Administrator;
//import p0.Banker;
//import p0.Loaner;
//import p0.Player;

public class AccountList {
	private ArrayList<Player> activeSave = new ArrayList<Player>();
	private ArrayList<Player> waitingSave = new ArrayList<Player>();
	//private Banker banker;
	//private Administrator admin;
	//private Loaner loaner;
	private boolean worldFlagged = false;
}
