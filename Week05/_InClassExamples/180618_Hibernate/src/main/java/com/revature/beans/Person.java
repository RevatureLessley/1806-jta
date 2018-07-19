package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

//This is just bean.
/*
 * However, once I map it, this becomes persistent class.
 * There are two ways to map a persistent class:
 * 1. The annoying, by using hibernate mapping files
 * 2. The easy way, use annotations
 */

@Entity //marks the class as a persistent class
@Table(name="Person") //Configuration details for the SQL table
public class Person {
	
	@Id //Marks the next variable as a primary key
	@Column(name="pid") //Optional field to configure column info
	@SequenceGenerator(sequenceName="MY_SEQ", name="JAVA_NAME")
	@GeneratedValue(generator="JAVA_NAME", strategy=GenerationType.SEQUENCE)
	private Integer id;
	
	@Column
	private String name;
	@Column
	private String title;
	@Column
	private Integer salary;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getSalary() {
		return salary;
	}
	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", title=" + title + ", salary=" + salary + "]";
	}
	public Person(Integer id, String name, String title, Integer salary) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.salary = salary;
	}
	public Person(String name, String title, Integer salary) {
		super();
		this.name = name;
		this.title = title;
		this.salary = salary;
	}
	
	public Person() {
		super();
	}
	
	
	
}
