package com.example.myclinic;

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

public class ModifyClinicServicesActivity extends AppCompatActivity {

    private Button done;
    private Button setCost;
    private EditText cost;
    private String accountID;
    private String serviceID;
    private EmployeeAccount thisAccount;
    private Service thisService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_clinic_services);

        getSupportActionBar().setTitle("MyClinic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        done = (Button) findViewById(R.id.doneButton4);
        setCost = (Button) findViewById(R.id.setCostButton);
        cost = (EditText) findViewById(R.id.costText);

        accountID = getIntent().getStringExtra("account");
        serviceID = getIntent().getStringExtra("service");

        setCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setServiceCost(serviceID);
            }
        });
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openClinicServices();
            }
        });

        for(int i = 0; i < MainActivity.employeeAccounts.size(); i++) {
            if (MainActivity.employeeAccounts.get(i).getId().equals(accountID)) {
                thisAccount = MainActivity.employeeAccounts.get(i);
            }
        }
        for(int i = 0; i< MainActivity.services.size();i++){
            if(MainActivity.services.get(i).getId().equals(serviceID)){
                thisService = MainActivity.services.get(i);
            }
        }




    }
    public void setServiceCost(String serviceID){
        double newCost = Double.parseDouble(cost.getText().toString());
        thisService.setCost(newCost);
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference("employeeAccounts").child(accountID).child("services");
        dr.child(thisService.getId()).setValue(thisService);
    }
    public void openClinicServices(){
        Intent intent = new Intent(this, ListClinicServicesActivity.class);
        intent.putExtra("account", accountID );
        startActivity(intent);

    }


}
