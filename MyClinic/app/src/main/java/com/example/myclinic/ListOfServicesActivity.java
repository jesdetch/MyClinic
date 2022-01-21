package com.example.myclinic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ListOfServicesActivity extends AppCompatActivity {

    private Button addServ;
    private Button deleteServ;
    private Button modifyServ;

    private ListView listService;
    List list = new ArrayList();
    List<String> list2 = new ArrayList<>();
    String pos;
    boolean itemSelected = false;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_services);

        getSupportActionBar().setTitle("MyClinic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        addServ = (Button)findViewById(R.id.addServiceButton);
        deleteServ = (Button) findViewById(R.id.deleteService);
        modifyServ = (Button) findViewById(R.id.modifyService);
        listService = (ListView) findViewById(R.id.listViewService);

        //list = MainActivity.adminAccount.getServices();
        for (int i = 0; i < MainActivity.services.size(); i++){
            Service temp = MainActivity.services.get(i);

            //list.add(temp.username);
            list.add("Service: " + temp.getName());
            list2.add(temp.id);
        }

        adapter = new ArrayAdapter(ListOfServicesActivity.this, android.R.layout.simple_list_item_1, list);
        listService.setAdapter(adapter);


        listService.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(ListOfServicesActivity.this, "Press Delete button to delete " + list.get(position), Toast.LENGTH_SHORT).show();
                pos = list2.get(position);
                itemSelected = true;

            }
        });

        modifyServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openModifyService();
            }
        });

        addServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAddService();
            }
        });

        deleteServ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemSelected == true) {
                    //deletes account from database
                    deleteService(pos);
                    //removes account from listView
                    finish();
                    startActivity(getIntent());
                }
            }
        });

    }

    public void openModifyService(){
        if(itemSelected == true) {
            Intent intent = new Intent(this, ModifyServiceActivity.class);
            intent.putExtra("id", pos);
            startActivity(intent);
        }
    }
    public void openAddService(){
        Intent intent = new Intent(this, AddServiceActivity.class);
        startActivity(intent);

    }



    //methode that deletes service from database when called   NOTE: i have not called it in the code yet
    public boolean deleteService(String id){
        //get specific product reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("services").child(id);
        //removing account
        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Service Deleted ", Toast.LENGTH_SHORT).show();
        return true;

    }
}
