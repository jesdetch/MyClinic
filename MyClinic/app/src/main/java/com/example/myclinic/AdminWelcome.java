package com.example.myclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminWelcome extends AppCompatActivity {

    private Button serviceFunc;
    private Button accountFunc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);

        serviceFunc = (Button) findViewById(R.id.serviceFunc);
        accountFunc = (Button) findViewById(R.id.accountFunc);


        serviceFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListOfService();
            }
        });

        accountFunc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openListOfAccount();
            }
        });

    }

    public void openListOfAccount(){
        Intent intent = new Intent(this, ListOfAccountActivity.class);
        startActivity(intent);
    }

    public void openListOfService(){
        Intent intent = new Intent(this, ListOfServicesActivity.class);
        startActivity(intent);
    }
}
