package p0.service;

import p0.LoanerAccount;
import p0.dao.LoanerDaoImpl;

public class LoanerService {
	LoanerDaoImpl ldi = new LoanerDaoImpl();
	
	public boolean updateLoaner(LoanerAccount loaner) {
		if(ldi.updateLoaner(loaner) >0) {
			return true;
		}
		return false;
	}
}
