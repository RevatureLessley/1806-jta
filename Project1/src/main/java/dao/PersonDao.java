package dao;

import beans.person;

public interface PersonDao {

	public person getPersonByUserId(Integer id);
	public Integer checkPassword(String uName, String pWord);
}
