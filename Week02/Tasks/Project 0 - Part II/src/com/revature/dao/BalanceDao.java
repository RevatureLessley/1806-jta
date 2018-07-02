package com.revature.dao;

import java.math.BigDecimal;

import com.revature.beans.Balance;

public interface BalanceDao {
	public void insertBalance(Balance balance);
	public Balance selectBalanceByUserId(Integer id);
	public BigDecimal selectBalanceViaFnc(Integer id);
	public void updateBalanceAmountById(Integer id, BigDecimal amount);
	public void insertIntoBalanceViaSp(Balance balance);
	public void commit();
}
