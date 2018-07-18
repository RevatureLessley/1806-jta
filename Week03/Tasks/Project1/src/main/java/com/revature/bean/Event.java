package com.revature.bean;

import java.time.LocalDateTime;

public class Event {

	private Integer id;
	private Integer empId;
	private Integer type;
	private Integer gradeScale;
	private String name;
	private LocalDateTime eventDate;
	private String location;
	private String description;
	private String justification;
	private Double cost;
	private LocalDateTime superApprove;
	private LocalDateTime headApprove;
	private LocalDateTime bencoApprove;
	private Integer reimbursementAmount;
	private Integer reimbursementConfirmation;
	private String reimbursementMessage;

	public Integer getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(Integer reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public Integer getReimbursementConfirmation() {
		return reimbursementConfirmation;
	}

	public void setReimbursementConfirmation(Integer reimbursementConfirmation) {
		this.reimbursementConfirmation = reimbursementConfirmation;
	}

	public String getReimbursementMessage() {
		return reimbursementMessage;
	}

	public void setReimbursementMessage(String reimbursementMessage) {
		this.reimbursementMessage = reimbursementMessage;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getGradeScale() {
		return gradeScale;
	}

	public void setGradeScale(Integer gradeScale) {
		this.gradeScale = gradeScale;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
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

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public LocalDateTime getSuperApprove() {
		return superApprove;
	}

	public void setSuperApprove(LocalDateTime superApprove) {
		this.superApprove = superApprove;
	}

	public LocalDateTime getHeadApprove() {
		return headApprove;
	}

	public void setHeadApprove(LocalDateTime headApprove) {
		this.headApprove = headApprove;
	}

	public LocalDateTime getBencoApprove() {
		return bencoApprove;
	}

	public void setBencoApprove(LocalDateTime bencoApprove) {
		this.bencoApprove = bencoApprove;
	}

//	public Event(Integer id, Integer empId, Integer type, Integer gradeScale, String name, LocalDateTime eventDate,
//			String location, String description, String justification, Double cost) {
//		super();
//		this.id = id;
//		this.empId = empId;
//		this.type = type;
//		this.gradeScale = gradeScale;
//		this.name = name;
//		this.eventDate = eventDate;
//		this.location = location;
//		this.description = description;
//		this.justification = justification;
//		this.cost = cost;
//	}

	public Event(Integer id, Integer empId, Integer type, Integer gradeScale, String name, LocalDateTime eventDate,
			String location, String description, String justification, Double cost, LocalDateTime superApprove,
			LocalDateTime headApprove, LocalDateTime bencoApprove, Integer reimbursementAmount,
			Integer reimbursementConfirmation, String reimbursementMessage) {
		super();
		this.id = id;
		this.empId = empId;
		this.type = type;
		this.gradeScale = gradeScale;
		this.name = name;
		this.eventDate = eventDate;
		this.location = location;
		this.description = description;
		this.justification = justification;
		this.cost = cost;
		this.superApprove = superApprove;
		this.headApprove = headApprove;
		this.bencoApprove = bencoApprove;
		this.reimbursementAmount = reimbursementAmount;
		this.reimbursementConfirmation = reimbursementConfirmation;
		this.reimbursementMessage = reimbursementMessage;
	}

	public Event(Integer id, Integer empId, Integer type, Integer gradeScale, String name, LocalDateTime eventDate,
			String location, String description, String justification, Double cost, LocalDateTime superApprove,
			LocalDateTime headApprove, LocalDateTime bencoApprove) {
		super();
		this.id = id;
		this.empId = empId;
		this.type = type;
		this.gradeScale = gradeScale;
		this.name = name;
		this.eventDate = eventDate;
		this.location = location;
		this.description = description;
		this.justification = justification;
		this.cost = cost;
		this.superApprove = superApprove;
		this.headApprove = headApprove;
		this.bencoApprove = bencoApprove;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", empId=" + empId + ", type=" + type + ", gradeScale=" + gradeScale + ", name="
				+ name + ", eventDate=" + eventDate + ", location=" + location + ", cost=" + cost + ", superApprove="
				+ superApprove + ", headApprove=" + headApprove + ", bencoApprove=" + bencoApprove + "]";
	}

}
