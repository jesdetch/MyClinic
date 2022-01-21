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

import java.util.ArrayList;
import java.util.List;

public class PatientSeeClinicActivity extends AppCompatActivity {

    private String accountID;
    private ListView listOfClinics;
    private ArrayList<String> list = new ArrayList<>();
    private ArrayList<String> list2 = new ArrayList<>();
    private Button select;
    boolean itemSelected = false;
    private String pos;


    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_see_clinic);

        accountID = getIntent().getStringExtra("account");
        list = getIntent().getStringArrayListExtra("accounts");
        list2 = getIntent().getStringArrayListExtra("Ids");


        listOfClinics = (ListView) findViewById(R.id.clinicsListView);
        select = (Button) findViewById(R.id.selectButton);;


        //for (int i = 1; i < MainActivity.employeeAccounts.size(); i++){
            //EmployeeAccount temp = MainActivity.employeeAccounts.get(i);
            //list2.add(temp.id);
            //list.add("ClinicName: " + temp.clinicName );
        //}

        adapter = new ArrayAdapter(PatientSeeClinicActivity.this, android.R.layout.simple_list_item_1, list);
        listOfClinics.setAdapter(adapter);


        listOfClinics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(PatientSeeClinicActivity.this, "Press select button to see the clinics details \n " + list.get(position), Toast.LENGTH_SHORT).show();
                pos = list2.get(position);
                itemSelected = true;

            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemSelected == true){
                    //deletes account from database
                    openSeeClinicDetails();

                }
            }
        });





    }

    public void openSeeClinicDetails(){
        Intent intent = new Intent(this, seeClinicDetailsActivity.class);
        intent.putExtra("employee", pos );
        intent.putExtra("account", accountID);
        startActivity(intent);

    }

    public String getID(int index){
        return this.list2.get(index);
    }
}
