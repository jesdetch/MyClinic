package com.example.myclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookAppointmentActivity extends AppCompatActivity {


    private String accountID;
    private String employeeID;
    private Button done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        accountID = getIntent().getStringExtra("account");
        employeeID = getIntent().getStringExtra("employee");

        done = (Button) findViewById(R.id.doneButton);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWelcomePageActivity();
            }
        });

    }

    public void openWelcomePageActivity(){

        Intent intent = new Intent(this, WelcomePageActivity.class);
        intent.putExtra("account", accountID );
        startActivity(intent);

    }
}
