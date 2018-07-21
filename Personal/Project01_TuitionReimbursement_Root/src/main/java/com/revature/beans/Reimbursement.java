package com.revature.beans;

public class Reimbursement {
	int id;
	int docId;
	int empId;
	int approverId;
	String date;
	String location;
	String description;
	double cost;
	String gradingFormat;
	String type;
	double coveragePercent;
	String justification;
	
	public Reimbursement() {
		super();
	}
	
	public Reimbursement(int id, int docId, int empId, int approverId, String date, String location, String description, double cost,
			String gradingFormat, String type, double coveragePercent, String justification) {
		super();
		this.id = id;
		this.docId = docId;
		this.empId = empId;
		this.approverId = approverId;
		this.date = date;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gradingFormat = gradingFormat;
		this.type = type;
		this.coveragePercent = coveragePercent;
		this.justification = justification;
	}
	
	public Reimbursement(int empId, int approverId, String date, String location, String description, 
			double cost, String gradingFormat, String type, double coveragePercent, String justification) {
		this.empId = empId;
		this.approverId = approverId;
		this.date = date;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gradingFormat = gradingFormat;
		this.type = type;
		this.coveragePercent = coveragePercent;
		this.justification = justification;
	}
	
	public Reimbursement(int id, int empId, int approverId, String date, String location, String description, 
			double cost, String gradingFormat, String type, double coveragePercent, String justification) {
		this.id = id;
		this.empId = empId;
		this.approverId = approverId;
		this.date = date;
		this.location = location;
		this.description = description;
		this.cost = cost;
		this.gradingFormat = gradingFormat;
		this.type = type;
		this.coveragePercent = coveragePercent;
		this.justification = justification;
	}
	
	public int getApproverId() {
		return approverId;
	}

	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDocId() {
		return docId;
	}
	public void setDocId(int docId) {
		this.docId = docId;
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
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
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getGradingFormat() {
		return gradingFormat;
	}
	public void setGradingFormat(String gradingFormat) {
		this.gradingFormat = gradingFormat;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getCoveragePercent() {
		return coveragePercent;
	}
	public void setCoveragePercent(double coveragePercent) {
		this.coveragePercent = coveragePercent;
	}
	public String getJustification() {
		return justification;
	}
	public void setJustification(String justification) {
		this.justification = justification;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", docId=" + docId + ", empId=" + empId + ", approverId=" + approverId
				+ ", date=" + date + ", location=" + location + ", description=" + description + ", cost=" + cost
				+ ", gradingFormat=" + gradingFormat + ", type=" + type + ", coveragePercent=" + coveragePercent
				+ ", justification=" + justification + "]";
	}
	
}
