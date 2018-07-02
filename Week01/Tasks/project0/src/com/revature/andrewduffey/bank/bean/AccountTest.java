package com.revature.andrewduffey.bank.bean;

import org.junit.After;
import org.junit.Before;
import org.junit.Assert;
import org.junit.Test;

public class AccountTest {
    private Account account;

    @Before
    public void setUp() throws Exception {
        account = new Account();
        account.deposit(50);
    }

    @After
    public void tearDown() throws Exception {
        account = null;
    }

    @Test
    public void whenDepositAmountShouldNotOverflowBalanceShouldReturnTrue() {
        boolean result = account.deposit(50);
        Assert.assertTrue(result);
    }

    @Test
    public void whenDepositAmountShouldOverflowBalanceShouldReturnFalse() {
        boolean result = account.deposit(Integer.MAX_VALUE);
        Assert.assertFalse(result);
    }

    @Test
    public void whenWithdrawalAmountIsEqualThanBalanceShouldReturnTrue() {
        boolean result = account.withdrawal(50);
        Assert.assertTrue(result);
    }

    @Test
    public void whenWithdrawalAmountIsLessThanBalanceShouldReturnTrue() {
        boolean result = account.withdrawal(25);
        Assert.assertTrue(result);
    }

    @Test
    public void whenWithdrawalAmountIsGreaterThanBalanceShouldReturnFalse() {
        boolean result = account.withdrawal(100);
        Assert.assertFalse(result);
    }
}