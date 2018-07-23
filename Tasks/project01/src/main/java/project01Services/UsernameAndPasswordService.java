package project01Services;

import project01Dao.UsernameAndPasswordDaoImpl;

import org.apache.log4j.Logger;

import project01Dao.RoleAssignmentDaoImpl;

public class UsernameAndPasswordService {
	final static Logger logger = Logger.getLogger(UsernameAndPasswordService.class);
	
	public boolean createUsernameAndPassword(String username, String password) {
		logger.info("");
		UsernameAndPasswordDaoImpl dao = new UsernameAndPasswordDaoImpl();
		logger.info("Creating an account");
		dao.createAccount(username, password);
		return dao.checkExistence(username, password);
	}
	
	public boolean loginToAccount(String username, String password) {
		logger.info("");
		UsernameAndPasswordDaoImpl dao = new UsernameAndPasswordDaoImpl();
		if(dao.checkExistence(username, password)) {
			logger.info("Checking out logging");
			return dao.checkPassword(username, password);
		}
		else {return false;}
		
	}
	
	public int getRole(String username) {
		logger.info("");
		RoleAssignmentDaoImpl dao = new RoleAssignmentDaoImpl();
		return dao.getRole(username);
	}
}
