package com.example.myclinic;

import androidx.appcompat.app.AppCompatActivity;

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

public class ListOfAccountActivity extends AppCompatActivity {

    private Button deleteAccount;
    private ListView listAccount;
    List<String> list = new ArrayList<>();
    List<String> list2 = new ArrayList<>();
    boolean itemSelected = false;

    ArrayAdapter adapter;
    String pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_account);

        getSupportActionBar().setTitle("MyClinic");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        deleteAccount = (Button) findViewById(R.id.DeleteAccount);
        listAccount = (ListView) findViewById(R.id.listViewAccount);

        /*
        * change this to a for loop that adds all of the elements
        * in the account in firebase to arraylist add
        */

        for (int i = 1; i < MainActivity.accounts.size(); i++){
            UserAccount temp = MainActivity.accounts.get(i);
            list2.add(temp.id);
            list.add("UserName: " + temp.username + "\nPassword: "+ temp.password);
        }

        adapter = new ArrayAdapter(ListOfAccountActivity.this, android.R.layout.simple_list_item_1, list);
        listAccount.setAdapter(adapter);

        listAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                Toast.makeText(ListOfAccountActivity.this, "Press Delete Button to delete the account \n " + list.get(position), Toast.LENGTH_SHORT).show();
                pos = list2.get(position);
                itemSelected = true;

            }
        });

        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemSelected == true){
                    //deletes account from database
                    deleteAccount(pos);
                    //removes account from listView
                    finish();
                    startActivity(getIntent());
                }
            }
        });


    }

    public boolean deleteAccount(String id){
        //get specific product reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("accounts").child(id);
        for (int i = 0; i < MainActivity.accounts.size(); i++){
            if(MainActivity.accounts.get(i).getId().equals(id)){
                if(MainActivity.accounts.get(i).getType().equals("employee")){
                    DatabaseReference dR2 = FirebaseDatabase.getInstance().getReference("employeeAccounts").child(id);
                    dR2.removeValue();
                }
                else{
                    DatabaseReference dR2 = FirebaseDatabase.getInstance().getReference("patientAccounts").child(id);
                    dR2.removeValue();
                }
            }
        }

        //removing account

        dR.removeValue();
        Toast.makeText(getApplicationContext(), "Account Deleted ", Toast.LENGTH_SHORT).show();
        return true;

    }


}
