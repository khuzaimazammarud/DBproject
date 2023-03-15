package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbproject.DB.MYDB;

public class UpdateEvent extends AppCompatActivity {


    private EditText eventNameedt, eventStartedt, eventEndedt;
    private Button updateCourseBtn,deleteEventbtn;
    private MYDB dbHandler;
    String eventName, startdate, enddate, SocietyId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);

        eventNameedt = findViewById(R.id.editEventName);
        eventStartedt = findViewById(R.id.editStartDate);
        eventEndedt = findViewById(R.id.editEndDate);


        updateCourseBtn = findViewById(R.id.idBtnUpdateEvent);
        deleteEventbtn = findViewById(R.id.idBtnDeleteEvent);

        // on below line we are initialing our dbhandler class.
        dbHandler = new MYDB(this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        eventName = getIntent().getStringExtra("eventname");
        startdate = getIntent().getStringExtra("startdate");
        enddate = getIntent().getStringExtra("enddate");
        SocietyId = getIntent().getStringExtra("societyid");

        // setting data to edit text
        // of our update activity.
        eventNameedt.setText(eventName);
        eventStartedt.setText(startdate);
        eventEndedt.setText(enddate);

        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(dbHandler.checkEvent(eventNameedt.getText().toString()))
                {
                    Toast.makeText(UpdateEvent.this, "Event already exist..", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    dbHandler.updateEvent(eventName,eventNameedt.getText().toString(), eventStartedt.getText().toString(),eventEndedt.getText().toString(),Integer.parseInt(SocietyId));
                    Toast.makeText(UpdateEvent.this, "Event Updated..", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(UpdateEvent.this, HeadDashboardActivity.class);
                    startActivity(i);
                }

            }
        });

        deleteEventbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHandler.deleteEvent(eventName);
                Toast.makeText(UpdateEvent.this, "Deleted the Event", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateEvent.this, AdminPanel.class);
                startActivity(i);
            }
        });

    }
}