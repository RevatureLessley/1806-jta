package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	public Shop(Integer id, String name, Npc ownerId) {
		super();
		this.id = id;
		this.name = name;
		this.ownerId = ownerId;
	}
	public Shop() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Shop(String name, Npc ownerId) {
		super();
		this.name = name;
		this.ownerId = ownerId;
	}
	
}
