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
	
	
}
