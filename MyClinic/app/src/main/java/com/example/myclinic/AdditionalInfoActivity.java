package com.example.myclinic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class AdditionalInfoActivity extends AppCompatActivity {

    private String accountID;
    private EmployeeAccount thisaccount;

    private EditText clinicName;
    private EditText phoneNumber;
    private EditText address;
    private EditText insuranceType;
    private EditText paymentMethod;
    private Button   doneButton;
    private Button   back;
    private TextView clinicText;
    private TextView phonetext;
    private TextView insurancetext;
    private TextView addressText;
    private TextView paymentText;
    private int location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additional_info);


        getSupportActionBar().setTitle("My clinic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        clinicName = (EditText) findViewById(R.id.ClinicNameEditText);
        phoneNumber = (EditText) findViewById(R.id.PhoneEditText);
        address = (EditText) findViewById(R.id.AddressEditText);
        insuranceType = (EditText) findViewById(R.id.InsuranceTypeEditText);
        paymentMethod = (EditText) findViewById(R.id.PaymentMethodEditText);
        doneButton = (Button) findViewById(R.id.DoneButton);

        clinicText = (TextView)findViewById(R.id.clinicNameTextView);
        addressText = (TextView) findViewById(R.id.adressTextView);
        phonetext = (TextView) findViewById(R.id.phoneTextView);
        insurancetext = (TextView) findViewById((R.id.insuranceTextView));
        paymentText = (TextView) findViewById(R.id.paymentTextView);
        back = (Button) findViewById(R.id.back1);

        accountID = getIntent().getStringExtra("account");

        for(int i = 0; i < MainActivity.employeeAccounts.size(); i++) {
            if (MainActivity.employeeAccounts.get(i).getId().equals(accountID)) {
                thisaccount = MainActivity.employeeAccounts.get(i);
                location = i;
            }
        }

        if ( !MainActivity.employeeAccountDatabase.child(accountID).child("clinicName").equals("") ) {
            clinicText.setText("Name: " + thisaccount.clinicName);
            addressText.setText("Address: " + thisaccount.getAddress());
            phonetext.setText("Tel: " + thisaccount.phoneNumber);
            insurancetext.setText("Insurance Type: " + thisaccount.insuranceType);
            paymentText.setText("Payment Method: " + thisaccount.paymentMethod);
        }else {
            clinicText.setText("Name: " );
            addressText.setText("Address:" );
            phonetext.setText("Tel:" );
            insurancetext.setText("Insurance Type: " );
            paymentText.setText("Payment Method: ");
        }

        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(clinicName.getText().toString().matches("")) && !(address.getText().toString().matches("")) && !(phoneNumber.getText().toString().matches("")) && !(insurancetext.getText().toString().matches("")) && !(paymentText.getText().toString().matches(""))){
                    String n = clinicName.getText().toString();
                    clinicText.setText("Name: " + n);

                    String a = address.getText().toString();
                    addressText.setText("Address: " + a);


                    String p = phoneNumber.getText().toString();
                    phonetext.setText("Tel: " + p);

                    String i = insuranceType.getText().toString();
                    insurancetext.setText("Insurance: " + i);


                    String pay = paymentMethod.getText().toString();
                    paymentText.setText("Payment Method: " + pay);


                    updateProfile(thisaccount.getId(), thisaccount.getUsername(), thisaccount.getPassword(), n, p, a, i, pay );
                }else {
                    //Toast.makeText(getApplicationContext(), "Some fields are empty", Toast.LENGTH_SHORT).show();
                    AlertDialog alertDialog = new AlertDialog.Builder(AdditionalInfoActivity.this).create();
                    alertDialog.setTitle("Alert ");
                    alertDialog.setMessage("To set your profile you must complete all fields");
                    alertDialog.show();
                }

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmployeeWelcomePage();
            }
        });

    }


    public void updateProfile(String id, String username, String password, String clinicName, String phoneNumber, String address, String insuranceType, String paymentMethod){

        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("employeeAccounts").child(id);
        DatabaseReference dA =  FirebaseDatabase.getInstance().getReference("accounts").child(id);
        //update account
        EmployeeAccount employee = new EmployeeAccount(id, username,password,clinicName,phoneNumber,address,insuranceType,paymentMethod);
        dR.setValue(employee);
        dA.setValue(employee);
        Toast.makeText(getApplicationContext(), "Profile Updated", Toast.LENGTH_SHORT).show();


    }

    public void finish() {
        Intent intent = new Intent(this, EmployeeMainPageActivity.class);
        intent.putExtra("account", accountID);
        //startActivity(intent);
        super.finish();
    }
    public void openEmployeeWelcomePage(){

        Intent intent = new Intent(this, EmployeeMainPageActivity.class);
        intent.putExtra("account", accountID);
        startActivity(intent);

    }

}
