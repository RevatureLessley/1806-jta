package services;

import org.codehaus.jackson.map.ObjectMapper;

import beans.employee;
import beans.headSupervisor;
import dao.EmployeeDaoImpl;
import dao.PersonDaoImpl;

public class HeadSupervisorService {
	public headSupervisor buildHeadSupervisorById(Integer ID) {
		PersonDaoImpl PDI = new PersonDaoImpl();
		EmployeeDaoImpl EDI = new EmployeeDaoImpl();
		headSupervisor result = new headSupervisor(PDI.getPersonByUserId(ID));
		for(employee e: EDI.getEmployeesByHeadSupervisor(ID)) {
			result.addEmployee(e);
		}
		return result;
	}
	
	public String getHeadSupervisorJSON(Integer ID){
		headSupervisor hSup = buildHeadSupervisorById(ID);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(hSup);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
}