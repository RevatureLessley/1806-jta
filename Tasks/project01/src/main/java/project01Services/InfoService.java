package project01Services;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import beans.Request;
import project01Dao.RequestDaoImpl;


public class InfoService {
	public String getAllRequests(){
		RequestDaoImpl req = new RequestDaoImpl();
		List<Request> requests = req.getListOfRequests();
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(requests);
		}catch(Exception e){
			e.printStackTrace();
		}


		return json;
	}
	
	public String getAllRequestsDS(){
		RequestDaoImpl req = new RequestDaoImpl();
		List<Request> requests = req.getListOfRequestsDS();
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(requests);
		}catch(Exception e){
			e.printStackTrace();
		}


		return json;
	}
	
	public String getAllRequestsBC(){
		RequestDaoImpl req = new RequestDaoImpl();
		List<Request> requests = req.getListOfRequestsBC();
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(requests);
		}catch(Exception e){
			e.printStackTrace();
		}


		return json;
	}
	

	public String getAllRequestsUser(String uname){
		RequestDaoImpl req = new RequestDaoImpl();
		List<Request> requests = req.getListOfRequestsClient(uname);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(requests);
		}catch(Exception e){
			e.printStackTrace();
		}

		return json;
	}
	
	public void directSuperApproval(String id) {
		RequestDaoImpl reqDao = new RequestDaoImpl();
		reqDao.directSupervisorApproval(id);
	}

	public void departmentHeadApproval(String id) {
		RequestDaoImpl reqDao = new RequestDaoImpl();
		reqDao.departmentHeadApproval(id);
	}
	
	public void bCApproval(String id) {
		RequestDaoImpl reqDao = new RequestDaoImpl();
		reqDao.bCApproval(id);
	}
	
	//Upload additional files
	public void additionalFiles(String id, String fileName) {
		RequestDaoImpl reqDao = new RequestDaoImpl();
		reqDao.createAdditionalFiles(id, fileName);
	}
	
	/**
	 * Get the name of all the files associated with the request Id given
	 * and return a JSON to the servlet*/
	public String getAllFileNames(String id) {
		System.out.println("SERVICE HIT");
		System.out.println(id);
		RequestDaoImpl req = new RequestDaoImpl();
		List<String> filenames = req.getListOfFileNames(id);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(filenames);
		}catch(Exception e){
			e.printStackTrace();
		}

		return json;
	}
}
