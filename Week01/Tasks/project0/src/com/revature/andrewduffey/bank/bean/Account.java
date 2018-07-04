package com.revature.andrewduffey.bank.bean;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 8117479689929343439L;
    private int balance;

    public Account() {
        balance = 0;
    }

    /**
     * Returns the balance of the account.
     * @return
     */
    public Integer getBalance() {
        return balance;
    }

    /**
     * Sets the balance to a given value
     * @param balance
     */
    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    /**
     * Returns true if the deposit can be added to the account without overflowing the account balance
     * @param amount
     * @return
     */
    public boolean deposit(Integer amount) {
        int val = (balance + amount);
        if (val < balance) {
            System.out.println("Cannot deposit more than " + (Integer.MAX_VALUE - balance) );
            return false;
        }
        balance = val;
        return true;
    }

    /**
     * Returns true if the withdrawal can be subtracted from the account without overdrawing  the account balance
     * @param amount
     * @return
     */
    public boolean withdrawal(Integer amount) {
        if (amount < 0 || balance < amount) {
            System.out.println("Cannot withdraw more than you have");

            return false;
        }
        balance -= amount;
        return true;
    }
}
