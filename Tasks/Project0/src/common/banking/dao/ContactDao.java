package common.banking.dao;

import common.banking.application.customer;

public interface ContactDao {
public String getPhoneNumber(customer account);
public String getAddress(customer account);
public void updatePhone(String newPhone);
public void updateAddress(String newAddress);
public void createContact(customer account);
}
