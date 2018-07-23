package com.revature.displaywrapper;

import com.revature.bean.EventDocument;

public class DocumentDisplay {

	private Integer id;
	private String name;
	private Integer eventId;
	private String docType;

	public DocumentDisplay(EventDocument eventDocument) {
		this.id = eventDocument.getId();
		this.docType = getDocType(eventDocument.getDocType());
		this.name = eventDocument.getName();
		this.eventId = eventDocument.getEventId();

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public static String getDocType(Integer docType) {
		switch (docType) {
		case 1:
			return "preapproval";
		case 2:
			return "presentation";
		default:
			return "normal";
		}
	}
}
