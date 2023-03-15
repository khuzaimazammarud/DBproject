package com.example.dbproject.utils;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dbproject.Adapter.UserAdapter;
import com.example.dbproject.DB.MYDB;
import com.example.dbproject.R;
import com.example.dbproject.model.User;

import java.util.ArrayList;

public class Test extends AppCompatActivity {

    private ArrayList<User> cursorUserArrayList;
    private MYDB dbHandler;
    private UserAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        cursorUserArrayList = new ArrayList<>();
        dbHandler = new MYDB(this);

        // getting our course array
        // list from db handler class.
        cursorUserArrayList = dbHandler.readUser();

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new UserAdapter(cursorUserArrayList, Test.this);
        coursesRV = findViewById(R.id.idRVCourses);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Test.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);

    }
}