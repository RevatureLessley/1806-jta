package com.revature.project0.monopoly;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {

    private static Account test;
    @BeforeClass
    public static void beforeClass(){
        test = new Account("username", "password");
    }

    @Test
    public void getPasswordTest() {
        assertEquals("password", test.getPassword());
        assertNotEquals("username", test.getPassword());
        assertNotEquals("", test.getPassword());
    }

    @Test
    public void getUsernameTest() {
        assertEquals("username", test.getUsername());
        assertNotEquals("password", test.getUsername());
        assertNotEquals("", test.getUsername());
    }

    @Test
    public void toStringTest() {
        assertEquals("username: " + test.getUsername() + " | password: "+ test.getPassword(), test.toString());
    }
}