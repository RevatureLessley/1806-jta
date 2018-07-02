package common.banking.dao;

import common.banking.application.customer;

public interface PersonalinfoDao {
public void createPersonalinfo(customer account);
public String getFirstname(customer account);
public String getLastname(customer account);

}
