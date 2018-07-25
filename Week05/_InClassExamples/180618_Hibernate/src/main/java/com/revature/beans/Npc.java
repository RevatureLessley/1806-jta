package com.revature.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@NamedQueries({
			@NamedQuery(name="getAllNpcs", query="FROM Npc"),
			@NamedQuery(name="getNpc", query="FROM Npc WHERE id = :grapes")
		})
@NamedNativeQueries({
	@NamedNativeQuery(
			/*
			 * Within a NativeQuery you are writing the ACTUAL
			 * SQL that your engine is using. NOT HQL.
			 */
				name="originalSql",
				query="SELECT * FROM Npc WHERE npc_lvl > :bananas",
				resultClass=Npc.class
			)
})

		


@Entity
@Table
@Cache(usage=CacheConcurrencyStrategy.READ_ONLY, region="npc")
public class Npc {
	
	@Id
	@Column(name="npc_id" )
	@SequenceGenerator(sequenceName="NPC_SEQ", name="NPC_SEQ")
	@GeneratedValue(generator="NPC_SEQ", strategy=GenerationType.SEQUENCE)
	private Integer id;
	@Column(name="npc_name", unique=true)
	private String name;
	@Column(name="npc_lvl")
	private Integer lvl;
	@Column(name="currency")
	private Integer currency;
	/*
	 * @OneToOne available as well
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="job_id")
	private Job job;
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
	public Integer getLvl() {
		return lvl;
	}
	public void setLvl(Integer lvl) {
		this.lvl = lvl;
	}
	public Integer getCurrency() {
		return currency;
	}
	public void setCurrency(Integer currency) {
		this.currency = currency;
	}
	public Job getJob() {
		return job;
	}
	public void setJob(Job job) {
		this.job = job;
	}
	public Npc(Integer id, String name, Integer lvl, Integer currency, Job job) {
		super();
		this.id = id;
		this.name = name;
		this.lvl = lvl;
		this.currency = currency;
		this.job = job;
	}
	public Npc(String name, Integer lvl, Integer currency, Job job) {
		super();
		this.name = name;
		this.lvl = lvl;
		this.currency = currency;
		this.job = job;
	}
	public Npc() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Npc [id=" + id + ", name=" + name + ", lvl=" + lvl + ", currency=" + currency + ", job=" + job + "]";
	}
	
	
}
