package beans;

import java.util.ArrayList;
import java.util.Date;

public class request {
	private int id;
	private double fullAmmount;
	private double cooperateAmmount;
	private int status;
	private Date approvealDate;
	private Date creationDate;
	private Date eventDate;
	private String gradingFormat;
	private String eventDescription;
	private String eventJustification;
	private String eventLocation;
	private double typeValue;
	private String typeName;
	private ArrayList<file> files;
	private ArrayList<info> infos;
	
	public request(int id, double fullAmmount, double cooperateAmmount, int status, Date approvealDate,
			Date creationDate, Date eventDate, String gradingFormat, String eventDescription,
			String eventJustification, String eventLocation, double typeValue, String typeName) {
		this.id = id;
		this.fullAmmount = fullAmmount;
		this.cooperateAmmount = cooperateAmmount;
		this.status = status;
		this.approvealDate = approvealDate;
		this.creationDate = creationDate;
		this.eventDate = eventDate;
		this.gradingFormat = gradingFormat;
		this.eventDescription = eventDescription;
		this.eventJustification = eventJustification;
		this.eventLocation = eventLocation;
		this.typeValue = typeValue;
		this.typeName = typeName;
		this.files = new ArrayList<file>();
		this.infos = new ArrayList<info>();
	}
	public void addFile(file f) {
		files.add(f);
	}
	public ArrayList<file> getFiles() {
		return files;
	}
	public void addInfo(info i) {
		infos.add(i);
	}
	public ArrayList<info> getInfos() {
		return infos;
	}
	public double getTypeValue() {
		return typeValue;
	}
	public void setTypeValue(double typeValue) {
		this.typeValue = typeValue;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getFullAmmount() {
		return fullAmmount;
	}
	public void setFullAmmount(double fullAmmount) {
		this.fullAmmount = fullAmmount;
	}
	public double getCooperateAmmount() {
		return cooperateAmmount;
	}
	public void setCooperateAmmount(double cooperateAmmount) {
		this.cooperateAmmount = cooperateAmmount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getApprovealDate() {
		return approvealDate;
	}
	public void setApprovealDate(Date approvealDate) {
		this.approvealDate = approvealDate;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getEventDate() {
		return eventDate;
	}
	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}
	public String getGradingFormat() {
		return gradingFormat;
	}
	public void setGradingFormat(String gradingFormat) {
		this.gradingFormat = gradingFormat;
	}
	public String getEventDescription() {
		return eventDescription;
	}
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}
	public String getEventJustification() {
		return eventJustification;
	}
	public void setEventJustification(String eventJustification) {
		this.eventJustification = eventJustification;
	}
	public String getEventLocation() {
		return eventLocation;
	}
	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}
	
}
