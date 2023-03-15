package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dbproject.Adapter.SocietyAdapter;
import com.example.dbproject.Adapter.ViewMemberAdapter;
import com.example.dbproject.DB.MYDB;
import com.example.dbproject.model.Society;
import com.example.dbproject.model.societyMember;

import java.util.ArrayList;

public class ViewMemberActivity extends AppCompatActivity {

    private ArrayList<societyMember> cursorUserArrayList_society;
    private MYDB dbHandler_society;
    private ViewMemberAdapter courseRVAdapter_society;
    private RecyclerView coursesRV_society;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_member);


        int societyid = Integer.parseInt( getIntent().getStringExtra("societyid"));

        cursorUserArrayList_society = new ArrayList<>();
        dbHandler_society = new MYDB(this);

        // getting our course array
        // list from db handler class.
        cursorUserArrayList_society = dbHandler_society.fetchMember(societyid);

        // on below line passing our array lost to our adapter class.
        courseRVAdapter_society = new ViewMemberAdapter(cursorUserArrayList_society, ViewMemberActivity.this);
        coursesRV_society = findViewById(R.id.recyclerview_memberlist);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager_society = new LinearLayoutManager(ViewMemberActivity.this, RecyclerView.VERTICAL, false);
        coursesRV_society.setLayoutManager(linearLayoutManager_society);

        // setting our adapter to recycler view.
        coursesRV_society.setAdapter(courseRVAdapter_society);
    }
}