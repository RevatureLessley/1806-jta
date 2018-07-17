package beans;

import java.sql.Date;

public class Request {
	private String fname;
	private String lname;
	private String uname;
	private String descript;
	private String event;
	private String justify;
	private Float cost;
	private int reqId;
	private Date date;
	
	public Request(String fname, String lname, String uname, String descript, String event, String justify, Float cost, int reqId, Date date) {
		this.fname = fname;
		this.lname = lname;
		this.uname = uname;
		this.descript = descript;
		this.event = event;
		this.justify = justify;
		this.cost = cost;
		this.reqId = reqId;
		this.date = date;
	}
	
	public void printAll() {
		System.out.println(this.fname);
		System.out.println(this.lname);
		System.out.println(this.uname);
		System.out.println(this.descript);
		System.out.println(this.event);
		System.out.println(this.justify);
		System.out.println(this.cost);
		System.out.println(this.reqId);
		System.out.println(this.date);
		System.out.println("=========WHAT IS IN THE CLASS ^^^^=======");
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getJustify() {
		return justify;
	}

	public void setJustify(String justify) {
		this.justify = justify;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public int getReqId() {
		return reqId;
	}

	public void setReqId(int reqId) {
		this.reqId = reqId;
	}
	
	
}
