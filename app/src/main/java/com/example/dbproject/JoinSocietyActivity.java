package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbproject.DB.MYDB;
import com.example.dbproject.model.User;
import com.example.dbproject.model.member;

import java.util.ArrayList;

public class JoinSocietyActivity extends AppCompatActivity {

    EditText username,userage,userregno,societyid;
    MYDB DB;
    Button joinBtn,ViewAnnoucementBtn;
    private ArrayList<member> cursorUserArrayList_member;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_society);

        String name,pass;
        name = getIntent().getStringExtra("username");
        pass = getIntent().getStringExtra("password");
        joinBtn = findViewById(R.id.JoinBtnJoin);

        DB = new MYDB(this);

        username = findViewById(R.id.memberNameEdtJoin);
        userage = findViewById(R.id.ageEdtjoin);
        userregno = findViewById(R.id.registrationNumberEdtJoin);
        societyid = findViewById(R.id.societyIdEdtJoin);

        cursorUserArrayList_member = DB.readOnemember(name,pass);

        username.setText(cursorUserArrayList_member.get(0).getName());
        userregno.setText(cursorUserArrayList_member.get(0).getUserregno());


        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DB.joinSociety(username.getText().toString(),cursorUserArrayList_member.get(0).getEmail(),cursorUserArrayList_member.get(0).getPassword(),Integer.parseInt(userage.getText().toString()),userregno.getText().toString(),Integer.parseInt(societyid.getText().toString()),cursorUserArrayList_member.get(0).getUser_id());
                Toast.makeText(JoinSocietyActivity.this,"Congragualation You Are Added In Society",Toast.LENGTH_SHORT).show();

            }
        });



    }
}