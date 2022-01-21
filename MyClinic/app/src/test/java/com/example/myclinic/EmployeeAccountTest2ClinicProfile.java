package com.example.myclinic;
import org.junit.Test;
import static org.junit.Assert.*;


public class EmployeeAccountTest2ClinicProfile {
    //Deliverable 3 Employee Account Test for setting profile
    @Test
    public void setClinicName(){
        EmployeeAccount employeeAccount= new EmployeeAccount("s","s","s");
        employeeAccount.setClinicName("this clinic");
        String output = "this clinic";
        assertEquals(employeeAccount.getClinicName(), (output));
    }
    public void setClinicPhoneNumber(){
        EmployeeAccount employeeAccount= new EmployeeAccount("s","s","s");
        employeeAccount.setPhoneNumber("000-000-000");
        String output = "000-000-000";
        assertEquals(employeeAccount.getPhoneNumber(), (output));
    }

}