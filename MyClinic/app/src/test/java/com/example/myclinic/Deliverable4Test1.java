package com.example.myclinic;
import org.junit.Test;

import static org.junit.Assert.*;

public class Deliverable4Test1 {
    @Test
    public void getIntent(){

        //testing getters and setters of appointment class
        int input = 5;
        Appointment a = new Appointment(3, 12,3,12,30);
        a.setDay(5);
        assertEquals(input, a.getDay());

    }
}
