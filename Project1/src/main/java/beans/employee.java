package beans;

import java.util.ArrayList;

public class employee {
	private int empId;
	private double maxReturn;
	private ArrayList<request> requests;
	private person  pers;
	
	public employee(int empId, double maxReturn, person p) {
		this.empId = empId;
		this.maxReturn = maxReturn;
		this.requests = new ArrayList<request>();
		this.pers = p;
	}
	
	public void addRequest(request r) {
		requests.add(r);
	}
	public ArrayList<request> getRequests() {
		return requests;
	}
	public void clearRequests() {
		requests = new ArrayList<request>();
	}
	public int getEmpId() {
		return empId;
	}
	public void setEmpId(int empId) {
		this.empId = empId;
	}
	public double getMaxReturn() {
		return maxReturn;
	}
	public void setMaxReturn(double maxReturn) {
		this.maxReturn = maxReturn;
	}
}
