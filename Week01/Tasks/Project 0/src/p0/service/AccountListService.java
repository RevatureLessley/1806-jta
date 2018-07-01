package p0.service;

import p0.dao.AccountListDaoImpl;

public class AccountListService {

	public Integer getAccSize() {
		AccountListDaoImpl ald = new AccountListDaoImpl();
		return ald.getAccSize();
	}
}
