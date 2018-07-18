package com.revature.beans;
import java.sql.Date;
public class RForm {
	private int rFormId;
	private int empid;
	private Date rFormDate;
	private String place;
	private String info;
	private int EventId;
	private double propReim;
	private String justification;
	private String filekey;
	private int timeMissed;
	private int formClosed;
	private int appLvl;
	
	public RForm(int rFormId, int empid, Date rFormDate, String place, String info, int eventId,
			double propReim, String justification, String filekey, int timeMissed, int formClosed, int appLvl) {
		super();
		this.rFormId = rFormId;
		this.empid = empid;
		this.rFormDate = rFormDate;
		this.place = place;
		this.info = info;
		this.EventId = eventId;
		this.propReim = propReim;
		this.justification = justification;
		this.filekey = filekey;
		this.timeMissed = timeMissed;
		this.formClosed = formClosed;
		this.appLvl = appLvl;
	}
	public RForm(int empid, Date rFormDate, String place, String info, int eventId,
			double propReim, String justification, String filekey, int timeMissed) {
		super();
		this.empid = empid;
		this.rFormDate = rFormDate;
		this.place = place;
		this.info = info;
		this.EventId = eventId;
		this.propReim = propReim;
		this.justification = justification;
		this.filekey = filekey;
		this.timeMissed = timeMissed;
	}
	
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
	
	/**
	 * @return the filekey
	 */
	public String getFilekey() {
		return filekey;
	}
	/**
	 * @param filekey the filekey to set
	 */
	public void setFilekey(String filekey) {
		this.filekey = filekey;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RForm [rFormId=" + rFormId + ", empid=" + empid + ", rFormDate=" + rFormDate + ", deadlineDate="
				+ deadlineDate + ", place=" + place + ", info=" + info + ", EventId=" + EventId + ", propReim="
				+ propReim + ", justification=" + justification + ", filekey=" + filekey + ", timeMissed=" + timeMissed
				+ ", formClosed=" + formClosed + ", appLvl=" + appLvl + "]";
	}
	
	
	
}
