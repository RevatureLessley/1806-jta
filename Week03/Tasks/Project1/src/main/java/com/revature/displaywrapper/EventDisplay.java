package com.revature.displaywrapper;

import com.revature.bean.Event;
import com.revature.bean.EventType;
import com.revature.service.EmployeeService;
import com.revature.service.EventService;
import com.revature.service.FixedDataService;
import com.revature.utils.StringManip;

public class EventDisplay {

	private Integer id;
	private String name;
	private String typeName;
	private String cost;
	private String date;
	private EventStatus status;
	private String employeeName;
	private String gradeScaleName;
	private String superApprove;
	private String headApprove;
	private String bencoApprove;
	private EventPhase phase;
	private boolean requiresPresentation;
	private String reimbursementAmount;
	private String finalGrade;
	private boolean isUpdated;

	public EventDisplay(Event event) {
		this.id = event.getId();
		this.name = event.getName();

		EventType eventType = FixedDataService.getEventType(event);
		this.typeName = eventType.getName();
		this.cost = StringManip.formatCurrency(event.getCost());
		this.reimbursementAmount = StringManip.formatCurrency(event.getReimbursementAmount());
		this.date = StringManip.formatDate(event.getEventDate());
		this.status = EventService.getEventStatus(event);
		this.phase = EventService.getPhase(event);
		this.employeeName = EmployeeService.getEmployeeName(event.getEmpId());
		this.gradeScaleName = FixedDataService.getGradeScale(event.getGradeScale()).getName();
		this.finalGrade = EventService.getEventGrade(event.getGrade());
		this.event = event;
		this.superApprove = StringManip.formatDate(event.getSuperApprove());
		this.headApprove = StringManip.formatDate(event.getHeadApprove());
		this.bencoApprove = StringManip.formatDate(event.getBencoApprove());
		this.requiresPresentation = (FixedDataService.getGradeScale(event.getGradeScale()).getPresentation() == 1);
		this.isUpdated = false;
		
	}

	public boolean isUpdated() {
		return isUpdated;
	}

	public void setUpdated(boolean isUpdated) {
		this.isUpdated = isUpdated;
	}

	@Override
	public String toString() {
		return "EventDisplay [id=" + id + ", name=" + name + ", typeName=" + typeName + ", cost=" + cost + ", date="
				+ date + ", status=" + status + ", employeeName=" + employeeName + ", gradeScaleName=" + gradeScaleName
				+ ", superApprove=" + superApprove + ", headApprove=" + headApprove + ", bencoApprove=" + bencoApprove
				+ ", phase=" + phase + ", requiresPresentation=" + requiresPresentation + ", reimbursementAmount="
				+ reimbursementAmount + ", finalGrade=" + finalGrade + ", event=" + event + "]";
	}

	public String getFinalGrade() {
		return finalGrade;
	}

	public void setFinalGrade(String finalGrade) {
		this.finalGrade = finalGrade;
	}

	public String getReimbursementAmount() {
		return reimbursementAmount;
	}

	public void setReimbursementAmount(String reimbursementAmount) {
		this.reimbursementAmount = reimbursementAmount;
	}

	public boolean isRequiresPresentation() {
		return requiresPresentation;
	}

	public void setRequiresPresentation(boolean requiresPresentation) {
		this.requiresPresentation = requiresPresentation;
	}

	public String getGradeScaleName() {
		return gradeScaleName;
	}

	public void setGradeScaleName(String gradeScaleName) {
		this.gradeScaleName = gradeScaleName;
	}

	private Event event;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getSuperApprove() {
		return superApprove;
	}

	public void setSuperApprove(String superApprove) {
		this.superApprove = superApprove;
	}

	public String getHeadApprove() {
		return headApprove;
	}

	public void setHeadApprove(String headApprove) {
		this.headApprove = headApprove;
	}

	public String getBencoApprove() {
		return bencoApprove;
	}

	public void setBencoApprove(String bencoApprove) {
		this.bencoApprove = bencoApprove;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public EventStatus getStatus() {
		return status;
	}

	public void setStatus(EventStatus status) {
		this.status = status;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public EventPhase getPhase() {
		return phase;
	}

	public void setPhase(EventPhase phase) {
		this.phase = phase;
	}

}
