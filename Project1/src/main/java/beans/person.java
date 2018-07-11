package beans;

public class person {
	String name;
	int secLvl;
	int personId;
	int superiorId;
	
	public person() {
		super();
	}
	public person(String name, int secLvl, int personId, int superiorId) {
		super();
		this.name = name;
		this.secLvl = secLvl;
		this.personId = personId;
		this.superiorId = superiorId;
	}

	public String getName() {
		return name;
	}
	public int getSecLvl() {
		return secLvl;
	}
	public int getPersonId() {
		return personId;
	}
	public int getSuperiorId() {
		return superiorId;
	}
}
