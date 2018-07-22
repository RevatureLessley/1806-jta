package services;

import org.codehaus.jackson.map.ObjectMapper;

import beans.employee;
import dao.EmployeeDaoImpl;

public class EmployeeService {
	public employee buildEmployeeById(Integer ID) {
		EmployeeDaoImpl EDI = new EmployeeDaoImpl();
		employee result = EDI.getEmployeeByID(ID);
		
		return result;
	}
	
	public String getEmployeeJSON(Integer ID){
		employee emp = buildEmployeeById(ID);
		ObjectMapper mapper = new ObjectMapper();
		String json = "";
		
		try{
			json = mapper.writeValueAsString(emp);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return json;
	}
}
