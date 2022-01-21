package com.example.myclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RateClinicActivity extends AppCompatActivity {

    private Button submit;
    private String accountID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_clinic);

        accountID = getIntent().getStringExtra("account");
        submit = (Button) findViewById(R.id.rateClinicBUtton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRateClinicActivty();
            }
        });
    }

    public void openRateClinicActivty(){
        Intent intent = new Intent(this, WelcomePageActivity.class);
        intent.putExtra("account", accountID);
        intent.putExtra("message", "Welcome");
        startActivity(intent);
    }
}
