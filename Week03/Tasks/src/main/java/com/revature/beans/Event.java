package com.revature.beans;

public class Event {
	private int eventId;
	private String eventN;
	private int eventTypeId;
	private int gradeF;
	private double cutoffG;
	private double eventC;
	/**
	 * @return the eventId
	 */
	public int getEventId() {
		return eventId;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	/**
	 * @return the eventN
	 */
	public String getEventN() {
		return eventN;
	}
	/**
	 * @param eventN the eventN to set
	 */
	public void setEventN(String eventN) {
		this.eventN = eventN;
	}
	/**
	 * @return the eventTypeId
	 */
	public int getEventTypeId() {
		return eventTypeId;
	}
	/**
	 * @param eventTypeId the eventTypeId to set
	 */
	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}
	/**
	 * @return the gradeF
	 */
	public int getGradeF() {
		return gradeF;
	}
	/**
	 * @param gradeF the gradeF to set
	 */
	public void setGradeF(int gradeF) {
		this.gradeF = gradeF;
	}
	/**
	 * @return the cutoffG
	 */
	public double getCutoffG() {
		return cutoffG;
	}
	/**
	 * @param cutoffG the cutoffG to set
	 */
	public void setCutoffG(double cutoffG) {
		this.cutoffG = cutoffG;
	}
	/**
	 * @return the eventC
	 */
	public double getEventC() {
		return eventC;
	}
	/**
	 * @param eventC the eventC to set
	 */
	public void setEventC(double eventC) {
		this.eventC = eventC;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", eventN=" + eventN + ", eventTypeId=" + eventTypeId + ", gradeF="
				+ gradeF + ", cutoffG=" + cutoffG + ", eventC=" + eventC + "]";
	}
	
}
