package com.example.myclinic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button signInButton;
    private Button createButton;
    public static List<UserAccount> accounts; //arraylist for the accounts
    public static List<Service> services;
    public static List<EmployeeAccount> employeeAccounts;
    public static List<PatientAccount> patientAccounts;

    public static List<WorkingHours> workingHours;
    public static DatabaseReference accountDatabase; //the database variable
    public static DatabaseReference servicedatabase;
    public static DatabaseReference employeeAccountDatabase;
    public static DatabaseReference workingHoursDataBase;
    public static DatabaseReference patientAccountDataBase;

    public static AdminAccount adminAccount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        signInButton = (Button) findViewById(R.id.bnt_signIn);
        createButton = (Button) findViewById(R.id.createButton); //this is the button on the home screen


        accountDatabase = FirebaseDatabase.getInstance().getReference("accounts");
        servicedatabase = FirebaseDatabase.getInstance().getReference("services");
        employeeAccountDatabase = FirebaseDatabase.getInstance().getReference("employeeAccounts");
        patientAccountDataBase = FirebaseDatabase.getInstance().getReference("patientAccounts");

        workingHoursDataBase = FirebaseDatabase.getInstance().getReference("workingHours");
        accounts = new ArrayList<>();//initialize both account based variables
        services = new ArrayList<>();
        employeeAccounts = new ArrayList<>();
        workingHours = new ArrayList<>();
        patientAccounts = new ArrayList<>();
        //create space in database storing working hours

        accountDatabase.addValueEventListener(new ValueEventListener() {// these methods implement what happens
            @Override                                                   //when there is a change in data
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                accounts.clear();

                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    UserAccount account = postsnapshot.getValue(UserAccount.class);
                    accounts.add(account);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        servicedatabase.addValueEventListener(new ValueEventListener() {// these methods implement what happens
            @Override                                                   //when there is a change in data
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                services.clear();

                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    Service service = postsnapshot.getValue(Service.class);
                    services.add(service);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        employeeAccountDatabase.addValueEventListener(new ValueEventListener() {// these methods implement what happens
            @Override                                                   //when there is a change in data
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                employeeAccounts.clear();

                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    EmployeeAccount account = postsnapshot.getValue(EmployeeAccount.class);
                    employeeAccounts.add(account);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        patientAccountDataBase.addValueEventListener(new ValueEventListener() {// these methods implement what happens
            @Override                                                   //when there is a change in data
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                patientAccounts.clear();

                for (DataSnapshot postsnapshot : dataSnapshot.getChildren()){
                    PatientAccount account = postsnapshot.getValue(PatientAccount.class);
                    patientAccounts.add(account);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignInActivity();
            }
        });

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreateAccountActivity();
            }
        });

    }

    public void openSignInActivity(){
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);

    }

    public void openCreateAccountActivity(){
        Intent intent2 = new Intent(this, CreateAccountActivity.class);
        startActivity(intent2);

    }

}
