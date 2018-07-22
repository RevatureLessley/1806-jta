package services;

import org.codehaus.jackson.map.ObjectMapper;

import beans.employee;
import beans.supervisor;
import dao.EmployeeDaoImpl;
import dao.PersonDaoImpl;

public class SupervisorService {
	public supervisor buildSupervisorById(Integer ID) {
		PersonDaoImpl PDI = new PersonDaoImpl();
		EmployeeDaoImpl EDI = new EmployeeDaoImpl();
		supervisor result = new supervisor(PDI.getPersonByUserId(ID));
		for(employee e: EDI.getEmployeesBySupervisor(ID)) {
			result.addEmployee(e);
		}
		return result;
	}
	public String getSupervisorJSON(Integer ID){
		supervisor sup = buildSupervisorById(ID);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(sup);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
}