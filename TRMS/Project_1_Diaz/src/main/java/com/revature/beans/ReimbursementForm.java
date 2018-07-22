package com.revature.beans;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Date;

public class ReimbursementForm {
	private Integer  rfId;
	private Integer empId;
	private String formStatus;
	private String formStatus2;
	private String formStatus3;
	private String eventType;
	private String eventLocation;
	private String eventDescribtion;
	private Integer eventCost;
	private Date formsDate;
	private Date startDate;
	public String getFormStatus2() {
		return formStatus2;
	}
	public void setFormStatus2(String formStatus2) {
		this.formStatus2 = formStatus2;
	}
	public String getFormStatus3() {
		return formStatus3;
	}
	public void setFormStatus3(String formStatus3) {
		this.formStatus3 = formStatus3;
	}
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
	public Date getFormsDate() {
		return formsDate;
	}
	public void setFormsDate(Date formsDate) {
		this.formsDate = formsDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
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
			String eventDescribtion, Integer eventCost, Date formsDate, Date startDate, Timestamp startTime,
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
			Integer eventCost, Date formsDate, Date startDate, Timestamp startTime, String gradeCutOff,
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
			Integer eventCost, Date startDate, Timestamp startTime, String gradeFormat, String gradeCutOff,
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
	
	public ReimbursementForm(Integer rfId, Integer empId, String formStatus, String eventType, String eventLocation,
			String eventDescribtion, Integer eventCost, Date formsDate, Date startDate,
			String gradeFormat, String gradeCutOff, String workTimeMissed) {
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
		this.gradeFormat = gradeFormat;
		this.gradeCutOff = gradeCutOff;
		this.workTimeMissed = workTimeMissed;
	}
	
	public ReimbursementForm(Integer empId, String eventType, String eventLocation, String eventDescribtion,
			Integer eventCost, Date formsDate, Date startDate, String gradeFormat, String gradeCutOff,
			String workTimeMissed) {
		super();
		this.empId = empId;
		this.eventType = eventType;
		this.eventLocation = eventLocation;
		this.eventDescribtion = eventDescribtion;
		this.eventCost = eventCost;
		this.formsDate = formsDate;
		this.startDate = startDate;
		this.gradeFormat = gradeFormat;
		this.gradeCutOff = gradeCutOff;
		this.workTimeMissed = workTimeMissed;
	}
	
	public ReimbursementForm(String formStatus, String formStatus2, String formStatus3, String eventType,
			String eventLocation, String eventDescribtion, Integer eventCost, Date formsDate, Date startDate,
			String gradeFormat, String gradeCutOff, String workTimeMissed) {
		super();
		this.formStatus = formStatus;
		this.formStatus2 = formStatus2;
		this.formStatus3 = formStatus3;
		this.eventType = eventType;
		this.eventLocation = eventLocation;
		this.eventDescribtion = eventDescribtion;
		this.eventCost = eventCost;
		this.formsDate = formsDate;
		this.startDate = startDate;
		this.gradeFormat = gradeFormat;
		this.gradeCutOff = gradeCutOff;
		this.workTimeMissed = workTimeMissed;
	}
	
	public ReimbursementForm(Integer rfId, Integer empId, String formStatus, String formStatus2, String formStatus3,
			String eventType, String eventLocation, String eventDescribtion, Integer eventCost, Date formsDate,
			Date startDate, String gradeFormat, String gradeCutOff, String workTimeMissed) {
		super();
		this.rfId = rfId;
		this.empId = empId;
		this.formStatus = formStatus;
		this.formStatus2 = formStatus2;
		this.formStatus3 = formStatus3;
		this.eventType = eventType;
		this.eventLocation = eventLocation;
		this.eventDescribtion = eventDescribtion;
		this.eventCost = eventCost;
		this.formsDate = formsDate;
		this.startDate = startDate;
		this.gradeFormat = gradeFormat;
		this.gradeCutOff = gradeCutOff;
		this.workTimeMissed = workTimeMissed;
	}
	
	public ReimbursementForm(Integer rfId, String formStatus3) {
		super();
		this.rfId = rfId;
		this.formStatus3 = formStatus3;
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
