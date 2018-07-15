package com.revature.beans;
import java.util.Date;

public class ReimbursementForm {
	private String  rfId;
	private String startDate;
	private String formsDate;
	private String gradeCutOff;
	private String eventId;
	private String eventAttachId;
	public String getRfId() {
		return rfId;
	}
	public void setRfId(String rfId) {
		this.rfId = rfId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFormsDate() {
		return formsDate;
	}
	public void setFormsDate(String formsDate) {
		this.formsDate = formsDate;
	}
	public String getGradeCutOff() {
		return gradeCutOff;
	}
	public void setGradeCutOff(String gradeCutOff) {
		this.gradeCutOff = gradeCutOff;
	}
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventAttachId() {
		return eventAttachId;
	}
	public void setEventAttachId(String eventAttachId) {
		this.eventAttachId = eventAttachId;
	}
	public ReimbursementForm( String startDate, String formsDate, String gradeCutOff, String eventId,
			String eventAttachId) {
		super();
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
