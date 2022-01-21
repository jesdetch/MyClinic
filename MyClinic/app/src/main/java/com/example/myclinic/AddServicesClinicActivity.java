package com.example.myclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AddServicesClinicActivity extends AppCompatActivity {

    private String accountID;
    private Button add;
    private Button done;
    private ListView listService;
    List<String> list = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
    ArrayAdapter adapter;
    String pos;
    boolean itemSelected = false;
    private EmployeeAccount thisaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_services_clinic);

        listService = (ListView) findViewById(R.id.clinicServices);
        accountID = getIntent().getStringExtra("account");


        for(int i = 0; i < MainActivity.employeeAccounts.size(); i++){
            //Toast.makeText(this, MainActivity.employeeAccounts.get(i).getId(), Toast.LENGTH_LONG).show();
            if(MainActivity.employeeAccounts.get(i).getId().equals(accountID)){
                thisaccount = MainActivity.employeeAccounts.get(i);
            }
        }


        for (int i = 1; i < MainActivity.services.size(); i++){
            Service temp = MainActivity.services.get(i);
            list2.add(temp.id);
            list.add("Service " + temp.getName() + "\nAdministered by: "+ temp.getRole());
        }
        adapter = new ArrayAdapter(AddServicesClinicActivity.this, android.R.layout.simple_list_item_1, list);
        listService.setAdapter(adapter);

        listService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(AddServicesClinicActivity.this, "Press Add Button to add the Service \n " + list.get(position), Toast.LENGTH_SHORT).show();
                pos = list2.get(position);
                itemSelected = true;

            }
        });


        getSupportActionBar().setTitle("MyClinic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        add = findViewById(R.id.addServiceButton);
        done = findViewById(R.id.doneButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemSelected == true){
                    for(int i = 0; i < MainActivity.services.size(); i++){
                        if(MainActivity.services.get(i).getId().equals(pos)){
                            Service temp = MainActivity.services.get(i);

                            for(int j = 0; j < MainActivity.employeeAccounts.size(); j++){
                                if(MainActivity.employeeAccounts.get(j).getId().equals(accountID)){
                                    EmployeeAccount account = MainActivity.employeeAccounts.get(j);
                                    String e = MainActivity.employeeAccounts.get(j).getId();
                                    MainActivity.employeeAccountDatabase.child(e).child("services").child(temp.getId()).setValue(temp);
                                    MainActivity.servicedatabase.child(temp.getId()).child("accounts").child(account.getId()).setValue(account);
                                }
                            }
                        }
                    }
                    ListClinicServicesActivity.relaodNeeded = true;
                }
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    openClinicServices();
            }
        });
    }
    public void openClinicServices(){
        Intent intent = new Intent(this, ListClinicServicesActivity.class);
        intent.putExtra("account", accountID );
        startActivity(intent);

    }

    public void addService(){

    }

}
