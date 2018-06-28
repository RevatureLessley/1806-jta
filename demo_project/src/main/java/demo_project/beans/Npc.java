package demo_project.beans;

public class Npc {
	private Integer id;
	private String Name;
	private Integer lvl;
	private Integer currency;
	private Integer jobClass;
	private String jobClassString;
	
	public String getJobClassString() {
		return jobClassString;
	}

	public void setJobClassString(String jobClassString) {
		this.jobClassString = jobClassString;
	}

	public Npc(Integer id, String name, Integer lvl, Integer currency, Integer jobClass, String jobClassString) {
		super();
		this.id = id;
		Name = name;
		this.lvl = lvl;
		this.currency = currency;
		this.jobClass = jobClass;
		this.jobClassString = jobClassString;
	}
	public Npc(Integer id, String name, Integer lvl, Integer currency, Integer jobClass) {
		super();
		this.id = id;
		Name = name;
		this.lvl = lvl;
		this.currency = currency;
		this.jobClass = jobClass;
	}

	public Npc() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
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

	public Integer getJobClass() {
		return jobClass;
	}

	public void setJobClass(Integer jobClass) {
		this.jobClass = jobClass;
	}

	@Override
	public String toString() {
		return "npc [id=" + id + ", Name=" + Name + ", lvl=" + lvl + ", currency=" + currency + ", jobClass="
				+ jobClass + "]";
	}
	
}
