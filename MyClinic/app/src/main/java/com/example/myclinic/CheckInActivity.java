package com.example.myclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheckInActivity extends AppCompatActivity {



    private Button rate;
    private String accountID;
    private String employee;
    private TextView message;
    private EmployeeAccount employeeAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in);

        rate = (Button) findViewById(R.id.rateClinicBUtton);
        accountID = getIntent().getStringExtra("account");
        employee = getIntent().getStringExtra("employee");
        message = findViewById(R.id.waitingTimeTextview);
        for (int i = 0; i < MainActivity.employeeAccounts.size(); i++){
            if(MainActivity.employeeAccounts.get(i).getId().equals(employee)){
                employeeAccount = MainActivity.employeeAccounts.get(i);
            }
        }

        message.setText(employeeAccount.getWaitTime().toString() + "Minutes");
        rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRateClinicActivty();
            }
        });


    }

    public void openRateClinicActivty(){
        Intent intent = new Intent(this, RateClinicActivity.class);
        intent.putExtra("account", accountID);
        startActivity(intent);
    }
}
