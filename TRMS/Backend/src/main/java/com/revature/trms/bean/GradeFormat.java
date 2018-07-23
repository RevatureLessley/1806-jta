package com.revature.trms.bean;

public class GradeFormat {
  Integer id;
  String desc;

  public GradeFormat(Integer id, String desc) {
    this.id = id;
    this.desc = desc;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  @Override
  public String toString() {
    return "GradeFormat{"
        + "id=" + id
        + ", desc='" + desc + '\'' + '}';
  }
}
