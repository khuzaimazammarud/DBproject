package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dbproject.Adapter.NonMemberAdapter;
import com.example.dbproject.Adapter.SocietyAdapter;
import com.example.dbproject.DB.MYDB;
import com.example.dbproject.model.Society;

import java.util.ArrayList;

public class NonMemberView extends AppCompatActivity {

    TextView username;
    Button joinSociety,Viewannoucement;

    private ArrayList<Society> cursorUserArrayList_society;
    private MYDB dbHandler_society;
    private NonMemberAdapter courseRVAdapter_society;
    private RecyclerView coursesRV_society;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_non_member_view);

        username = findViewById(R.id.user_view_nonmember);
        joinSociety = findViewById(R.id.joinSocietyBtn);
        Viewannoucement = findViewById(R.id.ViewAnnoucementBtnnon);
        String name,pass;
        name = getIntent().getStringExtra("username");
        pass = getIntent().getStringExtra("password");
        username.setText(name);



        cursorUserArrayList_society = new ArrayList<>();
        dbHandler_society = new MYDB(this);

        // getting our course array
        // list from db handler class.
        cursorUserArrayList_society = dbHandler_society.readSociety();

        // on below line passing our array lost to our adapter class.
        courseRVAdapter_society = new NonMemberAdapter(cursorUserArrayList_society, NonMemberView.this);
        coursesRV_society = findViewById(R.id.recyclerview_nonsociety);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager_society = new LinearLayoutManager(NonMemberView.this, RecyclerView.VERTICAL, false);
        coursesRV_society.setLayoutManager(linearLayoutManager_society);

        // setting our adapter to recycler view.
        coursesRV_society.setAdapter(courseRVAdapter_society);


        joinSociety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(NonMemberView.this,JoinSocietyActivity.class);
                intent.putExtra("username",name);
                intent.putExtra("password",pass);
                startActivity(intent);

            }
        });

        Viewannoucement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(NonMemberView.this,ViewAnnoucementMember.class);
                startActivity(intent);

            }
        });

    }
}