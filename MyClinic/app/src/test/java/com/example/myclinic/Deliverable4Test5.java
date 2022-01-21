package com.example.myclinic;
import org.junit.Test;

import static org.junit.Assert.*;

public class Deliverable4Test5 {
    @Test
    public void noneSelection(){

        //testing none selection for spinners
        WelcomePageActivity w = new WelcomePageActivity();
        boolean output = w.noneSelected("None");
        assertTrue(output);
    }
}