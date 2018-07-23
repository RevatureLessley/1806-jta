package com.revature.beans;

import java.sql.Date;

public class ReimbursementReq {
	
	private int reimbursementId;
	private int empId;
	private int eventId;
	private int eventType;
	private Date eventDate;
	private float amountReq;
	private boolean isDsApprove;
	private Date dsApproveDate;
	private boolean isDhApprove;
	private Date dhApproveDate;
	private boolean isBcApprove;
	private Date bcApproveDate;
	private boolean isExceedAmountApproved;
	
///////////////////////////////////////////////
// Constructors ///////////////////////////////
///////////////////////////////////////////////
	
	public ReimbursementReq() 
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
public ReimbursementReq(int empId, int eventId, int eventType, Date eventDate, float amountReq, boolean isDsApprove,
		boolean isDhApprove, boolean isBcApprove, boolean isExceedAmountApproved)
{
	super();
	this.empId = empId;
	this.eventId = eventId;
	this.eventType = eventType;
	this.eventDate = eventDate;
	this.amountReq = amountReq;
	this.isDsApprove = isDsApprove;
	this.isDhApprove = isDhApprove;
	this.isBcApprove = isBcApprove;
	this.isExceedAmountApproved = isExceedAmountApproved;
}



public ReimbursementReq(int reimbursementId, int empId, int eventId, int eventType, Date eventDate, float amountReq,
		boolean isDsApprove, Date dsApproveDate, boolean isDhApprove, Date dhApproveDate, boolean isBcApprove,
		Date bcApproveDate, boolean isExceedAmountApproved)
{
	super();
	this.reimbursementId = reimbursementId;
	this.empId = empId;
	this.eventId = eventId;
	this.eventType = eventType;
	this.eventDate = eventDate;
	this.amountReq = amountReq;
	this.isDsApprove = isDsApprove;
	this.dsApproveDate = dsApproveDate;
	this.isDhApprove = isDhApprove;
	this.dhApproveDate = dhApproveDate;
	this.isBcApprove = isBcApprove;
	this.bcApproveDate = bcApproveDate;
	this.isExceedAmountApproved = isExceedAmountApproved;
}



///////////////////////////////////////////////
//Getters and Setters ////////////////////////
///////////////////////////////////////////////



	public int getReimbursementId()
	{
		return reimbursementId;
	}

	public void setReimbursementId(int reimbursementId)
	{
		this.reimbursementId = reimbursementId;
	}

	public int getEmpId()
	{
		return empId;
	}

	public void setEmpId(int empId)
	{
		this.empId = empId;
	}

	public int getEventId()
	{
		return eventId;
	}

	public void setEventId(int eventId)
	{
		this.eventId = eventId;
	}
	
	public int getEventType()
	{
		return eventType;
	}
	public void setEventType(int eventType)
	{
		this.eventType = eventType;
	}
	public Date getEventDate()
	{
		return eventDate;
	}
	public void setEventDate(Date eventDate)
	{
		this.eventDate = eventDate;
	}


	public float getAmountReq()
	{
		return amountReq;
	}

	public void setAmountReq(float amountReq)
	{
		this.amountReq = amountReq;
	}

	public boolean isDsApprove()
	{
		return isDsApprove;
	}

	public void setDsApprove(boolean isDsApprove)
	{
		this.isDsApprove = isDsApprove;
	}

	public Date getDsApproveDate()
	{
		return dsApproveDate;
	}

	public void setDsApproveDate(Date dsApproveDate)
	{
		this.dsApproveDate = dsApproveDate;
	}

	public boolean isDhApprove()
	{
		return isDhApprove;
	}

	public void setDhApprove(boolean isDhApprove)
	{
		this.isDhApprove = isDhApprove;
	}

	public Date getDhApproveDate()
	{
		return dhApproveDate;
	}

	public void setDhApproveDate(Date dhApproveDate)
	{
		this.dhApproveDate = dhApproveDate;
	}

	public boolean isBcApprove()
	{
		return isBcApprove;
	}

	public void setBcApprove(boolean isBcApprove)
	{
		this.isBcApprove = isBcApprove;
	}

	public Date getBcApproveDate()
	{
		return bcApproveDate;
	}

	public void setBcApproveDate(Date bcApproveDate)
	{
		this.bcApproveDate = bcApproveDate;
	}
	public boolean isExceedAmountApproved()
	{
		return isExceedAmountApproved;
	}
	public void setExceedAmountApproved(boolean isExceedAmountApproved)
	{
		this.isExceedAmountApproved = isExceedAmountApproved;
	}

	///////////////////////////////////////////////
	// toString ///////////////////////////////////
	///////////////////////////////////////////////

	@Override
	public String toString() 
	{
		return "ReimbursementReq [reimbursementId=" + reimbursementId + ", empId=" + empId + ", eventId=" + eventId
				+ ", amountReq=" + amountReq + ", isDsApprove=" + isDsApprove + ", dsApproveDate=" + dsApproveDate
				+ ", isDhApprove=" + isDhApprove + ", dhApproveDate=" + dhApproveDate + ", isBcApprove=" + isBcApprove
				+ ", bcApproveDate=" + bcApproveDate + "]";
	}
	
	
	
	
	
}
