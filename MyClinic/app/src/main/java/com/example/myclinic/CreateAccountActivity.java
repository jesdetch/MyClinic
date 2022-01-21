package com.example.myclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import java.util.regex.Pattern;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CreateAccountActivity extends AppCompatActivity {

    protected String username;
    private String password;
    private Button createAccountBtn;
    private Spinner dropDownSpinner;
    protected EditText usernametextbox;
    private EditText passwordtextbox;
    private EmployeeAccount currentEmployee;
    private PatientAccount currentPatient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        createAccountBtn = (Button) findViewById(R.id.createAccountButton);
        dropDownSpinner = (Spinner) findViewById(R.id.accountTypeSpinner);
        usernametextbox = (EditText)findViewById(R.id.usernameCreate);
        passwordtextbox = (EditText) findViewById(R.id.passwordCreate);


        List<String> list = new ArrayList<>(); //methods to extract data from spinner?
        list.add("Employee");
        list.add("Patient");

        /*
        * implement the dropDownSpinner
        * */
        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDownSpinner.setAdapter(adapter);


         /*
         * checks the validitaty of the entered values by the user
         *
         * */
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                username = usernametextbox.getText().toString();
                password = passwordtextbox.getText().toString();

                if(!isValidUsername(username)) {
                    usernametextbox.setError("Username must be a minimum of 5 characters, and can only"
                            + " contain letters, and numbers");
                    usernametextbox.requestFocus();
                    usernametextbox.getError();
                }
                else if(!isValidPassword(password)) {
                    passwordtextbox.setError("That is not a valid password, a" +
                            " password must be a minimum of 5 characters, and can only" +
                            " contain letters, and numbers");
                    passwordtextbox.requestFocus();
                    passwordtextbox.getError();
                }else{
                    addAccount();
                    if (dropDownSpinner.getSelectedItem().toString().equals("Employee")) {
                        openEmployeeMainPage();
                    }else{
                        openWelcomePageActivity();
                    }
                }

                //}

            }
        });

    }
    public boolean isValidPassword(String password) { //methods to check validity of fields
        Pattern passwordPattern = Pattern.compile("[a-zA-Z0-9]");
        return passwordPattern.matcher(password).find() && password.length() >= 5;
    }

    public boolean isValidUsername(String username) { //methods to check validity of fields
        Pattern usernamePattern = Pattern.compile("[a-zA-Z0-9]");
        return usernamePattern.matcher(username).find() && username.length() >= 5;

    }



    public void openWelcomePageActivity(){

        Intent intent = new Intent(this, WelcomePageActivity.class);
        intent.putExtra("message", "Welcome " + username + ", you have created a " + dropDownSpinner.getSelectedItem().toString() +" account.");
        intent.putExtra("account", currentPatient.getId());

        startActivity(intent);

    }
    public void openEmployeeMainPage(){

        Intent intent = new Intent(this, EmployeeMainPageActivity.class);
        intent.putExtra("account", currentEmployee.getId());
        startActivity(intent);

    }

    public void addAccount(){ // method to add and store accounts in the database
        String username = usernametextbox.getText().toString().trim();
        String password = passwordtextbox.getText().toString().trim();
        String type = dropDownSpinner.getSelectedItem().toString();
        String id = MainActivity.accountDatabase.push().getKey();
        //String idEmployee = MainActivity.employeeAccountDatabase.push().getKey();
        if(type == "Employee"){
            EmployeeAccount account = new EmployeeAccount(id, username, password);
            MainActivity.accountDatabase.child(id).setValue(account);
            MainActivity.employeeAccountDatabase.child(id).setValue(account);
            currentEmployee = account;

        }
        else{
            PatientAccount account = new PatientAccount(id, username, password);
            MainActivity.accountDatabase.child(id).setValue(account);
            MainActivity.patientAccountDataBase.child(id).setValue(account);
            currentPatient = account;
        }
        passwordtextbox.setText("");
        usernametextbox.setText("");
        Toast.makeText(this, "Account Created", Toast.LENGTH_LONG).show();


    }
}
