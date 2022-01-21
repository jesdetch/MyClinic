package com.example.myclinic;
import org.junit.Test;

import static org.junit.Assert.*;

public class Deliverable4Test4 {
    @Test
    public void workingHourTest(){
        String string = "ss";
       //workinghour test
        WorkingHours w = new WorkingHours(string, string, 1, 1, 1, 1);

        w.setDay("sss");
        assertNotEquals(string, w.getDay());

    }
}