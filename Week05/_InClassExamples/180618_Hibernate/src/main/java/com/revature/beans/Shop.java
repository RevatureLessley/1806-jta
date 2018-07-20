package com.revature.beans;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Shop {
	
	@Id 
	@Column(name="shop_id") 
	@SequenceGenerator(sequenceName="shop_seq", name="shop_seq")
	@GeneratedValue(generator="shop_seq", strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="shop_name")
	private String name;
	@ManyToOne
	@JoinColumn(name="owner_id")
	private Npc ownerId;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name="Shop_2_Item", joinColumns=@JoinColumn(name="Shop_ID"),
				inverseJoinColumns=@JoinColumn(name="Item_ID"))
	private List<Item> items;
	
	
	
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Npc getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Npc ownerId) {
		this.ownerId = ownerId;
	}
	
	@Override
	public String toString() {
		return "Shop [id=" + id + ", name=" + name + ", ownerId=" + ownerId + "]";
	}
	
	public Shop(Integer id, String name, Npc ownerId, List<Item> items) {
		super();
		this.id = id;
		this.name = name;
		this.ownerId = ownerId;
		this.items = items;
	}
	public Shop(Integer id, String name, Npc ownerId) {
		super();
		this.id = id;
		this.name = name;
		this.ownerId = ownerId;
		this.items = new ArrayList<Item>();
	}
	public Shop() {
		super();
		this.items = new ArrayList<Item>();
		// TODO Auto-generated constructor stub
	}
	public Shop(String name, Npc ownerId) {
		super();
		this.name = name;
		this.ownerId = ownerId;
		this.items = new ArrayList<Item>();
	}
	
}
