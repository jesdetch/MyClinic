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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;


public class BookWeekdayActivity extends AppCompatActivity {


    public static boolean reloadNeeded = true ;

    private Button select;
    private ListView listofWorkingHour;

    private String employeeID;
    private String accountID;
    private String workingHourID;
    private String dayString;
    private String startHour;
    private String startMinute;
    private String endHour;
    private String endMinute;

    private Boolean itemSelected = false;
    private EmployeeAccount employeeAccount;


    private Calendar calendar;
    private ArrayAdapter adapter4;

    List<String> listOfDays = new ArrayList<>();

    private WorkingHours workingH;
    private String Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_weekday);

        workingHourID = getIntent().getStringExtra("workingHour");
        employeeID = getIntent().getStringExtra("employee");
        accountID = getIntent().getStringExtra("account");
        dayString = getIntent().getStringExtra("day");
        startHour = getIntent().getStringExtra("startHour");


        startMinute = getIntent().getStringExtra("startMinute");
        endHour = getIntent().getStringExtra("endHour");
        endMinute = getIntent().getStringExtra("endMinute");


        listofWorkingHour = (ListView) findViewById(R.id.WeekdayListView);
        select = (Button) findViewById(R.id.select3Button);


        if (reloadNeeded){
            refresh();
            reloadNeeded = false;
        }

        calendar = Calendar.getInstance();
        DateFormat format1 = new SimpleDateFormat("EEE, d MMM yyyy");
        String strDate = format1.format(calendar.getTime());


        if (dayString.equals("Monday")) {
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
                calendar.add(Calendar.DATE, 1);
            }
            format1 = new SimpleDateFormat("EEE, d MMM yyyy");
            strDate = format1.format(calendar.getTime());
            listOfDays.add(strDate);

        }else if (dayString.equals("Tuesday")){
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY) {
                calendar.add(Calendar.DATE, 1);
            }
            format1 = new SimpleDateFormat("EEE, d MMM yyyy");
            strDate = format1.format(calendar.getTime());
            listOfDays.add(strDate);

        }else if (dayString.equals("Wednesday")){
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY) {
                calendar.add(Calendar.DATE, 1);
            }
            format1 = new SimpleDateFormat("EEE, d MMM yyyy");
            strDate = format1.format(calendar.getTime());
            listOfDays.add(strDate);

        }else if (dayString.equals("Thurday")){
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY) {
                calendar.add(Calendar.DATE, 1);
            }
            format1 = new SimpleDateFormat("EEE, d MMM yyyy");
            strDate = format1.format(calendar.getTime());
            listOfDays.add(strDate);

        }else if (dayString.equals("Friday")){
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
                calendar.add(Calendar.DATE, 1);
            }
            format1 = new SimpleDateFormat("EEE, d MMM yyyy");
            strDate = format1.format(calendar.getTime());
            listOfDays.add(strDate);

        }else if (dayString.equals("Saturday")){
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                calendar.add(Calendar.DATE, 1);
            }
            format1 = new SimpleDateFormat("EEE, d MMM yyyy");
            strDate = format1.format(calendar.getTime());
            listOfDays.add(strDate);

        }else if (dayString.equals("Sunday")){
            while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
                calendar.add(Calendar.DATE, 1);
            }
            format1 = new SimpleDateFormat("EEE, d MMM yyyy");
            strDate = format1.format(calendar.getTime());
            listOfDays.add(strDate);
        }


        for (int i = 0; i < 5; i++) {
            calendar.add(Calendar.DATE, 7);
            format1 = new SimpleDateFormat("EEE, d MMM yyyy");
            strDate = format1.format(calendar.getTime());
            listOfDays.add(strDate);
        }

        adapter4 = new ArrayAdapter(BookWeekdayActivity.this, android.R.layout.simple_list_item_1, listOfDays);
        listofWorkingHour.setAdapter(adapter4);

        listofWorkingHour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                itemSelected = true;
                Date = listOfDays.get(position);
            }
        });

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemSelected == true){
                    openTimeSlotActvity();

                }else{
                    AlertDialog alertDialog = new AlertDialog.Builder(BookWeekdayActivity.this).create();
                    alertDialog.setTitle("Error");
                    alertDialog.setMessage("You must select a day before clicking the button");
                    alertDialog.show();
                }

            }
        });
    }

    public void openTimeSlotActvity(){
        Intent intent = new Intent(this, BookTimeSlotActivity.class);
        intent.putExtra("employee", employeeID);
        intent.putExtra("account", accountID );
        intent.putExtra("workingHour", workingHourID);
        intent.putExtra("date", Date);
        intent.putExtra("startHour", startHour);
        intent.putExtra("startMinute", startMinute);
        intent.putExtra("endHour", endHour);
        intent.putExtra("endMinute", endMinute);

        startActivity(intent);
    }

    public void refresh(){
        finish();
        startActivity(getIntent());
        return;
    }

}
