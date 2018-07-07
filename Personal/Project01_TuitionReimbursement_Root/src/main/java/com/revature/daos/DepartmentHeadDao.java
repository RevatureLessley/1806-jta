package com.revature.daos;

import java.util.List;

import com.revature.beans.DepartmentHead;

public interface DepartmentHeadDao {
	public DepartmentHead selectDepartmentHeadById(Integer id);
	public List<DepartmentHead> selectAllDepartmentHead();
	public Integer deleteDepartmentHeadById(Integer id);
	public Integer updateDepartmentHead(DepartmentHead head);
	public Boolean insertDepartmentHeadViaSp(DepartmentHead head);
	public DepartmentHead selectDepartmentHeadByName(String name);
}
