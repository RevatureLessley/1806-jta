package services;

import beans.request;
import dao.RequestDaoImpl;

public class RequestService {
	
	public void updateRequest(request r) {
		RequestDaoImpl RDI = new RequestDaoImpl();
		RDI.updateRequest(r);
	}
}
