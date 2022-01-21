package com.example.myclinic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookTimeSlotActivity extends AppCompatActivity {

    private String employeeID;
    private String accountID;
    private String workingHourID;

    private Button select;

    private ListView timeslotListView;

    private EmployeeAccount employeeAccount;
    private WorkingHours workingHours;

    private String startHour;
    private String startMinute;
    private String endHour;
    private String endMinute;

    private ArrayAdapter adapter5;

    private boolean allTimesPossible;
    List<String> list = new ArrayList<>();
    private int[] minutes;
    private int[] hours;

    private String time1;
    private String time2;
    private String dateStored;
    private String d;

    private boolean itemSelected = false;
    private boolean warned = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_time_slot);

        workingHourID = getIntent().getStringExtra("workingHour");
        employeeID = getIntent().getStringExtra("employee");
        accountID = getIntent().getStringExtra("account");
        dateStored = getIntent().getStringExtra("date");
        startHour = getIntent().getStringExtra("startHour");
        startMinute = getIntent().getStringExtra("startMinute");
        endHour = getIntent().getStringExtra("endHour");
        endMinute = getIntent().getStringExtra("endMinute");


        select = (Button) findViewById(R.id.select4Button);
        timeslotListView = (ListView) findViewById(R.id.timeslotListView);

        time1 = startHour + ":" + startMinute;
        time2 = endHour + ":" + endMinute;


        SimpleDateFormat tm1 = new SimpleDateFormat("HH:mm");
        //turn integers into times
        Date t1 = null;
        try {
            t1 = tm1.parse(time1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date t2 = null;

        try {
            t2 = tm1.parse(time2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar cal = Calendar.getInstance();

        Calendar cal2 = Calendar.getInstance();
        Calendar cal3 = Calendar.getInstance();

        cal.setTime(t1);
        cal2.setTime(t2);
        String newTime = tm1.format(cal.getTime());
        Boolean allPossibleTimes = false;

        //set multiple times possible
        while (allPossibleTimes == false) {


            if (cal.compareTo(cal2) < 0) {

                Date date = cal.getTime();
                SimpleDateFormat format1 = new SimpleDateFormat("HH:mm");
                String date1 = format1.format(date);
                list.add("At "  + date1 + " on " + dateStored);
                cal.add(Calendar.MINUTE, 30);
            } else {
                allPossibleTimes = true;
            }

        }

        adapter5 = new ArrayAdapter(BookTimeSlotActivity.this, android.R.layout.simple_list_item_1, list);
        timeslotListView.setAdapter(adapter5);

        timeslotListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemSelected = true;
                d = list.get(position);
            }
        });

        /*
        SimpleDateFormat formatter1=new SimpleDateFormat("At HH:mm on EEE, d MMM yyyy");
        Date ds = null;
        try {
            ds =formatter1.parse(d);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(ds);

        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);
        final int dayWeek = calendar.get(Calendar.DAY_OF_WEEK);
        final int timeH = calendar.get(Calendar.HOUR_OF_DAY);
        final int timeM = calendar.get(Calendar.MINUTE);

        Appointment appointment = new Appointment(dayWeek,month,day,timeH,timeM);
        */
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemSelected == true) {
                    if (warned == true) {

                       // updateAppointment(accountID);
                        openBookAppointmentActivity();
                    }else{
                        AlertDialog alertDialog = new AlertDialog.Builder(BookTimeSlotActivity.this).create();
                        alertDialog.setTitle("Verification");
                        alertDialog.setMessage("You can not modify or cancel an appointment. If you are certain you want to book the appointment click 'BOOK APPOINTMENT' again ");
                        alertDialog.show();
                        warned = true;
                    }
                }
            }
        });


    }


    public void openBookAppointmentActivity(){
        Intent intent = new Intent(this, BookAppointmentActivity.class);
        intent.putExtra("employee", employeeID);
        intent.putExtra("account", accountID );
        intent.putExtra("workingHour", workingHourID);
        startActivity(intent);
    }

    /*
    public void updateAppointment(int DayOfWeek, int month, int Day, int StartHour, int StartMinute){

        //get specific product reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("patientAccount").child(accountID);
        //update account
        PatientAccount patient = new PatientAccount(accountID,);
        dR.setValue(patient);
        Toast.makeText(getApplicationContext(), "Appointment Set ", Toast.LENGTH_SHORT).show();

    }*/

}
