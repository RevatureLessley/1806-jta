package dao;

import java.util.ArrayList;

import beans.request;

public interface RequestDao {
	public ArrayList<request> getRequestsByPlayerId(Integer id);
}
