package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dbproject.Adapter.EventAdapter;
import com.example.dbproject.Adapter.SocietyAdapter;
import com.example.dbproject.Adapter.UserAdapter;
import com.example.dbproject.DB.MYDB;
import com.example.dbproject.model.Event;
import com.example.dbproject.model.Society;
import com.example.dbproject.model.User;
import com.example.dbproject.utils.Test;

import java.util.ArrayList;

public class AdminPanel extends AppCompatActivity {


    TextView addsociety,btnViewEvents;
    TextView addevent,assignSociety,readuser;
    Button signout,addannouc,viewannouc;

    //==society view Recyler View===//

    private ArrayList<Society> cursorUserArrayList_society;
    private MYDB dbHandler_society;
    private SocietyAdapter courseRVAdapter_society;
    private RecyclerView coursesRV_society;

    ///====users=====//

    private ArrayList<User> cursorUserArrayList_user;
    private MYDB dbHandler_user;
    private UserAdapter courseRVAdapter_user;
    private RecyclerView coursesRV_user;


    //===event View recyler View====/

    private ArrayList<Event> cursorUserArrayList;
    private MYDB dbHandler;
    private EventAdapter courseRVAdapter;
    private RecyclerView coursesRV;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        addsociety = (TextView) findViewById(R.id.addSocietyApp);

        readuser = (TextView) findViewById(R.id.readUser);

        signout = (Button) findViewById(R.id.signout_admin);

        btnViewEvents = (TextView) findViewById(R.id.btnViewEvent);

        addannouc = (Button) findViewById(R.id.addannouBTN);
        viewannouc = (Button) findViewById(R.id.viewAnnoucBtnAdmin);

        //====recyclerView for Soceity===========///


        cursorUserArrayList_society = new ArrayList<>();
        dbHandler_society = new MYDB(this);

        // getting our course array
        // list from db handler class.
        cursorUserArrayList_society = dbHandler_society.readSociety();

        // on below line passing our array lost to our adapter class.
        courseRVAdapter_society = new SocietyAdapter(cursorUserArrayList_society, AdminPanel.this);
        coursesRV_society = findViewById(R.id.recyclerview_society);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager_society = new LinearLayoutManager(AdminPanel.this, RecyclerView.VERTICAL, false);
        coursesRV_society.setLayoutManager(linearLayoutManager_society);

        // setting our adapter to recycler view.
        coursesRV_society.setAdapter(courseRVAdapter_society);


        //====recyclerView for Event===========///


        cursorUserArrayList = new ArrayList<>();
        dbHandler = new MYDB(this);

        // getting our course array
        // list from db handler class.
        cursorUserArrayList = dbHandler.readEvent();

        // on below line passing our array lost to our adapter class.
        courseRVAdapter = new EventAdapter(cursorUserArrayList, AdminPanel.this);
        coursesRV = findViewById(R.id.recyclerview_Event);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AdminPanel.this, RecyclerView.VERTICAL, false);
        coursesRV.setLayoutManager(linearLayoutManager);

        // setting our adapter to recycler view.
        coursesRV.setAdapter(courseRVAdapter);



        //===recyler view for users====/


        cursorUserArrayList_user = new ArrayList<>();
        dbHandler_user = new MYDB(this);

        // getting our course array
        // list from db handler class.
        cursorUserArrayList_user = dbHandler_user.readUser();

        // on below line passing our array lost to our adapter class.
        courseRVAdapter_user = new UserAdapter(cursorUserArrayList_user, AdminPanel.this);
        coursesRV_user = findViewById(R.id.recyclerview_user);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager_user = new LinearLayoutManager(AdminPanel.this, RecyclerView.VERTICAL, false);
        coursesRV_user.setLayoutManager(linearLayoutManager_user);

        // setting our adapter to recycler view.
        coursesRV_user.setAdapter(courseRVAdapter_user);



        //===button listeners========/

        addsociety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(AdminPanel.this,addSociety.class);
                startActivity(intent);

            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AdminPanel.this,MainActivity.class);
                startActivity(i);
            }
        });

        readuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(AdminPanel.this, Test.class);
                startActivity(i);
            }
        });

        btnViewEvents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(AdminPanel.this, NonUserView.class);
                i.putExtra("username","Admin");
                startActivity(i);

            }
        });

        addannouc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanel.this,AddAnnoucementActivity.class);
                startActivity(intent);
            }
        });

        viewannouc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminPanel.this,ViewAnnoucementAdmin.class);
                startActivity(intent);
            }
        });

    }
}