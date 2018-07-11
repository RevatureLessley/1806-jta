package Project1.Beans;

import java.math.*;
import java.sql.*;
import java.util.HashMap;

import Project1.ApprovalType;

public class Event {
	private String type;
	private BigDecimal cost;
	private Timestamp datetime;
	private String description;
	private String location;
	private String workMissed;	//Note to Self: INTERVALDS?
	private HashMap<String, Attachment> attachments;
	
	/**
	 * @param type
	 * @param cost
	 * @param datetime
	 * @param description
	 * @param location
	 * @param workMissed
	 */
	public Event(String type, BigDecimal cost, Timestamp datetime,
				 String description, String location, String workMissed) {
		this.type = type;
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
	
	public Attachment retrieveAttachment(String name) {
		return attachments.get(name);
	}

	public Attachment insertAttachment(String name, Attachment attachment) {
		return attachments.put(name, attachment);
	}
}