package com.revature.trms.bean;

public class EventType {
  Integer id;
  String name;
  Integer rate;

  public EventType(Integer id, String name, Integer rate) {
    this.id = id;
    this.name = name;
    this.rate = rate;
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

  public Integer getRate() {
    return rate;
  }

  public void setRate(Integer rate) {
    this.rate = rate;
  }

  @Override
  public String toString() {
    return "EventType{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", rate=" + rate +
        '}';
  }
}
