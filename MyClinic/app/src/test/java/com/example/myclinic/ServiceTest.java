package com.example.myclinic;

import org.junit.Test;

import static org.junit.Assert.*;

public class ServiceTest {

    //testing the setter and getter methods of service
    Service service = new Service("11", "service", "nurse");

    @Test
    public void getName() {
        String testname = "service";
        assertEquals(testname, service.getName());
    }

    @Test
    public void getRole() {
        String testrole = "service";
        assertNotEquals(testrole, service.getRole());
    }

    @Test
    public void setCost() {
        double cost = 4.5;
        service.setCost(4.5);
        assertEquals(service.getCost(), cost, 0.05);
    }
}