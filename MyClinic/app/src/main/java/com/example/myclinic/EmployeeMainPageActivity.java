package com.example.myclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EmployeeMainPageActivity extends AppCompatActivity {

    private Button seeProfile;
    private Button seeServices;


    private Button seeWorkingHours;
    private String accountID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main_page);

        accountID = getIntent().getStringExtra("account");



        seeWorkingHours = (Button) findViewById(R.id.seeClinicHours);
        seeProfile = (Button) findViewById(R.id.modifyProfilebutton);
        seeServices = (Button) findViewById(R.id.SeeServicesButton);

        seeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfileView();
            }
        });

        seeServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSeeServices();
            }
        });


        seeWorkingHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSeeWorkingHours();
            }
        });
        accountID = (String) getIntent().getSerializableExtra("account");


    }



    public void openProfileView(){

        Intent intent = new Intent(this, AdditionalInfoActivity.class);
        intent.putExtra("account", accountID );
        startActivity(intent);


    }

    public void openSeeWorkingHours(){
        Intent intent = new Intent(this, seeWorkingHoursActivity.class);
        intent.putExtra("account", accountID );
        startActivity(intent);

    }
    public void openSeeServices(){
        Intent intent = new Intent(this, ListClinicServicesActivity.class);
        intent.putExtra("account", accountID );
        startActivity(intent);
    }



}
