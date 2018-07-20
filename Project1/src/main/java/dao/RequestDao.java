package dao;

import java.util.ArrayList;

import beans.request;

public interface RequestDao {
	public ArrayList<request> getRequestsByEmployeeId(Integer id);
	public void updateRequest(request r);
	public void createNewRequest(request r, Integer empID);
	public void createNewFile(String info, Integer reqID);
	public void createNewInfo(String info, Integer reqID);
}
