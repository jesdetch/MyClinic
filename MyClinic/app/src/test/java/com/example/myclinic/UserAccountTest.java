package com.example.myclinic;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserAccountTest {
    //testing the generalized class, and the inheritance of variables
    UserAccount account = new PatientAccount("11", "Samet", "12345");
    @Test
    public void getUsername() {
        String test = "11";
        assertNotEquals(test, account.username);
    }

    @Test
    public void getPassword() {
        String test = "12345";
        assertEquals(test, account.password);
    }

    @Test
    public void getId() {
        String test = "11";
        assertEquals(test, account.getId());
    }
}