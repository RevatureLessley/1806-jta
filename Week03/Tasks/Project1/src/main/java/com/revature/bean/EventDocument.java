package com.revature.bean;

public class EventDocument {

	private Integer id;
	private Integer eventId;
	private String name;
	private Integer docType;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getDocType() {
		return docType;
	}

	public void setDocType(Integer docType) {
		this.docType = docType;
	}

	@Override
	public String toString() {
		return "EventDocument [id=" + id + ", eventId=" + eventId + ", name=" + name + ", docType=" + docType + "]";
	}

	public EventDocument(Integer id, Integer eventId, String name, Integer docType) {
		super();
		this.id = id;
		this.eventId = eventId;
		this.name = name;
		this.docType = docType;
	}

}
