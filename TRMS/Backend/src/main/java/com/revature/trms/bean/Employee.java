package com.revature.trms.bean;

import java.io.IOException;
import org.codehaus.jackson.map.ObjectMapper;

public class Employee {
  private Integer id;
  private String firstName;
  private String lastName;
  private String email;
  private Integer title;
  private Integer department;
  private Integer supervisor;

  public Employee(Integer id, String firstName, String lastName, String email, Integer title,
      Integer department, Integer supervisor) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.title = title;
    this.department = department;
    this.supervisor = supervisor;
  }

  public Integer getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public Integer getTitle() {
    return title;
  }

  public Integer getDepartment() {
    return department;
  }

  public Integer getSupervisor() {
    return supervisor;
  }

  @Override
  public String toString() {
    ObjectMapper mapper = new ObjectMapper();
    String json = null;
    try {
      json = mapper.writeValueAsString(this);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return json;
  }
}
