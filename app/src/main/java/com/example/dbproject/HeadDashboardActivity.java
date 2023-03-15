package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dbproject.Adapter.UserEventAdapter;
import com.example.dbproject.DB.MYDB;
import com.example.dbproject.model.userViewSociety;

import java.util.ArrayList;

public class HeadDashboardActivity extends AppCompatActivity {


    TextView headname;
    Button AddEvent , viewannoucment,viewmember;
    String intentheadname;
    MYDB DB;

    private ArrayList<userViewSociety> cursorUserArrayList;
    private MYDB dbHandler;
    private UserEventAdapter courseRVAdapter;
    private RecyclerView coursesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_dashboard);

        AddEvent = (Button) findViewById(R.id.addEventBtn);
        viewannoucment = findViewById(R.id.ViewAnnocBtn);
        viewmember = (Button) findViewById(R.id.addMemberBtn);

        int societyid;
        DB = new MYDB(this);

        headname = findViewById(R.id.user_view_headname);
        intentheadname = getIntent().getStringExtra("headname");
        societyid = DB.fetchidHead(intentheadname);
        headname.setText(intentheadname);



        //=================recyler view===========================//

        cursorUserArrayList = new ArrayList<>();
        dbHandler = new MYDB(this);

        // getting our course array
        // list from db handler class.
        cursorUserArrayList = dbHandler.readMemberEvents(societyid);

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new UserEventAdapter(cursorUserArrayList, HeadDashboardActivity.this);
        coursesRV = findViewById(R.id.recyclerview_userEvent);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(HeadDashboardActivity.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);


        AddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HeadDashboardActivity.this,addEvent.class);

                intent.putExtra("societyid",Integer.toString(societyid));

                startActivity(intent);

            }
        });

        viewannoucment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HeadDashboardActivity.this,ViewAnnoucementMember.class);
                startActivity(intent);
            }
        });

        viewmember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HeadDashboardActivity.this,ViewMemberActivity.class);

                intent.putExtra("societyid",Integer.toString(societyid));

                startActivity(intent);
            }
        });

    }
}