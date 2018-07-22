package services;

import org.codehaus.jackson.map.ObjectMapper;

import beans.employee;
import beans.head;
import dao.EmployeeDaoImpl;
import dao.PersonDaoImpl;

public class HeadService {
	public head buildHeadById(Integer ID) {
		PersonDaoImpl PDI = new PersonDaoImpl();
		EmployeeDaoImpl EDI = new EmployeeDaoImpl();
		head result = new head(PDI.getPersonByUserId(ID));
		for(employee e: EDI.getEmployeesByHead(ID)) {
			result.addEmployee(e);
		}
		return result;
	}
	
	public String getHeadJSON(Integer ID){
		head hed = buildHeadById(ID);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(hed);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
}