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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//UNIDIRECTIONAL VS BIDIRECTIONAL
/*
 * UNIDIRECITONAL tables are any tables that grab data from it's 
 * dependencies In one direction. Where in the other direction it is 
 * impossible.
 * As such:
 * Through item, i can access shops, via item.getShops()
 * But through shops, I can NOT access time.
 * There is not shop.getItems() method.
 * This is what makes for UNIDIRECTIONAL TABLES.
 * *NOTE, after this comment I am indeed making items and shops bidirectional.
 */

@Entity
@Table
public class Item {

	@Id 
	@Column(name="item_id") 
	@SequenceGenerator(sequenceName="item_seq", name="item_seq")
	@GeneratedValue(generator="item_seq", strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="ITEM_NAME")
	private String name;
	@Column(name="ITEM_PRICE")
	private Integer price;
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name="SHOP_2_ITEM", 
				joinColumns=@JoinColumn(name="Item_ID"),
				inverseJoinColumns=@JoinColumn(name="Shop_ID"))
	private List<Shop> shops;
	
	public List<Shop> getShops() {
		return shops;
	}
	public void setShops(List<Shop> shops) {
		this.shops = shops;
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
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", shops=" + shops + "]";
	}
	public Item(Integer id, String name, Integer price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.shops = new ArrayList<Shop>();
	}
	
	public Item() {
		super();
		this.shops = new ArrayList<Shop>();
		// TODO Auto-generated constructor stub
	}
	public Item(String name, Integer price) {
		super();
		this.name = name;
		this.price = price;
		this.shops = new ArrayList<Shop>();
	}
	public Item(Integer id, String name, Integer price, List<Shop> shops) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.shops = shops;
	}
	
}
