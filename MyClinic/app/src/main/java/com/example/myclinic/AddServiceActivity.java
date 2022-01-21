package com.example.myclinic;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddServiceActivity extends AppCompatActivity {

    private Button add;
    private EditText name;
    private EditText role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service);

        getSupportActionBar().setTitle("MyClinic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        add = (Button) findViewById(R.id.addServiceButton);
        name = (EditText) findViewById(R.id.addNameTextView);
        role = (EditText) findViewById(R.id.addRoleTextView);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               addService();
            }
        });

    }

    public void addService(){ // method to add and store accounts in the database

        String name1 = name.getText().toString().trim();
        String role1 = role.getText().toString().trim();

        if(name1.matches("")) {
            name.setError("To create a service you must enter a name");
            name.requestFocus();
            name.getError();
        }
        else if(role1.matches("")) {
            role.setError("To create a service you must enter a role");
            role.requestFocus();
            role.getError();
        }else{
            String id = MainActivity.servicedatabase.push().getKey();
            Service newService = new Service(id, name1, role1);
            MainActivity.servicedatabase.child(id).setValue(newService);

            name.setText("");
            role.setText("");
            Toast.makeText(this, "New Service Added", Toast.LENGTH_LONG).show();



        }


    }


}
