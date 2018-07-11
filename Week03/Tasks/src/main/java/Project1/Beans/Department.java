package Project1.Beans;

import java.util.*;

public class Department {
	private String name;
	private HashMap<String, Employee> employees;

	/**
	 * @param name
	 */
	public Department(String name) {
		this.name = name;
		employees = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Employee retrieveEmployee(String username) {
		return employees.get(username);
	}

	public Employee insertEmployee(String username, Employee employee) {
		return employees.put(username, employee);
	}
}
