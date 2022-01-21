package com.example.myclinic;
import org.junit.Test;

import static org.junit.Assert.*;

public class Deliverable4Test2 {
    @Test
    public void getIntent(){

        //testing the waittime incrementation

        Double input = 15.0;
        EmployeeAccount e = new EmployeeAccount("Hi", "hello", "world");
        e.incrementWaitTime();
        assertEquals(input, e.getWaitTime());

    }
}