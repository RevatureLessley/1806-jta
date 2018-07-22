package com.revature.beans;
import java.sql.Timestamp;
import java.util.Date;

public class ReimbursementForm {
	private Integer  rfId;
	private Integer empId;
	private String formStatus;
	private String eventType;
	private String eventLocation;
	private String eventDescribtion;
	private Integer eventCost;
	private Timestamp formsDate;
	private Timestamp startDate;
	private Timestamp startTime;
	private String gradeFormat;
	public String getGradeFormat() {
		return gradeFormat;
	}
	public void setGradeFormat(String gradeFormat) {
		this.gradeFormat = gradeFormat;
	}
	private String gradeCutOff;
	private String workTimeMissed;
	public Integer getRfId() {
		return rfId;
	}
	public void setRfId(Integer rfId) {
		this.rfId = rfId;
	}
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getFormStatus() {
		return formStatus;
	}
	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	public String getEventDescribtion() {
		return eventDescribtion;
	}
	public void setEventDescribtion(String eventDescribtion) {
		this.eventDescribtion = eventDescribtion;
	}
	public Integer getEventCost() {
		return eventCost;
	}
	public void setEventCost(Integer eventCost) {
		this.eventCost = eventCost;
	}
	public Timestamp getFormsDate() {
		return formsDate;
	}
	public void setFormsDate(Timestamp formsDate) {
		this.formsDate = formsDate;
	}
	public Timestamp getStartDate() {
		return startDate;
	}
	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public String getGradeCutOff() {
		return gradeCutOff;
	}
	public void setGradeCutOff(String gradeCutOff) {
		this.gradeCutOff = gradeCutOff;
	}
	public String getWorkTimeMissed() {
		return workTimeMissed;
	}
	public void setWorkTimeMissed(String workTimeMissed) {
		this.workTimeMissed = workTimeMissed;
	}
	public ReimbursementForm(Integer rfId, Integer empId, String formStatus, String eventType, String eventLocation,
			String eventDescribtion, Integer eventCost, Timestamp formsDate, Timestamp startDate, Timestamp startTime,
			String gradeCutOff, String workTimeMissed) {
		super();
		this.rfId = rfId;
		this.empId = empId;
		this.formStatus = formStatus;
		this.eventType = eventType;
		this.eventLocation = eventLocation;
		this.eventDescribtion = eventDescribtion;
		this.eventCost = eventCost;
		this.formsDate = formsDate;
		this.startDate = startDate;
		this.startTime = startTime;
		this.gradeCutOff = gradeCutOff;
		this.workTimeMissed = workTimeMissed;
	}
	
	public ReimbursementForm(String formStatus, String eventType, String eventLocation, String eventDescribtion,
			Integer eventCost, Timestamp formsDate, Timestamp startDate, Timestamp startTime, String gradeCutOff,
			String workTimeMissed) {
		super();
		this.formStatus = formStatus;
		this.eventType = eventType;
		this.eventLocation = eventLocation;
		this.eventDescribtion = eventDescribtion;
		this.eventCost = eventCost;
		this.formsDate = formsDate;
		this.startDate = startDate;
		this.startTime = startTime;
		this.gradeCutOff = gradeCutOff;
		this.workTimeMissed = workTimeMissed;
	}
	
	public ReimbursementForm(Integer empId, String eventType, String eventLocation, String eventDescribtion,
			Integer eventCost, Timestamp startDate, Timestamp startTime, String gradeFormat, String gradeCutOff,
			String workTimeMissed) {
		super();
		this.empId = empId;
		this.eventType = eventType;
		this.eventLocation = eventLocation;
		this.eventDescribtion = eventDescribtion;
		this.eventCost = eventCost;
		this.startDate = startDate;
		this.startTime = startTime;
		this.gradeFormat = gradeFormat;
		this.gradeCutOff = gradeCutOff;
		this.workTimeMissed = workTimeMissed;
	}
	public ReimbursementForm() {
		super();
		
	}
	@Override
	public String toString() {
		return "ReimbursementForm [rfId=" + rfId + ", empId=" + empId + ", formStatus=" + formStatus + ", eventType="
				+ eventType + ", eventLocation=" + eventLocation + ", eventDescribtion=" + eventDescribtion
				+ ", eventCost=" + eventCost + ", formsDate=" + formsDate + ", startDate=" + startDate + ", startTime="
				+ startTime + ", gradeCutOff=" + gradeCutOff + ", workTimeMissed=" + workTimeMissed + "]";
	}
	
	
	
	
}
