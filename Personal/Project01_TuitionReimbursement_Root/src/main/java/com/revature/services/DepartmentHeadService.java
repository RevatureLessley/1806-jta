package com.revature.services;

import com.revature.beans.DepartmentHead;
import com.revature.daos.DepartmentHeadDaoImpl;

public class DepartmentHeadService {
	public boolean insertDepartmentHead(DepartmentHead head) {
		DepartmentHeadDaoImpl headd = new DepartmentHeadDaoImpl();
		return headd.insertDepartmentHeadViaSp(head);
	}
}
