package com.revature.bean;

import java.util.Date;

public class Reimbursement {
	private int id;
	private int empId;
	private Date reimDate;
	private Date startDate;
	private String location;
	private String description;
	private int cost;
	private int gradeId;
	private int trainId;
	private int passGrade;
	private String justification;
	private boolean dsApproval;
	private boolean dhApproval;
	private boolean bencoApproval;
	private String firstName;
	private String lastName;
	
	
	
	public Reimbursement(int id, int empId, Date reimDate, Date startDate, String location, String description,
			int cost, int gradeId, int trainId, int passGrade, String justification, boolean dsApproval,
			boolean dhApproval, boolean bencoApproval, String firstName, String lastName) {
		super();
		this.id = id;
		this.empId = empId;
		this.reimDate = reimDate;
		this.startDate = startDate;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gradeId = gradeId;
		this.trainId = trainId;
		this.passGrade = passGrade;
		this.justification = justification;
		this.dsApproval = dsApproval;
		this.dhApproval = dhApproval;
		this.bencoApproval = bencoApproval;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Reimbursement(int id, int empId, Date reimDate, Date startDate, String location, String description,
			int cost, int gradeId, int trainId, int passGrade, String justification, boolean dsApproval,
			boolean dhApproval, boolean bencoApproval) {
		super();
		this.id = id;
		this.empId = empId;
		this.reimDate = reimDate;
		this.startDate = startDate;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gradeId = gradeId;
		this.trainId = trainId;
		this.passGrade = passGrade;
		this.justification = justification;
		this.dsApproval = dsApproval;
		this.dhApproval = dhApproval;
		this.bencoApproval = bencoApproval;
	}
	
	public Reimbursement(int empId, Date reimDate, Date startDate, String location, String description,
			int cost, int gradeId, int trainId, int passGrade, String justification, boolean dsApproval,
			boolean dhApproval, boolean bencoApproval) {
		super();
		this.empId = empId;
		this.reimDate = reimDate;
		this.startDate = startDate;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gradeId = gradeId;
		this.trainId = trainId;
		this.passGrade = passGrade;
		this.justification = justification;
		this.dsApproval = dsApproval;
		this.dhApproval = dhApproval;
		this.bencoApproval = bencoApproval;
	}

	public Reimbursement(int empId, Date reimDate, Date startDate, String location, String description,
			int cost, int gradeId, int trainId, int passGrade, String justification) {
		super();
		this.empId = empId;
		this.reimDate = reimDate;
		this.startDate = startDate;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gradeId = gradeId;
		this.trainId = trainId;
		this.passGrade = passGrade;
		this.justification = justification;
	}
	
	public Reimbursement() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public Date getReimDate() {
		return reimDate;
	}

	public void setReimDate(Date reimDate) {
		this.reimDate = reimDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getGradeId() {
		return gradeId;
	}

	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public int getPassGrade() {
		return passGrade;
	}

	public void setPassGrade(int passGrade) {
		this.passGrade = passGrade;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}
	
	public boolean isDsApproval() {
		return dsApproval;
	}

	public void setDsApproval(boolean dsApproval) {
		this.dsApproval = dsApproval;
	}

	public boolean isDhApproval() {
		return dhApproval;
	}

	public void setDhApproval(boolean dhApproval) {
		this.dhApproval = dhApproval;
	}

	public boolean isBencoApproval() {
		return bencoApproval;
	}
	public void setBencoApproval(boolean bencoApproval) {
		this.bencoApproval = bencoApproval;
	}

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
	
}
