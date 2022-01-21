package com.example.myclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accounts.Account;
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

public class ListClinicServicesActivity extends AppCompatActivity {

    private Button addService;
    private Button deleteService;
    private Button modifyCost;
    private String accountID;
    private ListView thisview;
    private ArrayAdapter adapter;
    private ArrayList list;
    private ArrayList list2;
    boolean itemsSlected = false;
    private String pos;
    private EmployeeAccount thisaccount;
    public static Boolean relaodNeeded = true;
    public static DatabaseReference serviceList;
    private Button back22;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_clinic_services);


        getSupportActionBar().setTitle("MyClinic");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addService = (Button) findViewById(R.id.addServiceButton);
        deleteService = (Button) findViewById(R.id.deleteServiceButton);
        modifyCost = (Button) findViewById(R.id.ModifyCostButton);
        thisview = (ListView) findViewById(R.id.serviceListClinic);
        back22 = (Button) findViewById(R.id.back2);
        list = new ArrayList();
        list2 = new ArrayList();


        addService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddServices();
            }
        });

        accountID = getIntent().getStringExtra("account");
        for(int i = 0; i < MainActivity.employeeAccounts.size(); i++) {
            if (MainActivity.employeeAccounts.get(i).getId().equals(accountID)) {
                thisaccount = MainActivity.employeeAccounts.get(i);
            }
        }

        serviceList = FirebaseDatabase.getInstance().getReference("employeeAccounts").child(accountID).child("services");

        serviceList.addListenerForSingleValueEvent(new ValueEventListener() {// these methods implement what happens
            @Override                                                   //when there is a change in data
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    thisaccount.services.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    Service service = postsnapshot.getValue(Service.class);
                    thisaccount.services.add(service);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        if (relaodNeeded){
            refresh();
            relaodNeeded = false;
        }

        //Toast.makeText(this, accountID, Toast.LENGTH_LONG).show();

        for(int i = 0; i < MainActivity.employeeAccounts.size(); i++){
            if(MainActivity.employeeAccounts.get(i).getId().equals(accountID)){
                for(int j = 0; j < thisaccount.services.size(); j++){
                    list.add("Service: " + thisaccount.services.get(j).getName() + " Role: " + thisaccount.services.get(j).getRole() + "\nCost: " + thisaccount.services.get(j).getCost());
                    list2.add(thisaccount.services.get(j).getId());
                }
            }
        }

        adapter = new ArrayAdapter(ListClinicServicesActivity.this, android.R.layout.simple_list_item_1, list);
        thisview.setAdapter(adapter);

        thisview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                pos = list2.get(position).toString();
                itemsSlected = true;

            }
        });

        deleteService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemsSlected == true) {
                    //deletes account from database
                    deleteService(pos);
                    //removes account from listView
                    finish();
                    startActivity(getIntent());
                }
            }
        });

        modifyCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openModifyCost(pos);
            }
        });

        back22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmployeeMainPage2();
            }
        });
    }

    public void openAddServices(){
        Intent intent = new Intent(this, AddServicesClinicActivity.class);
        intent.putExtra("account", accountID );
        startActivity(intent);
    }

    public void deleteService(String id){
        //get specific service reference
        MainActivity.employeeAccountDatabase.child(thisaccount.getId()).child("services").child(id).removeValue();
        MainActivity.servicedatabase.child(id).child("accounts").child(thisaccount.getId()).removeValue();
        relaodNeeded = true;

    }
    public void openModifyCost(String id){
        //get specific service reference
        Intent intent = new Intent(this, ModifyClinicServicesActivity.class);
        intent.putExtra("account", accountID);
        intent.putExtra("service", id);
        relaodNeeded = true;
        startActivity(intent);

    }



    public void onResume(){
        super.onResume();
        if(relaodNeeded){
            finish();
            startActivity(getIntent());
            relaodNeeded = false;
        }
    }

    public Service getService(String ID){
        Service s = MainActivity.services.get(0);
        for(int i = 0; i  < MainActivity.services.size(); i++){
            if(MainActivity.services.get(i).getId().equals(ID)){
                s = MainActivity.services.get(i);
            }
        }
        return s;
    }

    public void refresh(){
        finish();
        startActivity(getIntent());
        return;
    }

    public void openEmployeeMainPage2(){
        Intent intent = new Intent(this, EmployeeMainPageActivity.class);
        intent.putExtra("account", accountID );
        startActivity(intent);
    }

}
