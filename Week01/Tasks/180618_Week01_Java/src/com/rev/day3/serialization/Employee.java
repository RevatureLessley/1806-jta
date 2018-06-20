package com.rev.day3.serialization;

import java.io.Serializable;

public class Employee implements Serializable{

	private static final long serialVersionUID = 7870103943487988592L;
		private String firstName;
		private String lastName;
		private String title;
		private String phone;
		
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		public Employee(String firstName, String lastName, String title, String phone) {
			super();
			this.firstName = firstName;
			this.lastName = lastName;
			this.title = title;
			this.phone = phone;
		}
		
		@Override
		public String toString() {
			return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", title=" + title + ", phone="
					+ phone + "]";
		}
		
		
		
		
}
