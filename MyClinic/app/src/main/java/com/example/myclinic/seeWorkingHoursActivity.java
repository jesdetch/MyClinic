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

public class seeWorkingHoursActivity extends AppCompatActivity {

    public static boolean reloadNeeded = true ;
    private Button setWorkingHours;
    private String accountID;
    private List<String> list;
    private List<String> list2;
    private ListView listViewWorkourHours;
    private int location;
    private DatabaseReference workingList;
    private EmployeeAccount thisaccount;
    private static boolean itemsSelected = false;
    private Button back3;

    ArrayAdapter adapter;
    String pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_working_hours);

        getSupportActionBar().setTitle("My clinic");
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        accountID = getIntent().getStringExtra("account");

        setWorkingHours = (Button) findViewById(R.id.setWorkingHoursButton);
        listViewWorkourHours = (ListView) findViewById(R.id.workingHoursListView);
        back3 = (Button) findViewById(R.id.back3);
        list = new ArrayList();
        list2 = new ArrayList();



        for(int i = 0; i < MainActivity.employeeAccounts.size(); i++) {
            if (MainActivity.employeeAccounts.get(i).getId().equals(accountID)) {
                thisaccount = MainActivity.employeeAccounts.get(i);
            }
        }


        workingList = FirebaseDatabase.getInstance().getReference("employeeAccounts").child(accountID).child("workingHour");

        workingList.addListenerForSingleValueEvent(new ValueEventListener() {// these methods implement what happens
            @Override                                                   //when there is a change in data
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                thisaccount.workingHours.clear();

                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    WorkingHours workH = postsnapshot.getValue(WorkingHours.class);
                    thisaccount.workingHours.add(workH);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        if (reloadNeeded){
            refresh();
            reloadNeeded = false;
        }


        for(int i = 0; i < MainActivity.employeeAccounts.size(); i++){
            if(MainActivity.employeeAccounts.get(i).getId().equals(accountID)){
                for(int j = 0; j <  thisaccount.workingHours.size(); j++){
                    Toast.makeText(getApplicationContext(), "WorkingHours Added ", Toast.LENGTH_SHORT).show();

                    String h1 = Integer.toString(thisaccount.workingHours.get(j).getStartHour());
                    String h2 = Integer.toString(thisaccount.workingHours.get(j).getEndHour());
                    String m1 = Integer.toString(thisaccount.workingHours.get(j).getStartMinute());
                    String m2 = Integer.toString(thisaccount.workingHours.get(j).getEndMinute());

                    list.add( thisaccount.workingHours.get(j).getDay() + "           From: " + h1 + ":" +  m1+ " To: " +h2+ ":" + m2);
                    list2.add(thisaccount.workingHours.get(j).getId());
                }
                break;
            }
        }

        adapter = new ArrayAdapter(seeWorkingHoursActivity.this, android.R.layout.simple_list_item_1, list);
        listViewWorkourHours.setAdapter(adapter);



        setWorkingHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    openWorkingHours();

            }
        });

        back3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmployeeWelcomePage();
            }
        });

    }

    public void openWorkingHours(){
        Intent intent = new Intent(this, WorkingHoursActivity.class);
        intent.putExtra("account", accountID );
        startActivity(intent);
    }

    public void deleteWorkHour(String id){
        //get specific service reference
        MainActivity.employeeAccountDatabase.child(thisaccount.getId()).child("workingHour").child(id).removeValue();
        reloadNeeded = true;

    }

    public void refresh(){
        finish();
        startActivity(getIntent());
        return;
    }

    public void openEmployeeWelcomePage(){

        Intent intent = new Intent(this, EmployeeMainPageActivity.class);
        intent.putExtra("account", accountID);
        startActivity(intent);

    }
}
