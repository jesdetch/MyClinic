package com.example.myclinic;
import org.junit.Test;
import static org.junit.Assert.*;


public class EmployeeAccountTest2ServiceList {
    //Deliverable 3 Employee Account Test
    @Test
    public void addService(){
        EmployeeAccount employeeAccount= new EmployeeAccount("s","s","s");
        Service s = new Service("id", "name", "role");
        employeeAccount.addService(s);
        String output = "name";
        assertEquals(employeeAccount.services.get(0).getName(), (output));
    }

}
