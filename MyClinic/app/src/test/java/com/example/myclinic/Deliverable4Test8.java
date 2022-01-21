package com.example.myclinic;

import org.junit.Test;

import static org.junit.Assert.*;

public class Deliverable4Test8 {

    //Appointment of Patient class
    @Test
    public void getAppointment() {
        Appointment a = new Appointment(1, 1, 1,2,3);
        PatientAccount patientAccount = new PatientAccount("11","Samet", "12345");
        patientAccount.appointment = a;
        int output = patientAccount.getAppointment().getDay();
        assertEquals(output, 1);
    }

}