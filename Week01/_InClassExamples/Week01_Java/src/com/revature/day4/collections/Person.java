package com.revature.day4.collections;

public class Person implements Comparable<Person>{
	private String name;
	private int age;
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	@Override
	public int compareTo(Person p) {
		/*
		 * Any comparison method in programming simple checks two
		 * elements together and returns a number. if the number is
		 * negative, that means the left object comes first. If the
		 * number is positive, that means the right object comes
		 * first. If the number is 0, they are the same.
		 */
		return this.getName().compareTo(p.getName());
	}
	
	
}
