package com.example.myclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

public class BookWorkingHourActivity extends AppCompatActivity {

    public static boolean reloadNeeded = true ;

    private Button select;
    private String employeeID;
    private String accountID;
    private String workingHourID;
    private String dayString;

    private String startHour;
    private String startMinute;

    private String endHour;
    private String endMinute;

    private ListView listofWorkingHour;
    private EmployeeAccount employeeAccount;


    private DatabaseReference workingList;
    private ArrayAdapter adapter3;

    List<String> list = new ArrayList<>();
    //contain id
    List<String> list2 = new ArrayList<>();

    //contains day
    List<String> list3 = new ArrayList<>();

    //contains startTime and endtime
    List<String> list4 = new ArrayList<>();
    List<String> list5 = new ArrayList<>();
    List<String> list6 = new ArrayList<>();
    List<String> list7 = new ArrayList<>();


    private int pos;
    private boolean itemSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_working_hour);

        employeeID = getIntent().getStringExtra("employee");
        accountID = getIntent().getStringExtra("account");

        select = (Button) findViewById(R.id.select2Button);

        listofWorkingHour = (ListView) findViewById(R.id.workingHour2ListView);

        workingList = FirebaseDatabase.getInstance().getReference("employeeAccounts").child(employeeID).child("workingHour");

        workingList.addListenerForSingleValueEvent(new ValueEventListener() {// these methods implement what happens
            @Override                                                   //when there is a change in data
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                employeeAccount.workingHours.clear();

                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    WorkingHours workH = postsnapshot.getValue(WorkingHours.class);
                    employeeAccount.workingHours.add(workH);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        for(int i = 0; i < MainActivity.employeeAccounts.size(); i++){
            if(MainActivity.employeeAccounts.get(i).getId().equals(employeeID)){
                employeeAccount = MainActivity.employeeAccounts.get(i);

                for(int j = 0; j <  employeeAccount.workingHours.size(); j++){
                    String h1 = Integer.toString(employeeAccount.workingHours.get(j).getStartHour());
                    String h2 = Integer.toString(employeeAccount.workingHours.get(j).getEndHour());
                    String m1 = Integer.toString(employeeAccount.workingHours.get(j).getStartMinute());
                    String m2 = Integer.toString(employeeAccount.workingHours.get(j).getEndMinute());

                    list.add( employeeAccount.workingHours.get(j).getDay() + "           From: " + h1 + ":" +  m1+ " To: " +h2+ ":" + m2);
                    list2.add(employeeAccount.workingHours.get(j).getId());
                    list3.add(employeeAccount.workingHours.get(j).getDay());

                    list4.add(h1);
                    list5.add(m1);
                    list6.add(h2);
                    list7.add(m2);


                }

                break;
            }
        }

        listofWorkingHour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                workingHourID = list2.get(position);
                dayString = list3.get(position);
                startHour = list4.get(position);

                startMinute = list5.get(position);
                endHour = list6.get(position);
                endMinute = list7.get(position);


                itemSelected = true;

            }
        });

        if (reloadNeeded){
            refresh();
            reloadNeeded = false;
        }

        adapter3 = new ArrayAdapter(BookWorkingHourActivity.this, android.R.layout.simple_list_item_1, list);
        listofWorkingHour.setAdapter(adapter3);


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemSelected == true) {
                    openWeekdayActivity();
                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(BookWorkingHourActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("You must select a Working Hour before clicking the button");
                    alertDialog.show();
                }

            }
        });
    }

    public void refresh(){
        finish();
        startActivity(getIntent());
        return;
    }

    public void openWeekdayActivity(){

        Intent intent = new Intent(this, BookWeekdayActivity.class);
        intent.putExtra("employee", employeeID);
        intent.putExtra("account", accountID );
        intent.putExtra("workingHour", workingHourID);

        intent.putExtra("day", dayString);
        intent.putExtra("startHour", startHour);

        intent.putExtra("startMinute", startMinute);
        intent.putExtra("endHour", endHour);
        intent.putExtra("endMinute", endMinute);


        startActivity(intent);

    }
}
