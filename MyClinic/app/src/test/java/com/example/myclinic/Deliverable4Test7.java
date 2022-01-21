package com.example.myclinic;

import org.junit.Test;

import static org.junit.Assert.*;

public class Deliverable4Test7 {

    //Testing Getters for Patient Account class
    @Test
    public void getUsername() {
        String input = "Samet";
        PatientAccount patientAccount = new PatientAccount("11","Samet", "12345");
        assertEquals(input, patientAccount.getUsername());
    }

    @Test
    public void getPassword() {
        String input = "Samet";
        PatientAccount patientAccount = new PatientAccount("11","Samet", "12345");
        assertNotEquals(input, patientAccount.getPassword());
    }

    @Test
    public void getId() {
        String input = "Samet";
        PatientAccount patientAccount = new PatientAccount("11","Samet", "12345");
        assertNotEquals(input, patientAccount.getId());
    }
}