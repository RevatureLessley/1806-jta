package beans;

import java.util.ArrayList;

public class supervisor {
	private person pers;
	private ArrayList<employee> empList;
	
	public supervisor(person p) {
		this.pers = p;
		empList = new ArrayList<employee>();
	}

	public ArrayList<employee> getEmpList(){
		return empList;
	}
	public void addEmployee(employee e) {
		empList.add(e);
	}
	public person getPers() {
		return pers;
	}
	
}
