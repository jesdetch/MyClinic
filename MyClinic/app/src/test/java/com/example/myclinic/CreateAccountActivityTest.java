package com.example.myclinic;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import org.junit.Test;

import static org.junit.Assert.*;

public class CreateAccountActivityTest {
    //testing the validity of fields for account creation
    @Test
    public void isValidUsername() {
        CreateAccountActivity createAccountActivity = new CreateAccountActivity();
        String input = "Alex";
        boolean output;
        //boolean expcted = false;

        output = createAccountActivity.isValidUsername(input);
        assertFalse(output);

    }

    @Test
    public void isPassword() {
        CreateAccountActivity createAccountActivity = new CreateAccountActivity();
        String input = "Alex";
        boolean output;
        //boolean expcted = false;

        output = createAccountActivity.isValidPassword(input);
        assertFalse(output);

    }
}