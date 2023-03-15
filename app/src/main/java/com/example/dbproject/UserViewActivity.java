package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dbproject.Adapter.UserAdapter;
import com.example.dbproject.Adapter.UserEventAdapter;
import com.example.dbproject.DB.MYDB;
import com.example.dbproject.model.User;
import com.example.dbproject.model.userViewSociety;

import java.util.ArrayList;

public class UserViewActivity extends AppCompatActivity {


    TextView user_view_societyName,user_view_username;
//    MYDB DB;
    Button signout;


    private ArrayList<userViewSociety> cursorUserArrayList;
    private MYDB dbHandler;
    private UserEventAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        user_view_username = (TextView) findViewById(R.id.user_view_username);
        user_view_societyName = (TextView) findViewById(R.id.user_view_societyname);
        signout = (Button) findViewById(R.id.signout_user);

        String username,password;

        username = getIntent().getStringExtra("username");
        password = getIntent().getStringExtra("password");

        user_view_username.setText(username);
        user_view_societyName.setText("Events");


        cursorUserArrayList = new ArrayList<>();
        dbHandler = new MYDB(this);

        // getting our course array
        // list from db handler class.
        cursorUserArrayList = dbHandler.readUserEvents(username,password);

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new UserEventAdapter(cursorUserArrayList, UserViewActivity.this);
        coursesRV = findViewById(R.id.recyclerview_userEvent);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(UserViewActivity.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(UserViewActivity.this,MainActivity.class);
                startActivity(i);
            }
        });

    }
}