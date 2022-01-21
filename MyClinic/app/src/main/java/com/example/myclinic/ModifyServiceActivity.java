package com.example.myclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class ModifyServiceActivity extends AppCompatActivity {

    private Button update;
    private EditText name;
    private EditText role;
    private String n;
    private String r;
    private  String oldId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_service);

        getSupportActionBar().setTitle("MyClinic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        update = (Button) findViewById(R.id.updateService);
        name = (EditText) findViewById(R.id.nameTextView);
        role = (EditText) findViewById(R.id.RoleTextView) ;

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                n = name.getText().toString();
                r = role.getText().toString();
                oldId = getIntent().getExtras().getString("id");

                if(n.matches("")) {
                    name.setError("To modify a service you must enter a name");
                    name.requestFocus();
                    name.getError();
                }
                else if(r.matches("")) {
                    role.setError("To modify a service you must enter a role");
                    role.requestFocus();
                    role.getError();
                }else{
                    updateService(oldId,n, r);
                    finish();
                    startActivity(getIntent());

                }

            }
        });

    }

    //methode that update service from database when called   NOTE: I have not called it in the code yet database
    public void updateService(String id, String name, String role){

        //get specific product reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
        //update account
        Service service = new Service(id , name , role);
        dR.setValue(service);
        Toast.makeText(getApplicationContext(), "Service Updated ", Toast.LENGTH_SHORT).show();

    }
}
