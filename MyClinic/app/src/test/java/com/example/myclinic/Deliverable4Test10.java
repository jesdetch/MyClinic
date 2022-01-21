package com.example.myclinic;

import org.junit.Test;

import static org.junit.Assert.*;

public class Deliverable4Test10 {

    //Appointment of Patient class
    @Test
    public void getAppointment() {
        Appointment a = new Appointment(1, 1, 1,2,3);
        Appointment b = new Appointment(5,5,5,5,5);
        PatientAccount patientAccount = new PatientAccount("11","Samet", "12345");
        patientAccount.appointment = a;
        patientAccount.setAppointment(b);
        int output = patientAccount.getAppointment().getDay();
        assertNotEquals(output, 1);
    }

}