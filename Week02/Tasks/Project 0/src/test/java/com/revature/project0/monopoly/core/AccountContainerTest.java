package com.revature.project0.monopoly.core;

import com.revature.project0.monopoly.core.Account;
import com.revature.project0.monopoly.core.AccountContainer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountContainerTest {

    private static AccountContainer test;

    @Before
    public void setUp() {
        test = new AccountContainer();
    }

    @Test
    public void addAccountTest() {
        assertEquals("", test.toString());
        String user = "Happy";
        String pass = "Sad";
        test.addAccount(new Account(user, pass));
        assertEquals("username: " + user + " | password: "+ pass +"\n", test.toString());
    }

    @Test
    public void findAccountTest() {
        String s1 = "Anything";
        String s2 = "Anything Else";
        String s3 = "TestName";
        String s4 = "TestPassword";
        assertNull(test.findAccount(s1, s2));
        test.addAccount(new Account(s3, s4));
        assertNull(test.findAccount(s1, s2));
        assertNotNull(s3, s4);
    }

    @Test
    public void toStringTest() {
        assertEquals("", test.toString());
        String s1 = "String1";
        String s2 = "String2";
        test.addAccount(new Account(s1, s2));
        assertEquals("username: "+s1+" | password: "+s2+"\n", test.toString());
        test.addAccount(new Account(s2, s1));
        assertEquals("username: "+s1+" | password: "+s2+"\n" +
                "username: "+s2+" | password: "+s1+"\n", test.toString());
    }
}