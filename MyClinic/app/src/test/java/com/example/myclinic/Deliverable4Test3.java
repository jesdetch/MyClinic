package com.example.myclinic;
import org.junit.Test;

import static org.junit.Assert.*;

public class Deliverable4Test3 {

    @Test
    public void addressTest(){
        boolean output;
        //testing invalid characters for address field
        WelcomePageActivity welcomePageActivity = new WelcomePageActivity();
        output = welcomePageActivity.isValidAddress("_____");
        assertFalse(output);

    }

}