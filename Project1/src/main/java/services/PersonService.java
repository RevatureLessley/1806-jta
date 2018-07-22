package services;

import beans.person;
import dao.PersonDaoImpl;

public class PersonService {
	static PersonDaoImpl PDI = new PersonDaoImpl();
	
	
	public static Integer userLogin(String username, String password){
		return PDI.checkPassword(username, password);
	}
	
	public person buildPersonByID(Integer id) {
		return PDI.getPersonByUserId(id);
	}
}
