package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbproject.DB.MYDB;

public class UpdateSociety extends AppCompatActivity {


    private EditText userNameedt,societyIdEdt , headname, userid;
    private Button updateCourseBtn, deleteCourseBtn, addEventBtn;
    private MYDB dbHandler;
    String userName,societyid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_society);


        userNameedt = findViewById(R.id.edtSocietyName);
        headname = findViewById(R.id.edtSocietyheadName);
        userid = findViewById(R.id.edtheaduserid);
        societyIdEdt = findViewById(R.id.edtsocietyidhead);


        updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);
        deleteCourseBtn = findViewById(R.id.idBtnDelete);
        addEventBtn = findViewById(R.id.idBtnEaddEvent);

        // on below line we are initialing our dbhandler class.
        dbHandler = new MYDB(this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        userName = getIntent().getStringExtra("name");
        societyid = getIntent().getStringExtra("societyid");


        // setting data to edit text
        // of our update activity.
        userNameedt.setText(userName);

        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // inside this method we are calling an update course
                // method and passing all our edit text values.

//                if(dbHandler.checkSociety(userNameedt.getText().toString()))
//                {
//                    Toast.makeText(UpdateSociety.this, "Society already exist..", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    dbHandler.updateSociety(userName,userNameedt.getText().toString());

                dbHandler.addhead(headname.getText().toString(),Integer.parseInt(userid.getText().toString()),Integer.parseInt(societyIdEdt.getText().toString()));

                    // displaying a toast message that our course has been updated.
                    Toast.makeText(UpdateSociety.this, "Head Added..", Toast.LENGTH_SHORT).show();

                    // launching our main activity.
                    Intent i = new Intent(UpdateSociety.this, AdminPanel.class);
                    startActivity(i);
//                }

            }
        });

        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our course.
                dbHandler.deleteSociety(userName);
                Toast.makeText(UpdateSociety.this, "Deleted the Society", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateSociety.this, AdminPanel.class);
                startActivity(i);
            }
        });

        addEventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(UpdateSociety.this, addEvent.class);
                i.putExtra("societyid",societyid);
                startActivity(i);

            }
        });


    }
}