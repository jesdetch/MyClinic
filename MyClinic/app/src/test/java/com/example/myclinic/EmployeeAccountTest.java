package com.example.myclinic;

import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeAccountTest {

//testing getters for EmployeeAccount Class
    @Test
    public void getUsername() {
        String input = "Samet";
        EmployeeAccount employeeAccount = new EmployeeAccount("11","Samet", "12345");
        assertEquals(input, employeeAccount.getUsername());
    }

    @Test
    public void getPassword() {
        String input = "Samet";
        EmployeeAccount employeeAccount = new EmployeeAccount("11","Samet", "12345");
        assertNotEquals(input, employeeAccount.getPassword());
    }

    @Test
    public void getId() {
        String input = "Samet";
        EmployeeAccount employeeAccount = new EmployeeAccount("11","Samet", "12345");
        assertNotEquals(input, employeeAccount.getId());
    }
}