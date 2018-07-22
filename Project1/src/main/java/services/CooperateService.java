package services;

import org.codehaus.jackson.map.ObjectMapper;

import beans.cooperate;
import beans.employee;
import dao.EmployeeDaoImpl;
import dao.PersonDaoImpl;

public class CooperateService {
	public cooperate buildCooperateById(Integer ID) {
		PersonDaoImpl PDI = new PersonDaoImpl();
		EmployeeDaoImpl EDI = new EmployeeDaoImpl();
		cooperate result = new cooperate(PDI.getPersonByUserId(ID));
		for(employee e: EDI.getEmployeesBySupervisor(ID)) {
			result.addEmployee(e);
		}
		return result;
	}
	
	public String getCooperateJSON(Integer ID){
		cooperate coop = buildCooperateById(ID);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(coop);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
}