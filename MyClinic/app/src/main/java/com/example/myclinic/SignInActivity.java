package com.example.myclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.text.BoringLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.*;

import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class SignInActivity extends AppCompatActivity {

    private Button signInButton;
    private String username;
    private String password;
    private TextView invalid;
    private EditText usernametextbox;
    private EditText passwordtextbox;
    private String currentID;

    private EmployeeAccount currentEmployee;
    private PatientAccount currentPatient;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        getSupportActionBar().setTitle("Sign In");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        usernametextbox = (EditText)findViewById(R.id.username);
        passwordtextbox = (EditText) findViewById(R.id.password);
        invalid = (TextView) findViewById(R.id.invalid);
        signInButton = (Button) findViewById(R.id.btn_signIn2);

        /**
         * action that happens on OnClick
         */
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                username = usernametextbox.getText().toString();
                password = passwordtextbox.getText().toString();
                Boolean validcredentials = false;
                Boolean isEmployeeAccount = false;

                //checks immediatly if the admin account is signing in
                if (username.equals("admin") && password.equals("5T5ptQ")) {
                    openAdminWelcome();
                } else {
                    //check the list of accounts stored by admin to see if the entered values are for a validAccount

                    for (int i = 0; i < MainActivity.employeeAccounts.size();i++){
                        EmployeeAccount temp2 = MainActivity.employeeAccounts.get(i);
                        if(temp2.username.equals(username) && temp2.password.equals(password)){

                            validcredentials = true;

                            currentEmployee = temp2;
                            isEmployeeAccount = true;

                            break;

                        }
                    }

                    for (int i = 0; i < MainActivity.patientAccounts.size(); i++) {
                        PatientAccount temp = MainActivity.patientAccounts.get(i);

                        if (temp.username.equals(username) && temp.password.equals(password)) {

                            currentPatient = temp;

                            validcredentials = true;

                            break;
                        }
                    }
                    //check the list of accounts stored by admin to see if the entered values are for a validAccount

                    //opens the Welcomepage if valid account
                    if (validcredentials) {
                        if (isEmployeeAccount == true){
                            openEmployeeMainPage();
                        }else {
                            openWelcomePageActivity();
                        }
                    } else {
                        invalid.setText("Invalid combination of Username and Password");
                    }
                 }
            }
        });

    }

    /**
     * function that opens the WelcomePageActivity while supplying a welcome message
     *
     * **/
    public void openWelcomePageActivity(){
        Intent intent = new Intent(this, WelcomePageActivity.class);
        intent.putExtra("message", "Welcome back " + username);
        intent.putExtra("account", currentPatient.getId() );
        startActivity(intent);
    }

    /**
     * function that opens the adminWelcomePage while supplying a welcome message
     *
     * **/

    public void openAdminWelcome(){
        Intent intent = new Intent(this, AdminWelcome.class);
        startActivity(intent);
    }

    public void openEmployeeMainPage(){

        Intent intent = new Intent(this, EmployeeMainPageActivity.class);

        intent.putExtra("account", currentEmployee.getId());

        startActivity(intent);
    }
}
