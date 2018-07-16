package com.trms.beans;

import java.sql.Date;

public class ReimbursementRequest {
	//    requestID number(6),
	//    requestorID number(6),
	//    statusID number(6) unique,
	//    gradeFormatID number(6),
	//    eventID number(6),
	//    requestDate date,
	//    cost float(6),
	private int requestID, requestorID, statusID, gradeFormatID, eventID;
	private Date requestDate;
	private float cost;
	
	public ReimbursementRequest() {
		super();
	}

	public ReimbursementRequest(int requestID, int requestorID, int statusID, int gradeFormatID, int eventID,
			Date requestDate, float cost) {
		super();
		this.requestID = requestID;
		this.requestorID = requestorID;
		this.statusID = statusID;
		this.gradeFormatID = gradeFormatID;
		this.eventID = eventID;
		this.requestDate = requestDate;
		this.cost = cost;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public int getRequestorID() {
		return requestorID;
	}

	public void setRequestorID(int requestorID) {
		this.requestorID = requestorID;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public int getGradeFormatID() {
		return gradeFormatID;
	}

	public void setGradeFormatID(int gradeFormatID) {
		this.gradeFormatID = gradeFormatID;
	}

	public int getEventID() {
		return eventID;
	}

	public void setEventID(int eventID) {
		this.eventID = eventID;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "ReimbursementRequest [requestID=" + requestID + ", requestorID=" + requestorID + ", statusID="
				+ statusID + ", gradeFormatID=" + gradeFormatID + ", eventID=" + eventID + ", requestDate="
				+ requestDate + ", cost=" + cost + "]";
	}
}
