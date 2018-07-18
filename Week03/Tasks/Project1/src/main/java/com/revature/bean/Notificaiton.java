package com.revature.bean;

import java.time.LocalDateTime;

public class Notificaiton {
	private Integer id;
	private Integer empTarget;
	private Integer empSource;
	private String message;
	private Integer eventId;
	private Integer read;
	private LocalDateTime timestamp;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getEmpTarget() {
		return empTarget;
	}

	public void setEmpTarget(Integer empTarget) {
		this.empTarget = empTarget;
	}

	public Integer getEmpSource() {
		return empSource;
	}

	public void setEmpSource(Integer empSource) {
		this.empSource = empSource;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getEventId() {
		return eventId;
	}

	public void setEventId(Integer eventId) {
		this.eventId = eventId;
	}

	public Integer getRead() {
		return read;
	}

	public void setRead(Integer read) {
		this.read = read;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Notificaiton [id=" + id + ", empTarget=" + empTarget + ", empSource=" + empSource + ", message="
				+ message + ", eventId=" + eventId + ", read=" + read + ", timestamp=" + timestamp + "]";
	}

	public Notificaiton(Integer id, Integer empTarget, Integer empSource, String message, Integer eventId, Integer read,
			LocalDateTime timestamp) {
		super();
		this.id = id;
		this.empTarget = empTarget;
		this.empSource = empSource;
		this.message = message;
		this.eventId = eventId;
		this.read = read;
		this.timestamp = timestamp;
	}

}
