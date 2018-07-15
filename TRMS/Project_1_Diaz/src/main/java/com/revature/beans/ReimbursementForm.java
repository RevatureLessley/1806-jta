package com.revature.beans;
import java.util.Date;

public class ReimbursementForm {
	private Integer rfId;
	private Date startDate;
	private Date formsDate;
	private String gradeCutOff;
	private Integer eventId;
	private Integer eventAttachId;
	public Integer getRfId() {
		return rfId;
	}
	public void setRfId(Integer rfId) {
		this.rfId = rfId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFormsDate() {
		return formsDate;
	}
	public void setFormsDate(Date formsDate) {
		this.formsDate = formsDate;
	}
	public String getGradeCutOff() {
		return gradeCutOff;
	}
	public void setGradeCutOff(String gradeCutOff) {
		this.gradeCutOff = gradeCutOff;
	}
	public Integer getEventId() {
		return eventId;
	}
	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}
	public Integer getEventAttachId() {
		return eventAttachId;
	}
	public void setEventAttachId(Integer eventAttachId) {
		this.eventAttachId = eventAttachId;
	}
	public ReimbursementForm(Integer rfId, Date startDate, Date formsDate, String gradeCutOff, Integer eventId,
			Integer eventAttachId) {
		super();
		this.rfId = rfId;
		this.startDate = startDate;
		this.formsDate = formsDate;
		this.gradeCutOff = gradeCutOff;
		this.eventId = eventId;
		this.eventAttachId = eventAttachId;
	}
	public ReimbursementForm() {
		super();
	}
	@Override
	public String toString() {
		return "ReimbursementForm [rfId=" + rfId + ", startDate=" + startDate + ", formsDate=" + formsDate
				+ ", gradeCutOff=" + gradeCutOff + ", eventId=" + eventId + ", eventAttachId=" + eventAttachId + "]";
	}
	
	
}
