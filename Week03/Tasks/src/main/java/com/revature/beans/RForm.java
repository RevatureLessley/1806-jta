package com.revature.beans;
import java.sql.Date;
public class RForm {
	private int rFormId;
	private int empid;
	private Date rFormDate;
	private Date deadlineDate;
	private String place;
	private String info;
	private int EventId;
	private double propReim;
	private String justification;
	private int timeMissed;
	private int formClosed;
	private int appLvl;
	/**
	 * @return the rFormId
	 */
	public int getrFormId() {
		return rFormId;
	}
	/**
	 * @param rFormId the rFormId to set
	 */
	public void setrFormId(int rFormId) {
		this.rFormId = rFormId;
	}
	/**
	 * @return the empid
	 */
	public int getEmpid() {
		return empid;
	}
	/**
	 * @param empid the empid to set
	 */
	public void setEmpid(int empid) {
		this.empid = empid;
	}
	/**
	 * @return the rFormDate
	 */
	public Date getrFormDate() {
		return rFormDate;
	}
	/**
	 * @param rFormDate the rFormDate to set
	 */
	public void setrFormDate(Date rFormDate) {
		this.rFormDate = rFormDate;
	}
	/**
	 * @return the deadlineDate
	 */
	public Date getDeadlineDate() {
		return deadlineDate;
	}
	/**
	 * @param deadlineDate the deadlineDate to set
	 */
	public void setDeadlineDate(Date deadlineDate) {
		this.deadlineDate = deadlineDate;
	}
	/**
	 * @return the place
	 */
	public String getPlace() {
		return place;
	}
	/**
	 * @param place the place to set
	 */
	public void setPlace(String place) {
		this.place = place;
	}
	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}
	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	/**
	 * @return the eventId
	 */
	public int getEventId() {
		return EventId;
	}
	/**
	 * @param eventId the eventId to set
	 */
	public void setEventId(int eventId) {
		EventId = eventId;
	}
	/**
	 * @return the propReim
	 */
	public double getPropReim() {
		return propReim;
	}
	/**
	 * @param propReim the propReim to set
	 */
	public void setPropReim(double propReim) {
		this.propReim = propReim;
	}
	/**
	 * @return the justification
	 */
	public String getJustification() {
		return justification;
	}
	/**
	 * @param justification the justification to set
	 */
	public void setJustification(String justification) {
		this.justification = justification;
	}
	/**
	 * @return the timeMissed
	 */
	public int getTimeMissed() {
		return timeMissed;
	}
	/**
	 * @param timeMissed the timeMissed to set
	 */
	public void setTimeMissed(int timeMissed) {
		this.timeMissed = timeMissed;
	}
	/**
	 * @return the formClosed
	 */
	public int getFormClosed() {
		return formClosed;
	}
	/**
	 * @param formClosed the formClosed to set
	 */
	public void setFormClosed(int formClosed) {
		this.formClosed = formClosed;
	}
	/**
	 * @return the appLvl
	 */
	public int getAppLvl() {
		return appLvl;
	}
	/**
	 * @param appLvl the appLvl to set
	 */
	public void setAppLvl(int appLvl) {
		this.appLvl = appLvl;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RForm [rFormId=" + rFormId + ", empid=" + empid + ", rFormDate=" + rFormDate + ", deadlineDate="
				+ deadlineDate + ", place=" + place + ", info=" + info + ", EventId=" + EventId + ", propReim="
				+ propReim + ", justification=" + justification + ", timeMissed=" + timeMissed + ", formClosed="
				+ formClosed + ", appLvl=" + appLvl + "]";
	}
	
	
}
