package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbproject.DB.MYDB;

public class addEvent extends AppCompatActivity {


    Button addEvent;
    EditText eventName;
    EditText startDate;
    EditText endDate;
    MYDB DB;

    String societyid_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);


        addEvent = (Button) findViewById(R.id.addButtonEvent);
        eventName = (EditText) findViewById(R.id.eventName);
        startDate = (EditText) findViewById(R.id.stDate);
        endDate = (EditText) findViewById(R.id.endDate);


        //==this is coming from update soceity====/
        societyid_admin = getIntent().getStringExtra("societyid");

        DB = new MYDB(this);

        addEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String eventname;
                String startdate;
                String enddate;
                int societyid;

                eventname = eventName.getText().toString();
                startdate = startDate.getText().toString();
                enddate = endDate.getText().toString();
                societyid = Integer.parseInt(societyid_admin);

                if(eventname.isEmpty() || startdate.isEmpty() || enddate.isEmpty()) {

                    Toast.makeText(addEvent.this, "Field Is Empty....", Toast.LENGTH_SHORT).show();
                }
                else if(!eventname.isEmpty() && !startdate.isEmpty() && !enddate.isEmpty()){

                    if(DB.checkEvent(eventname))
                    {
                        Toast.makeText(addEvent.this, "Event already exist..", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        DB.addEvent(eventname,startdate,enddate,societyid);
                        Toast.makeText(addEvent.this, "Event Added..", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(addEvent.this, HeadDashboardActivity.class);
                        startActivity(i);
                    }

                }
            }
        });



    }
}