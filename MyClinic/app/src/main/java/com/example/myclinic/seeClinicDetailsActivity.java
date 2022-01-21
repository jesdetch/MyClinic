package com.example.myclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class seeClinicDetailsActivity extends AppCompatActivity {

    public static boolean reloadNeeded = true ;

    private String employeeID;
    private String accountID;


    private ListView listOfservices;
    private ListView listOfHours;
    private Button bookAppointmentBut;
    private Button checkInBut;
    private EmployeeAccount employeeAccount;
    private DatabaseReference workingList;
    private DatabaseReference serviceList;

    private TextView clinicName;
    private TextView addressName;
    private TextView phoneNumber;
    private TextView insurancetype;
    private TextView paymentMethod;



    List<String> list = new ArrayList<>();
    List<String> list2 = new ArrayList<>();

    ArrayAdapter adapter;
    ArrayAdapter adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_clinic_details);

        employeeID = getIntent().getStringExtra("employee");
        accountID = getIntent().getStringExtra("account");

        bookAppointmentBut = (Button) findViewById(R.id.BookAppointmentDutton);
        checkInBut = (Button) findViewById(R.id.checkInButton);

        listOfservices = (ListView) findViewById(R.id.servicesListview);
        listOfHours = (ListView) findViewById(R.id.hoursListView);

        clinicName = (TextView) findViewById(R.id.specificNameTextView);
        addressName = (TextView) findViewById(R.id.specificAddressTextView);
        phoneNumber = (TextView) findViewById(R.id.specificTelTextView);
        insurancetype = (TextView) findViewById(R.id.specificInsurance);
        paymentMethod = (TextView) findViewById(R.id.specificPaymentMethodTextView);


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

        serviceList = FirebaseDatabase.getInstance().getReference("employeeAccounts").child(employeeID).child("services");

        serviceList.addListenerForSingleValueEvent(new ValueEventListener() {// these methods implement what happens
            @Override                                                   //when there is a change in data
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                employeeAccount.services.clear();
                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    Service service = postsnapshot.getValue(Service.class);
                    employeeAccount.services.add(service);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        for(int i = 0; i < MainActivity.employeeAccounts.size(); i++){
            if(MainActivity.employeeAccounts.get(i).getId().equals(employeeID)){
                employeeAccount = MainActivity.employeeAccounts.get(i);

                for(int j = 0; j < employeeAccount.workingHours.size(); j++){
                    String h1 = Integer.toString(employeeAccount.workingHours.get(j).getStartHour());
                    String h2 = Integer.toString(employeeAccount.workingHours.get(j).getEndHour());
                    String m1 = Integer.toString(employeeAccount.workingHours.get(j).getStartMinute());
                    String m2 = Integer.toString(employeeAccount.workingHours.get(j).getEndMinute());

                    list2.add( employeeAccount.workingHours.get(j).getDay() + "           From: " + h1 + ":" +  m1+ " To: " +h2+ ":" + m2);

                }
                break;
            }
        }


        for(int i = 0; i < MainActivity.employeeAccounts.size(); i++){
            if(MainActivity.employeeAccounts.get(i).getId().equals(employeeID)){
                employeeAccount = MainActivity.employeeAccounts.get(i);
                for(int j = 0; j < employeeAccount.services.size(); j++){
                    list.add("Service: " + employeeAccount.services.get(j).getName() + "Role: " + employeeAccount.services.get(j).getRole());
                }
            }
        }

        if (reloadNeeded){
            refresh();
            reloadNeeded = false;
        }

        clinicName.setText(" Clinic Name: "+ employeeAccount.clinicName);
        addressName.setText(" Clinic Adress: "+ employeeAccount.address);
        phoneNumber.setText(" Clinic PhoneNumber: "+employeeAccount.phoneNumber);
        insurancetype.setText(" Clinic Insurance Type: "+employeeAccount.insuranceType);
        paymentMethod.setText(" Clinic Payment Method: "+employeeAccount.paymentMethod);


        adapter = new ArrayAdapter(seeClinicDetailsActivity.this, android.R.layout.simple_list_item_1, list);
        listOfservices.setAdapter(adapter);

        adapter2 = new ArrayAdapter(seeClinicDetailsActivity.this, android.R.layout.simple_list_item_1, list2);
        listOfHours.setAdapter(adapter2);

        bookAppointmentBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBookWorkingHourActivity();
            }
        });

        checkInBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCheckInAsctivity();
            }
        });



    }

    public void refresh(){
        finish();
        startActivity(getIntent());
        return;
    }

    public void openBookWorkingHourActivity(){
        Intent intent = new Intent(this, BookWorkingHourActivity.class);
        intent.putExtra("employee", employeeID);
        intent.putExtra("account", accountID );
        startActivity(intent);

    }

    public void openCheckInAsctivity(){
        for (int i = 0; i < MainActivity.employeeAccounts.size(); i++){
            if(employeeID.equals(MainActivity.employeeAccounts.get(i).getId())){
                MainActivity.employeeAccounts.get(i).incrementWaitTime();
            }
        }

        Intent intent = new Intent(this, CheckInActivity.class);
        intent.putExtra("employee", employeeID);
        intent.putExtra("account", accountID );
        startActivity(intent);

    }


}
