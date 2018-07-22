package com.revature.dao;

import com.revature.beans.Employee;
import com.revature.beans.RForm;

public interface RFormDao {
	public Boolean insertEmployeeViaSp(Employee employee);
	public Boolean approveRForm(int applvl, int rformid);
}
