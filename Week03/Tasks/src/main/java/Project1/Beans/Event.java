package Project1.Beans;

import java.math.*;
import java.sql.*;
import java.util.HashMap;

import Project1.ApprovalType;

public class Event {
	private String type;
	private double coverage;
	private BigDecimal cost;
	private Timestamp datetime;
	private String description;
	private String location;
	private String workMissed;
	private HashMap<BigInteger, Attachment> attachments;
	
	/**
	 * @param type
	 * @param cost
	 * @param datetime
	 * @param description
	 * @param location
	 * @param workMissed
	 */
	public Event(String type, double coverage, BigDecimal cost,
				 Timestamp datetime, String description, String location,
				 String workMissed) {
		this.type = type;
		this.coverage = coverage;
		this.cost = cost;
		this.datetime = datetime;
		this.description = description;
		this.location = location;
		this.workMissed = workMissed;
		attachments = new HashMap<>();
	}
	
	public String getType() {
		return type;
	}
	
	public double getCoverage() {
		return coverage;
	}

	public void setType(Integer index) {
		type = ApprovalType.retrieveType(index);
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getWorkMissed() {
		return workMissed;
	}

	public void setWorkMissed(String workMissed) {
		this.workMissed = workMissed;
	}
	
	public HashMap<BigInteger, Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(HashMap<BigInteger, 
								  Attachment> attachments) {
		this.attachments = attachments;
	}
	
	public Attachment retrieveAttachment(BigInteger index) {
		return attachments.get(index);
	}

	public Attachment insertAttachment(BigInteger index, Attachment attachment) {
		return attachments.put(index, attachment);
	}
	
	@Override
	public String toString() {
		return "Event [type=" + type + ", coverage=" + coverage + ", cost=" + cost + ", datetime=" + datetime
				+ ", description=" + description + ", location=" + location + ", workMissed=" + workMissed
				+ ", attachments=" + attachments + "]";
	}
}