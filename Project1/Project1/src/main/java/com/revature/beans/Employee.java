package com.revature.beans;

public class Employee {
	private int userId;
	private String password;
	private String fName;
	private String lName;
	private String address;
	private float remainingReimbursement;
	private String empRole;
	
	///////////////////////////////////////////////
	// Constructors ///////////////////////////////
	///////////////////////////////////////////////

	
	public Employee(int userId, String password, String fName, String lName, String address,
			float remainingReimbursement, String empRole)
	{
		super();
		this.userId = userId;
		this.password = password;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.remainingReimbursement = remainingReimbursement;
		this.empRole = empRole;
	}

	public Employee()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
///////////////////////////////////////////////
// Getters and Setters ////////////////////////
///////////////////////////////////////////////
	
	public int getUserId()
	{
		return userId;
	}

	public void setUserId(int userId)
	{
		this.userId = userId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getfName()
	{
		return fName;
	}

	public void setfName(String fName)
	{
		this.fName = fName;
	}

	public String getlName()
	{
		return lName;
	}

	public void setlName(String lName)
	{
		this.lName = lName;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public float getRemainingReimbursement()
	{
		return remainingReimbursement;
	}

	public void setRemainingReimbursement(float remainingReimbursement)
	{
		this.remainingReimbursement = remainingReimbursement;
	}
	
	public String getEmpRole()
	{
		return empRole;
	}

	public void setEmpRole(String empRole)
	{
		this.empRole = empRole;
	}

	
///////////////////////////////////////////////
// toString ///////////////////////////////////
///////////////////////////////////////////////
	
	@Override
	public String toString()
	{
		return "Employee [userId=" + userId + ", password=" + password + ", fName=" + fName + ", lName=" + lName
				+ ", address=" + address + ", remainingReimbursement=" + remainingReimbursement + "]";
	}

}
