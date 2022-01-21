package com.example.myclinic;
import android.os.Bundle;

import org.junit.Test;

import static org.junit.Assert.*;

public class Deliverable4Test9 {
    @Test
    public void addressTestBlank(){
        boolean output;
        //testing blank for address field
        WelcomePageActivity welcomePageActivity = new WelcomePageActivity();
        output = welcomePageActivity.isValidAddress("");
        assertTrue(output);

    }
}