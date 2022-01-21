package com.example.myclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class WelcomePageActivity extends AppCompatActivity {

    String message;
    TextView welcomeMsg;
    TextView addressText;
    TextView patientTitle;
    TextView hoursText;
    TextView serviceText;
    EditText addressInput;
    Spinner beginHourSpinner;
    Spinner endHourSpinner;
    Spinner serviceSpinner;
    Button seeClinic;
    ArrayList<String> list;
    ArrayList<String> list2;
    List<String> serviceID;
    List<String> serviceName;

    private PatientAccount currentPatient;

    private String accountID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);

        accountID = getIntent().getStringExtra("account");

        message = getIntent().getExtras().getString("message");
        welcomeMsg = (TextView) findViewById(R.id.welcomeMessage);
        seeClinic = (Button) findViewById(R.id.seeClinicsButton);
        serviceSpinner = (Spinner) findViewById(R.id.serviceSpinner);
        beginHourSpinner = (Spinner) findViewById(R.id.startHourSpinner);
        endHourSpinner = (Spinner) findViewById(R.id.endHourSpinner);
        addressInput = (EditText) findViewById(R.id.AddressInput);
        welcomeMsg.setText(message);
        list = new ArrayList<String>();
        list2 = new ArrayList<String>();
        serviceID = new ArrayList<>();
        serviceName = new ArrayList<>();
        serviceName.add("None");
        serviceID.add("None");
        for (int i = 0; i < MainActivity.services.size(); i++) {
            serviceName.add(MainActivity.services.get(i).getName());
            serviceID.add(MainActivity.services.get(i).getId());
        }

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, serviceName);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceSpinner.setAdapter(adapter);

        seeClinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidAddress(addressInput.getText().toString())) {
                    addressInput.setError("Address format must only contain numbers and letters");
                    addressInput.requestFocus();
                    addressInput.getError();
                }
                else{
                    openPatientSeeClinic();
                }
            }
        });

        Bundle extra = new Bundle();
        extra.putSerializable("accountName", list);


    }

    public void openPatientSeeClinic() {

        Intent intent = new Intent(this, PatientSeeClinicActivity.class);
        intent.putExtra("account", accountID);
        addAddressAccounts(addressInput.getText().toString());
        //addServiceAccounts();
        intent.putStringArrayListExtra("accounts", list);
        intent.putStringArrayListExtra("Ids", list2);
        startActivity(intent);
    }

    public void addAddressAccounts(String address) {
        list2.clear();
        list.clear();
        addServiceAccounts();
        for (int i = 1; i < MainActivity.employeeAccounts.size(); i++) {
            EmployeeAccount temp = MainActivity.employeeAccounts.get(i);
            if (temp.getAddress().contains(address)) {
                list.add("Clinic Name: " + temp.getClinicName());
                list2.add(temp.getId());
            }
        }
    }

    public void addServiceAccounts() {
        String selected = serviceSpinner.getSelectedItem().toString();
        String id = serviceID.get(serviceName.indexOf(selected));
        //Toast.makeText(this, id, Toast.LENGTH_LONG).show();
        DatabaseReference accounts;
        accounts = FirebaseDatabase.getInstance().getReference("services").child(id).child("accounts");

        accounts.addListenerForSingleValueEvent(new ValueEventListener() {// these methods implement what happens
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                list2.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()) {
                    EmployeeAccount e = postsnapshot.getValue(EmployeeAccount.class);
                    list.add("Clinic Name: " + e.getClinicName());
                    list2.add(e.getId());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });


    }

    public static boolean isValidAddress(String address) {//methods to check validity of fields
        if(address.equals("")){return true;}
        Pattern passwordPattern = Pattern.compile("[a-zA-Z0-9]");
        return passwordPattern.matcher(address).find();
    }

    public static boolean noneSelected(String string){
        return string.equals("None");
    }
    public String getID(int index){
        return this.serviceID.get(index);
    }




}
