package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dbproject.Adapter.MyAdapter;
import com.example.dbproject.DB.MYDB;
import com.example.dbproject.model.User;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

//    RecyclerView recyclerView;
//    ArrayList<String> name, email;
//    MYDB DB;
//    MyAdapter adapter;

    MYDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

//        DB = new MYDB(this);
//        name = new ArrayList<>();
//        email = new ArrayList<>();
//        recyclerView = findViewById(R.id.recyclerview);
//        adapter = new MyAdapter(this, name, email);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        displaydata();
//    }
//
//    private void displaydata()
//    {
//        Cursor cursor = DB.getdata();
//        if(cursor.getCount()==0)
//        {
//            Toast.makeText(Home.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
//            return;
//        }
//        else
//        {
//            while(cursor.moveToNext())
//            {
//                name.add(cursor.getString(1));
//                email.add(cursor.getString(2));
//            }
//        }











    }
}
