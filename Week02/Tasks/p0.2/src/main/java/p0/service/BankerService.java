package p0.service;

import p0.BankerAccount;
import p0.dao.BankerDaoImpl;

public class BankerService {
	BankerDaoImpl bdi = new BankerDaoImpl();
	
	public boolean updateBanker(BankerAccount banker) {
		if(bdi.updateBanker(banker) >0) {
			return true;
		}
		return false;
	}
}
