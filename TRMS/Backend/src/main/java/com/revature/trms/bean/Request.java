package com.revature.trms.bean;

import java.sql.Timestamp;

public class Request {

  private Integer id;
  private Integer employee;
  private String name;
  private String location;
  private Timestamp eventTime;
  private Double eventCost;
  private Integer eventType;
  private Integer gradeFormat;
  private Timestamp requestTime;
  private String justification;
  private Double amountReimbursed;

  public Request(Integer id, Integer employee, String name, String location, Timestamp eventTime,
      Double eventCost, Integer eventType, Integer gradeFormat, Timestamp requestTime,
      String justification, Double amountReimbursed) {
    this.id = id;
    this.employee = employee;
    this.name = name;
    this.location = location;
    this.eventTime = eventTime;
    this.eventCost = eventCost;
    this.eventType = eventType;
    this.gradeFormat = gradeFormat;
    this.requestTime = requestTime;
    this.justification = justification;
    this.amountReimbursed = amountReimbursed;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getEmployee() {
    return employee;
  }

  public void setEmployee(Integer employee) {
    this.employee = employee;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Timestamp getEventTime() {
    return eventTime;
  }

  public void setEventTime(Timestamp eventTime) {
    this.eventTime = eventTime;
  }

  public Double getEventCost() {
    return eventCost;
  }

  public void setEventCost(Double eventCost) {
    this.eventCost = eventCost;
  }

  public Integer getEventType() {
    return eventType;
  }

  public void setEventType(Integer eventType) {
    this.eventType = eventType;
  }

  public Integer getGradeFormat() {
    return gradeFormat;
  }

  public void setGradeFormat(Integer gradeFormat) {
    this.gradeFormat = gradeFormat;
  }

  public Timestamp getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(Timestamp requestTime) {
    this.requestTime = requestTime;
  }

  public String getJustification() {
    return justification;
  }

  public void setJustification(String justification) {
    this.justification = justification;
  }

  public Double getAmountReimbursed() {
    return amountReimbursed;
  }

  public void setAmountReimbursed(Double amountReimbursed) {
    this.amountReimbursed = amountReimbursed;
  }
}
