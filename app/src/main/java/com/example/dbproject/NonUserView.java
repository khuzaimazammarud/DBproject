package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dbproject.Adapter.UserEventAdapter;
import com.example.dbproject.DB.MYDB;
import com.example.dbproject.model.userViewSociety;

import java.util.ArrayList;

public class NonUserView extends AppCompatActivity {


    private ArrayList<userViewSociety> cursorUserArrayList;
    private MYDB dbHandler;
    private UserEventAdapter courseRVAdapter;
    private RecyclerView coursesRV;
    Button signout;
    TextView setusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_user_view);

        String username;
        username = getIntent().getStringExtra("username");
        setusername = (TextView) findViewById(R.id.user_view_username);
        signout = (Button) findViewById(R.id.signout_user);


        setusername.setText(username);



        cursorUserArrayList = new ArrayList<>();
        dbHandler = new MYDB(this);

        // getting our course array
        // list from db handler class.
        cursorUserArrayList = dbHandler.readAllEvents();

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new UserEventAdapter(cursorUserArrayList, NonUserView.this);
        coursesRV = findViewById(R.id.recyclerview_userEvent);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(NonUserView.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(NonUserView.this,MainActivity.class);
                startActivity(i);
            }
        });


    }
}