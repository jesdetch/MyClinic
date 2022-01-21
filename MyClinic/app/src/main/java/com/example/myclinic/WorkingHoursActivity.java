package com.example.myclinic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class WorkingHoursActivity extends AppCompatActivity {

    private Spinner s1, s2, s3, s4, s32, s31, s30, s8 ,s9, s12, s13 , s14, s15, s16;
    private Spinner s25, s26, s27, s29, s33, s28 , s19,s17, s18, s20, s22, s21 , s23, s24;
    private Button  set;

    private WorkingHours monday;
    private WorkingHours tuesday;
    private WorkingHours wednesday;
    private WorkingHours thursday;
    private WorkingHours friday;
    private WorkingHours saturday;
    private WorkingHours sunday;


    private String accountID;

    private Button back4;

    private Button sundayBut;
    private Button saturdayBut;
    private Button fridayBut;
    private Button thursdayBut;
    private Button wednesdayBut;
    private Button tuesdayBut;
    private Button mondayBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_working_hours);

            getSupportActionBar().setTitle("Set Working Hours");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            accountID = getIntent().getStringExtra("account");
            back4 = (Button) findViewById(R.id.back4);

            List<String> listHour = new ArrayList<>();
            List<String> listMinutes = new ArrayList<>(); //methods to extract data from spinner?
            Toast.makeText(this, "Click on theweekday Button to add a work time on that Day ", Toast.LENGTH_LONG).show();


            listHour.add("00");
            listHour.add("01");
            listHour.add("02");
            listHour.add("03");
            listHour.add("04");
            listHour.add("05");
            listHour.add("06");
            listHour.add("07");
            listHour.add("08");
            listHour.add("09");
            listHour.add("10");
            listHour.add("11");
            listHour.add("12");
            listHour.add("13");
            listHour.add("14");
            listHour.add("15");
            listHour.add("16");
            listHour.add("17");
            listHour.add("18");
            listHour.add("19");
            listHour.add("20");
            listHour.add("21");
            listHour.add("22");
            listHour.add("23");
            listHour.add("24");
            listMinutes.add("00");
            listMinutes.add("05");
            listMinutes.add("10");
            listMinutes.add("15");
            listMinutes.add("20");
            listMinutes.add("25");
            listMinutes.add("30");
            listMinutes.add("35");
            listMinutes.add("40");
            listMinutes.add("45");
            listMinutes.add("50");
            listMinutes.add("55");


            //methods to extract data from spinner?
            set = (Button) findViewById(R.id.SetButton);

            s1 = (Spinner) findViewById(R.id.spinner);
            s2 = (Spinner) findViewById(R.id.spinner2);
            s4 = (Spinner) findViewById(R.id.spinner4);
            s8 = (Spinner) findViewById(R.id.spinner8);
            s9 = (Spinner) findViewById(R.id.spinner9);

            s20 = (Spinner) findViewById(R.id.spinner20);
            s28 = (Spinner) findViewById(R.id.spinner28);
            s19 = (Spinner) findViewById(R.id.spinner19);
            s21 = (Spinner) findViewById(R.id.spinner21);
            s22 = (Spinner) findViewById(R.id.spinner22);

            s23 = (Spinner) findViewById(R.id.spinner23);
            s24 = (Spinner) findViewById(R.id.spinner24);
            s25 = (Spinner) findViewById(R.id.spinner25);
            s26 = (Spinner) findViewById(R.id.spinner26);
            s27 = (Spinner) findViewById(R.id.spinner27);

            s29 = (Spinner) findViewById(R.id.spinner29);
            s12 = (Spinner) findViewById(R.id.spinner12);
            s13 = (Spinner) findViewById(R.id.spinner13);
            s14 = (Spinner) findViewById(R.id.spinner14);
            s16 = (Spinner) findViewById(R.id.spinner16);

            s27 = (Spinner) findViewById(R.id.spinner27);
            s17 = (Spinner) findViewById(R.id.spinner17);
            s15 = (Spinner) findViewById(R.id.spinner15);
            s30 = (Spinner) findViewById(R.id.spinner30);
            s31 = (Spinner) findViewById(R.id.spinner31);

            s32 = (Spinner) findViewById(R.id.spinner32);
            s33 = (Spinner) findViewById(R.id.spinner33);
            s18 = (Spinner) findViewById(R.id.spinner18);
            s3 = (Spinner) findViewById(R.id.spinner3);

            fridayBut = (Button) findViewById(R.id.fridayButton);
            sundayBut = (Button) findViewById(R.id.sundayButton);
            saturdayBut = (Button) findViewById(R.id.saturdayButton);
            thursdayBut = (Button) findViewById(R.id.thurdayButton);
            wednesdayBut = (Button) findViewById(R.id.wednesdayButton);
            tuesdayBut = (Button) findViewById(R.id.tuesdayButton);
            mondayBut = (Button) findViewById(R.id.mondayButton);


            final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listHour);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            final ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,listMinutes);
            adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            s1.setAdapter(adapter);
            s30.setAdapter(adapter);
            s20.setAdapter(adapter);
            s22.setAdapter(adapter);
            s26.setAdapter(adapter);
            s15.setAdapter(adapter);
            s18.setAdapter(adapter);
            s8.setAdapter(adapter);
            s32.setAdapter(adapter);
            s28.setAdapter(adapter);
            s24.setAdapter(adapter);
            s26.setAdapter(adapter);
            s16.setAdapter(adapter);
            s12.setAdapter(adapter);
            s3.setAdapter(adapter);

            s31.setAdapter(adapter2);
            s27.setAdapter(adapter2);
            s23.setAdapter(adapter2);
            s19.setAdapter(adapter2);
            s17.setAdapter(adapter2);
            s9.setAdapter(adapter2);
            s2.setAdapter(adapter2);
            s33.setAdapter(adapter2);
            s29.setAdapter(adapter2);
            s25.setAdapter(adapter2);
            s21.setAdapter(adapter2);
            s14.setAdapter(adapter2);
            s13.setAdapter(adapter2);
            s4.setAdapter(adapter2);

            mondayBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (s30.getSelectedItem().toString().compareTo(s32.getSelectedItem().toString()) < 0 ) {
                        addWorkingHours("Monday", Integer.valueOf(s30.getSelectedItem().toString()), Integer.valueOf(s31.getSelectedItem().toString()), Integer.valueOf(s32.getSelectedItem().toString()), Integer.valueOf(s33.getSelectedItem().toString()));
                    }else{
                        AlertDialog alertDialog = new AlertDialog.Builder(WorkingHoursActivity.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("The time selected is invalid");
                        alertDialog.show();
                    }
                }
            });

            tuesdayBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (s26.getSelectedItem().toString().compareTo(s28.getSelectedItem().toString()) < 0 ) {
                            addWorkingHours("Tuesday", Integer.valueOf(s26.getSelectedItem().toString()), Integer.valueOf(s27.getSelectedItem().toString()), Integer.valueOf(s28.getSelectedItem().toString()), Integer.valueOf(s29.getSelectedItem().toString()));
                    }else{
                        AlertDialog alertDialog = new AlertDialog.Builder(WorkingHoursActivity.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("The time selected is invalid");
                        alertDialog.show();
                    }
                }
            });

            wednesdayBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (s22.getSelectedItem().toString().compareTo(s24.getSelectedItem().toString()) < 0) {
                        addWorkingHours("Wednesday", Integer.valueOf(s22.getSelectedItem().toString()), Integer.valueOf(s23.getSelectedItem().toString()), Integer.valueOf(s24.getSelectedItem().toString()), Integer.valueOf(s25.getSelectedItem().toString()));
                    } else {
                        AlertDialog alertDialog = new AlertDialog.Builder(WorkingHoursActivity.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("The time selected is invalid");
                        alertDialog.show();
                    }
                }

            });

            thursdayBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (s18.getSelectedItem().toString().compareTo(s20.getSelectedItem().toString()) < 0 ) {
                        addWorkingHours("Thurday", Integer.valueOf(s18.getSelectedItem().toString()), Integer.valueOf(s19.getSelectedItem().toString()), Integer.valueOf(s20.getSelectedItem().toString()), Integer.valueOf(s21.getSelectedItem().toString()));
                    }else {
                            AlertDialog alertDialog = new AlertDialog.Builder(WorkingHoursActivity.this).create();
                            alertDialog.setTitle("Error");
                            alertDialog.setMessage("The time selected is invalid");
                            alertDialog.show();
                        }
                }
            });

            fridayBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (s15.getSelectedItem().toString().compareTo(s16.getSelectedItem().toString()) < 0 ) {
                        addWorkingHours("Friday", Integer.valueOf(s15.getSelectedItem().toString()), Integer.valueOf(s17.getSelectedItem().toString()), Integer.valueOf(s16.getSelectedItem().toString()), Integer.valueOf(s14.getSelectedItem().toString()));
                    }else {
                        AlertDialog alertDialog = new AlertDialog.Builder(WorkingHoursActivity.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("The time selected is invalid");
                        alertDialog.show();
                    }
                }
            });

            saturdayBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (s8.getSelectedItem().toString().compareTo(s12.getSelectedItem().toString()) < 0 ) {

                        addWorkingHours("Saturday", Integer.valueOf(s8.getSelectedItem().toString()), Integer.valueOf(s9.getSelectedItem().toString()), Integer.valueOf(s12.getSelectedItem().toString()), Integer.valueOf(s13.getSelectedItem().toString()));
                     }else {
                        AlertDialog alertDialog = new AlertDialog.Builder(WorkingHoursActivity.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("The time selected is invalid");
                        alertDialog.show();
                    }
                }
            });

            sundayBut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (s1.getSelectedItem().toString().compareTo(s3.getSelectedItem().toString()) < 0 ) {
                        addWorkingHours("Sunday", Integer.valueOf(s1.getSelectedItem().toString()), Integer.valueOf(s2.getSelectedItem().toString()), Integer.valueOf(s3.getSelectedItem().toString()), Integer.valueOf(s4.getSelectedItem().toString()));
                    }else {
                        AlertDialog alertDialog = new AlertDialog.Builder(WorkingHoursActivity.this).create();
                        alertDialog.setTitle("Error");
                        alertDialog.setMessage("The time selected is invalid");
                        alertDialog.show();
                    }
                }
            });


             set.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                    openSeeWorkingHours();
                     //addWorkingHours();
                 }
             });

             back4.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     openSeeWorkingHours();
                 }
             });
    }

    public void addWorkingHours(String day, int startH, int startM, int endH, int endM){ // method to add and store workingHours into the database

            String id = MainActivity.employeeAccountDatabase.push().getKey();

            if ( !day.equals(null) ) {

                WorkingHours workingHour= new WorkingHours(id, day, startH, startM, endH, endM);
                MainActivity.employeeAccountDatabase.child(accountID).child("workingHour").child(id).setValue(workingHour);
                MainActivity.workingHoursDataBase.child(id).setValue(workingHour);
                seeWorkingHoursActivity.reloadNeeded = true;

            }

        Toast.makeText(this, "WorkingHours created ", Toast.LENGTH_LONG).show();

    }

    public void openSeeWorkingHours(){
        Intent intent = new Intent(this, seeWorkingHoursActivity.class);
        intent.putExtra("account", accountID );
        startActivity(intent);
    }


}
