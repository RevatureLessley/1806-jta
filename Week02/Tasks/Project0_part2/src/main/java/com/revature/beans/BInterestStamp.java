package com.revature.beans;

import java.time.LocalDateTime;

public class BInterestStamp {
	
	private Integer id;
	private LocalDateTime localDateTime;
	
	public BInterestStamp(int id, LocalDateTime localDateTime) {
		super();
		this.id = id;
		this.localDateTime = localDateTime;
	}

	public BInterestStamp(LocalDateTime localDateTime) {
		super();
		this.id = null;
		this.localDateTime = localDateTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}

	@Override
	public String toString() {
		return "BInterestStamp [id=" + id + ", localDateTime=" + localDateTime + "]";
	}
	
	
}
