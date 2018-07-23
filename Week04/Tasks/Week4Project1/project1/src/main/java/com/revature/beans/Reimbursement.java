package com.revature.beans;

/**
 * Employee Bean class with the following attributes:
 * <br>-Integer rId
 * <br>-String  eventDate
 * <br>-String  eventTime
 * <br>-String  eventLocation
 * <br>-String  eventDesc
 * <br>-Integer eventCost
 * <br>-String  justification
 * <br>-Integer gradeCutoff
 * <br>-Integer grade
 * <br>-Integer empId
 * <br>-Integer eventId
 * <br>-Integer gradingFormatId
 * <br>-Integer docId
 * <br>-Integer approvalId
 * <br>
 * @author Logan Brewer
 *
 */
public class Reimbursement 
{
	Integer rId;
	String eventDate;
	String eventTime;
	String eventLocation;
	String eventDesc;
	Integer eventCost;
	String justification;
	Integer gradeCutoff;
	Integer grade;
	Integer empId;
	Integer eventId;
	Integer gradingFormatId;
	Integer docId;
	Integer approvalId;
	
	public Reimbursement(Integer rId, String eventDate, String eventTime, String eventLocation, String eventDesc,
			Integer eventCost, String justification, Integer gradeCutoff, Integer grade, Integer empId, Integer eventId,
			Integer gradingFormatId, Integer docId, Integer approvalId) 
	{
		super();
		this.rId = rId;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventLocation = eventLocation;
		this.eventDesc = eventDesc;
		this.eventCost = eventCost;
		this.justification = justification;
		this.gradeCutoff = gradeCutoff;
		this.grade = grade;
		this.empId = empId;
		this.eventId = eventId;
		this.gradingFormatId = gradingFormatId;
		this.docId = docId;
		this.approvalId = approvalId;
	}

	public Reimbursement(String eventDate, String eventTime, String eventLocation, String eventDesc, Integer eventCost,
			String justification, Integer gradeCutoff, Integer grade, Integer empId, Integer eventId,
			Integer gradingFormatId, Integer docId, Integer approvalId) 
	{
		super();
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventLocation = eventLocation;
		this.eventDesc = eventDesc;
		this.eventCost = eventCost;
		this.justification = justification;
		this.gradeCutoff = gradeCutoff;
		this.grade = grade;
		this.empId = empId;
		this.eventId = eventId;
		this.gradingFormatId = gradingFormatId;
		this.docId = docId;
		this.approvalId = approvalId;
	}
	
	public Reimbursement(String eventDesc, String eventDate, String eventTime, 
						 String eventLocation, Integer eventCost) 
	{
		super();
		this.eventDesc = eventDesc;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventLocation = eventLocation;
		this.eventCost = eventCost;
	}
	
	public Reimbursement() 
	{
		super();
	}

	public Integer getrId() 
	{
		return rId;
	}
	
	public void setrId(Integer rId) 
	{
		this.rId = rId;
	}
	
	public String getEventDate() 
	{
		return eventDate;
	}
	
	public void setEventDate(String eventDate) 
	{
		this.eventDate = eventDate;
	}
	
	public String getEventTime() 
	{
		return eventTime;
	}
	
	public void setEventTime(String eventTime) 
	{
		this.eventTime = eventTime;
	}
	
	public String getEventLocation() 
	{
		return eventLocation;
	}
	
	public void setEventLocation(String eventLocation) 
	{
		this.eventLocation = eventLocation;
	}
	
	public String getEventDesc() 
	{
		return eventDesc;
	}
	
	public void setEventDesc(String eventDesc) 
	{
		this.eventDesc = eventDesc;
	}
	
	public Integer getEventCost() 
	{
		return eventCost;
	}
	
	public void setEventCost(Integer eventCost) 
	{
		this.eventCost = eventCost;
	}
	
	public String getJustification() 
	{
		return justification;
	}
	
	public void setJustification(String justification) 
	{
		this.justification = justification;
	}
	
	public Integer getGradeCutoff() 
	{
		return gradeCutoff;
	}
	
	public void setGradeCutoff(Integer gradeCutoff) 
	{
		this.gradeCutoff = gradeCutoff;
	}
	
	public Integer getGrade() 
	{
		return grade;
	}
	
	public void setGrade(Integer grade) 
	{
		this.grade = grade;
	}
	
	public Integer getEmpId() 
	{
		return empId;
	}
	
	public void setEmpId(Integer empId) 
	{
		this.empId = empId;
	}
	
	public Integer getEventId() 
	{
		return eventId;
	}
	
	public void setEventId(Integer eventId) 
	{
		this.eventId = eventId;
	}
	
	public Integer getGradingFormatId() 
	{
		return gradingFormatId;
	}
	
	public void setGradingFormatId(Integer gradingFormatId) 
	{
		this.gradingFormatId = gradingFormatId;
	}
	
	public Integer getDocId() 
	{
		return docId;
	}
	
	public void setDocId(Integer docId) 
	{
		this.docId = docId;
	}
	
	public Integer getApprovalId() 
	{
		return approvalId;
	}
	
	public void setApprovalId(Integer approvalId) 
	{
		this.approvalId = approvalId;
	}

	@Override
	public String toString() 
	{
		return "Reimbursement [rId=" + rId + ", eventDate=" + eventDate + ", eventTime=" + eventTime
				+ ", eventLocation=" + eventLocation + ", eventDesc=" + eventDesc + ", eventCost=" + eventCost
				+ ", justification=" + justification + ", gradeCutoff=" + gradeCutoff + ", grade=" + grade + ", empId="
				+ empId + ", eventId=" + eventId + ", gradingFormatId=" + gradingFormatId + ", docId=" + docId
				+ ", approvalId=" + approvalId + "]";
	}
		
}
