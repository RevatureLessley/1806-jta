package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Job_Class")
public class Job {
	
	@Id 
	@Column(name="job_id") 
	@SequenceGenerator(sequenceName="JOB_SEQ", name="Job_Seq")
	@GeneratedValue(generator="Job_Seq", strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="job_name")
	private String name;
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
	@Override
	public String toString() {
		return "Job [id=" + id + ", name=" + name + "]";
	}
	public Job(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public Job() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Job(String name) {
		super();
		this.name = name;
	}
	
}
