package Project1.Beans;

import java.math.*;
import java.util.*;

public class Employee {
	private Employee supervisor;
	private double availableReimbursement;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private boolean isBenco;
	private HashMap<BigInteger, Reimbursement> reimbursements; 
	
	/**
	 * @param supervisor
	 * @param availableReimbursement
	 * @param username
	 * @param password
	 * @param firstname
	 * @param lastname
	 * @param isBenco
	 */
	public Employee(Employee supervisor, double availableReimbursement,
					String username, String password, String firstname,
					String lastname, boolean isBenco) {
		this.supervisor = supervisor;	// Think about this.
		this.availableReimbursement = availableReimbursement;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.isBenco = isBenco;
		reimbursements = new HashMap<>();
	}

	public Employee getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Employee supervisor) {
		this.supervisor = supervisor;
	}

	public double getAvailableReimbursement() {
		return availableReimbursement;
	}

	public void setAvailableReimbursement(double availableReimbursement) {
		this.availableReimbursement = availableReimbursement;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public boolean isBenco() {
		return isBenco;
	}

	public void setBenco(boolean isBenco) {
		this.isBenco = isBenco;
	}
	
	public Reimbursement retrieveReimbursement(BigInteger index) {
		return reimbursements.get(index);
	}

	public Reimbursement insertReimbursement(BigInteger index, 
											 Reimbursement reimbursement) {
		return reimbursements.put(index, reimbursement);
	}
}