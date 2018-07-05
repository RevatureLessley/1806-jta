package com.revature.misc.lambda;

public class DietNpc implements Comparable<DietNpc>{
	private int id;
	private String name;
	private int lvl;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public DietNpc(int id, String name, int lvl) {
		super();
		this.id = id;
		this.name = name;
		this.lvl = lvl;
	}
	public DietNpc() {
		super();

	}
	@Override
	public String toString() {
		return "DietNpc [id=" + id + ", name=" + name + ", lvl=" + lvl + "]";
	}
	@Override
	public int compareTo(DietNpc npc) {
		return this.getName().compareTo(npc.getName());
	}
	
}
