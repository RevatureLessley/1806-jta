package project01Services;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.apache.log4j.*;

import beans.Request;
import project01Dao.RequestDaoImpl;


public class InfoService {
	final static Logger logger = Logger.getLogger(InfoService.class);
	public String getAllRequests(){
		logger.info("Entering getAllRequests");
		RequestDaoImpl req = new RequestDaoImpl();
		List<Request> requests = req.getListOfRequests();
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			logger.info("Trying to turn the List into a string");
			json = mapper.writeValueAsString(requests);
		}catch(Exception e){
			logger.error("Something went wrong in attempting to turn the list into a string");
			e.printStackTrace();
		}


		return json;
	}
	
	public String getAllRequestsDS(){
		logger.info("Entering getAllRequestsDS");
		RequestDaoImpl req = new RequestDaoImpl();
		List<Request> requests = req.getListOfRequestsDS();
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			logger.info("Attempting to Stringify the list in getAllRequestsDS");
			json = mapper.writeValueAsString(requests);
		}catch(Exception e){
			logger.error("Something went wrong with trying to turn the list into a string");
			e.printStackTrace();
		}


		return json;
	}
	
	public String getAllRequestsBC(){
		logger.info("Entering getAllRequestBC");
		RequestDaoImpl req = new RequestDaoImpl();
		List<Request> requests = req.getListOfRequestsBC();
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			logger.info("Turning the list to a string");
			json = mapper.writeValueAsString(requests);
		}catch(Exception e){
			logger.error("Something went wrong in trying to turn the list to a string");
			e.printStackTrace();
		}


		return json;
	}
	
	

	public String getAllRequestsUser(String uname){
		logger.info("Entered method: getAllRequestUser");
		RequestDaoImpl req = new RequestDaoImpl();
		List<Request> requests = req.getListOfRequestsClient(uname);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			logger.info("Attempting to turn the return of getListOfRequest into a String");
			json = mapper.writeValueAsString(requests);
		}catch(Exception e){
			logger.error("Encountered an error in the method getAllRequestUser had troubple turning the return of getListRequestsClient to a string.");
			e.printStackTrace();
		}

		return json;
	}
	
	public String directSuperApproval(String id) {
		logger.info("");
		RequestDaoImpl reqDao = new RequestDaoImpl();
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		logger.info("Entering switch");
		switch (reqDao.getEvent(id)) {
		
		case "University Course":
			logger.info("University Course Case");
			if(reqDao.getFund(id) > (.8 * reqDao.getCost(id))) {
				reqDao.directSupervisorApproval(id);
				try{json = mapper.writeValueAsString(true);}
				catch(Exception e){e.printStackTrace();}
				return json;
			}
			else {
				try{json = mapper.writeValueAsString(false);}
				catch(Exception e){e.printStackTrace();}
				return json;
			}
						
		case "Seminar":
			logger.info("Seminar Case");
			if(reqDao.getFund(id) > (.6 * reqDao.getCost(id))) {
				reqDao.directSupervisorApproval(id);
				try{json = mapper.writeValueAsString(true);}
				catch(Exception e){e.printStackTrace();}
				return json;
			}
			else {
				try{json = mapper.writeValueAsString(false);}
				catch(Exception e){e.printStackTrace();}
				return json;
			}
			
		case "CPC":
			logger.info("CPC Case");
			if(reqDao.getFund(id) > (.75 * reqDao.getCost(id))) {
				reqDao.directSupervisorApproval(id);
				try{json = mapper.writeValueAsString(true);}
				catch(Exception e){e.printStackTrace();}
				return json;
			}
			else {
				try{json = mapper.writeValueAsString(false);}
				catch(Exception e){e.printStackTrace();}
				return json;
			}
			
		case "Certification":
			logger.info("Certification Case");
			if(reqDao.getFund(id) >  reqDao.getCost(id)) {
				reqDao.directSupervisorApproval(id);
				try{json = mapper.writeValueAsString(true);}
				catch(Exception e){e.printStackTrace();}
				return json;
			}
			else {
				try{json = mapper.writeValueAsString(false);}
				catch(Exception e){e.printStackTrace();}
				return json;
			}
			
		case "tech":
			logger.info("Tech Case");
			if(reqDao.getFund(id) > (.9 * reqDao.getCost(id))) {
				reqDao.directSupervisorApproval(id);
				try{json = mapper.writeValueAsString(true);}
				catch(Exception e){e.printStackTrace();}
				return json;
			}
			else {
				try{json = mapper.writeValueAsString(false);}
				catch(Exception e){e.printStackTrace();}
				return json;
			}
			
		case "other":
			logger.info("Other Case");
			if(reqDao.getFund(id) > (.3 * reqDao.getCost(id))) {
				reqDao.directSupervisorApproval(id);
				try{json = mapper.writeValueAsString(true);}
				catch(Exception e){e.printStackTrace();}
				return json;
			}
			else {
				try{json = mapper.writeValueAsString(false);}
				catch(Exception e){e.printStackTrace();}
				return json;
			}
		
		}

		return null;
	}

	public void departmentHeadApproval(String id) {
		logger.info("");
		RequestDaoImpl reqDao = new RequestDaoImpl();
		reqDao.departmentHeadApproval(id);
	}
	
	public void bCApproval(String id) {
		logger.info("");
		RequestDaoImpl reqDao = new RequestDaoImpl();
		
		switch(reqDao.getEvent(id)) {
		
		case "University Course":
			logger.info("University Case");
			reqDao.updateFunds(reqDao.getUsername(id), (float)(reqDao.getFund(id)- (.8*reqDao.getCost(id))));
			reqDao.bCApproval(id);
			break;
		case "Seminar":
			logger.info("Seminar Case");
			reqDao.updateFunds(reqDao.getUsername(id), (float)(reqDao.getFund(id)- (.6*reqDao.getCost(id))));
			reqDao.bCApproval(id);
			break;
			
		case "CPC":
			logger.info("CPC Case");
			reqDao.updateFunds(reqDao.getUsername(id), (float)(reqDao.getFund(id)- (.75*reqDao.getCost(id))));
			reqDao.bCApproval(id);
			break;
			
		case "Certification":
			logger.info("Certification Case");
			reqDao.updateFunds(reqDao.getUsername(id), (float)(reqDao.getFund(id)- reqDao.getCost(id)));
			reqDao.bCApproval(id);
			break;

			
		case "tech":
			logger.info("Tech Case");
			reqDao.updateFunds(reqDao.getUsername(id), (float)(reqDao.getFund(id)- (.9*reqDao.getCost(id))));
			reqDao.bCApproval(id);
			break;

			
		case "other":
			logger.info("Other Case");
			reqDao.updateFunds(reqDao.getUsername(id), (float)(reqDao.getFund(id)- (.3*reqDao.getCost(id))));
			reqDao.bCApproval(id);
			break;
		
		}
		

	}
	
	//Upload additional files
	public void additionalFiles(String id, String fileName) {
		logger.info("");
		RequestDaoImpl reqDao = new RequestDaoImpl();
		reqDao.createAdditionalFiles(id, fileName);
	}
	
	/**
	 * Get the name of all the files associated with the request Id given
	 * and return a JSON to the servlet*/
	public String getAllFileNames(String id) {
		logger.info("");
		RequestDaoImpl req = new RequestDaoImpl();
		List<String> filenames = req.getListOfFileNames(id);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			logger.info("Turn into a string the list of filenames");
			json = mapper.writeValueAsString(filenames);
		}catch(Exception e){
			e.printStackTrace();
		}

		return json;
	}
}
