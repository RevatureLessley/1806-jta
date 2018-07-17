package project01Services;

import project01Dao.UsernameAndPasswordDaoImpl;
import project01Dao.RoleAssignmentDaoImpl;

public class UsernameAndPasswordService {
	
	public boolean createUsernameAndPassword(String username, String password) {
		UsernameAndPasswordDaoImpl dao = new UsernameAndPasswordDaoImpl();
		dao.createAccount(username, password);
		return dao.checkExistence(username, password);
	}
	
	public boolean loginToAccount(String username, String password) {
		UsernameAndPasswordDaoImpl dao = new UsernameAndPasswordDaoImpl();
		if(dao.checkExistence(username, password)) {
			return dao.checkPassword(username, password);
		}
		else {return false;}
		
	}
	
	public int getRole(String username) {
		RoleAssignmentDaoImpl dao = new RoleAssignmentDaoImpl();
		return dao.getRole(username);
	}
}
