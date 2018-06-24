package com.revature.andrewduffey.bank;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 8117479689929343439L;
    private double balance;

    public Account() {
        balance = 0.0;
    }

    /**
     * Returns the balance of the account.
     * @return
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Returns true if the deposit can be added to the account without overflowing the account balance
     * @param amount
     * @return
     */
    public boolean deposit(double amount) {
        double val = (balance + amount);
        if (val >= Double.MAX_VALUE) return false;
        balance = val;
        return true;
    }

    /**
     * Returns true if the withdrawal can be subtracted from the account without overdrawing  the account balance
     * @param amount
     * @return
     */
    public boolean withdrawal(double amount) {
        if (balance < amount) {
            return false;
        }
        balance -= amount;
        return true;
    }
}
