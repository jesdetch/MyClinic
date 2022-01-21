package com.example.myclinic;
import org.junit.Test;
import static org.junit.Assert.*;


public class Deliverable4Test6 {
    //Deliverable 3 Employee Account Test
    @Test
    public void workingHourAdditionTest(){
        EmployeeAccount employeeAccount= new EmployeeAccount("s","s","s");
        WorkingHours w = new WorkingHours("hi", "hi", 1,1,1,1);
        employeeAccount.addWorkingHours(w);
        String output = "hi";
        assertEquals(employeeAccount.workingHours.get(0).day, (output));
    }

}