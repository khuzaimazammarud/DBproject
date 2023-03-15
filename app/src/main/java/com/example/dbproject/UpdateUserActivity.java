package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbproject.DB.MYDB;
import com.example.dbproject.model.User;
import com.example.dbproject.model.userViewSociety;
import com.example.dbproject.utils.Test;

import java.util.ArrayList;

public class UpdateUserActivity extends AppCompatActivity {

    private EditText userNameedt, usersocietyIdedt, userPasswordedt, userEmailedt;
    private Button updateCourseBtn, deleteCourseBtn;
    private MYDB dbHandler;
    String userName, userEmail, userPassword, userSocietyId, userId;

    private ArrayList<User> cursorUserArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);


        userNameedt = findViewById(R.id.edituserName);
//        usersocietyIdedt = findViewById(R.id.editsocietyId);
        userPasswordedt = findViewById(R.id.editUserPassword);
        userEmailedt = findViewById(R.id.editEmailuser);
        updateCourseBtn = findViewById(R.id.idBtnUpdateCourse);
        deleteCourseBtn = findViewById(R.id.idBtnDelete);

        // on below line we are initialing our dbhandler class.
        dbHandler = new MYDB(this);

        // on below lines we are getting data which
        // we passed in our adapter class.
        userId = getIntent().getStringExtra("userid");
        userName = getIntent().getStringExtra("name");
        userEmail = getIntent().getStringExtra("email");
        userPassword = getIntent().getStringExtra("password");
        userSocietyId = getIntent().getStringExtra("societyid");

        // setting data to edit text
        // of our update activity

        userNameedt.setText(userName);
        userEmailedt.setText(userEmail);
//        usersocietyIdedt.setText(userSocietyId);
        userPasswordedt.setText(userPassword);



        updateCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                cursorUserArrayList =dbHandler.fetchidusersociety(userName,userPassword);

                // inside this method we are calling an update course
                // method and passing all our edit text values.
                dbHandler.updateUser(userName,userNameedt.getText().toString(), userEmailedt.getText().toString(),userPasswordedt.getText().toString(),cursorUserArrayList.get(0).getSocietyid(),cursorUserArrayList.get(0).getRegno());

                // displaying a toast message that our course has been updated.
                Toast.makeText(UpdateUserActivity.this, "User Updated..", Toast.LENGTH_SHORT).show();

                // launching our main activity.
                Intent i = new Intent(UpdateUserActivity.this, AdminPanel.class);
                startActivity(i);
            }
        });


        deleteCourseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calling a method to delete our course.
                dbHandler.deleteUser(userName);
                Toast.makeText(UpdateUserActivity.this, "Deleted the User", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UpdateUserActivity.this, AdminPanel.class);
                startActivity(i);
            }
        });

    }
}