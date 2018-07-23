package com.revature.beans;
import java.sql.Date;
public class RForm {
	private int rFormId;
	private int empid;
	private Date rFormDate;
	private String place;
	private String info;
	private int propReim;
	private String justification;
	private String filekey;
	private int timeMissed;
	private int formClosed;
	private int appLvl;
	private int gradeFormat;
	private int cutoffGrade;
	private int eventTypeId;
	private String eventTypeName;
	private double eventCost;
	private int supid;
	private String eventName;
	private String empName;
	private int isSup;
	private int depid;
	private int finalperc;
	
	
	public RForm(int rFormId, int empid, Date rFormDate, String place, String info, int propReim, String justification,
			String filekey, int timeMissed, int formClosed, int appLvl, int gradeFormat, int cutoffGrade,
			int eventTypeId, double eventCost, String eventName,int supid,int depid,int moreinfo,int finalperc,
			int grade) {
		super();
		this.rFormId = rFormId;
		this.empid = empid;
		this.rFormDate = rFormDate;
		this.place = place;
		this.info = info;
		this.propReim = propReim;
		this.justification = justification;
		this.filekey = filekey;
		this.timeMissed = timeMissed;
		this.formClosed = formClosed;
		this.appLvl = appLvl;
		this.gradeFormat = gradeFormat;
		this.cutoffGrade = cutoffGrade;
		this.eventTypeId = eventTypeId;
		this.eventCost = eventCost;
		this.eventName = eventName;
		this.supid = supid;
		this.depid = depid;
		this.finalperc = finalperc;
	}
	public RForm(int rFormId, int empid, Date rFormDate, String place, String info,
			int propReim, String justification, String filekey, int timeMissed, int formClosed, int appLvl) {
		super();
		this.rFormId = rFormId;
		this.empid = empid;
		this.rFormDate = rFormDate;
		this.place = place;
		this.info = info;
		this.propReim = propReim;
		this.justification = justification;
		this.filekey = filekey;
		this.timeMissed = timeMissed;
		this.formClosed = formClosed;
		this.appLvl = appLvl;
	}
	public RForm(int empid, Date rFormDate, String place, String info,
			int propReim, String justification, int timeMissed, int gradeFormat,
			int cutoffGrade, int eventTypeId, double eventCost, int supid,
			String eventName,int depid, int finalperc) {
		super();
		this.empid = empid;
		this.rFormDate = rFormDate;
		this.place = place;
		this.info = info;
		this.propReim = propReim;
		this.justification = justification;
		this.timeMissed = timeMissed;
		this.gradeFormat = gradeFormat;
		this.cutoffGrade = cutoffGrade;
		this.eventTypeId = eventTypeId;
		this.eventCost = eventCost;
		this.supid = supid;
		this.eventName = eventName;
		this.depid = depid;
		this.finalperc = finalperc;
	}
	
	/**
	 * @return the finalperc
	 */
	public int getFinalperc() {
		return finalperc;
	}
	/**
	 * @param finalperc the finalperc to set
	 */
	public void setFinalperc(int finalperc) {
		this.finalperc = finalperc;
	}
	/**
	 * @return the depid
	 */
	public int getDepid() {
		return depid;
	}
	/**
	 * @param depid the depid to set
	 */
	public void setDepid(int depid) {
		this.depid = depid;
	}
	/**
	 * @return the isSup
	 */
	public int getIsSup() {
		return isSup;
	}
	/**
	 * @param isSup the isSup to set
	 */
	public void setIsSup(int isSup) {
		this.isSup = isSup;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public RForm(int rFormId, String eventName) {
		super();
		this.rFormId = rFormId;
		this.eventName = eventName;
	}
	
	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}
	/**
	 * @param eventName the eventName to set
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	/**
	 * @return the supid
	 */
	public int getSupid() {
		return supid;
	}
	/**
	 * @param supid the supid to set
	 */
	public void setSupid(int supid) {
		this.supid = supid;
	}
	/**
	 * @return the gradeFormat
	 */
	public int getGradeFormat() {
		return gradeFormat;
	}
	/**
	 * @param gradeFormat the gradeFormat to set
	 */
	public void setGradeFormat(int gradeFormat) {
		this.gradeFormat = gradeFormat;
	}
	/**
	 * @return the cutoffGrade
	 */
	public int getCutoffGrade() {
		return cutoffGrade;
	}
	/**
	 * @param cutoffGrade the cutoffGrade to set
	 */
	public void setCutoffGrade(int cutoffGrade) {
		this.cutoffGrade = cutoffGrade;
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
	 * @return the eventTypeName
	 */
	public String getEventTypeName() {
		return eventTypeName;
	}
	/**
	 * @param eventTypeName the eventTypeName to set
	 */
	public void setEventTypeName(String eventTypeName) {
		this.eventTypeName = eventTypeName;
	}
	/**
	 * @return the eventCost
	 */
	public double getEventCost() {
		return eventCost;
	}
	/**
	 * @param eventCost the eventCost to set
	 */
	public void setEventCost(double eventCost) {
		this.eventCost = eventCost;
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
	 * @return the propReim
	 */
	public int getPropReim() {
		return propReim;
	}
	/**
	 * @param propReim the propReim to set
	 */
	public void setPropReim(int propReim) {
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
		return "RForm [rFormId=" + rFormId + ", empid=" + empid + ", rFormDate=" + rFormDate + ", place=" + place + ", info=" + info +  ", propReim="
				+ propReim + ", justification=" + justification + ", filekey=" + filekey + ", timeMissed=" + timeMissed
				+ ", formClosed=" + formClosed + ", appLvl=" + appLvl + "]";
	}
	
	
	
}
