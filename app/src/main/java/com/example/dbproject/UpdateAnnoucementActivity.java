package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.dbproject.DB.MYDB;

public class UpdateAnnoucementActivity extends AppCompatActivity {


    EditText title,message,stardate;
    Button updatebtn;
    MYDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_annoucement);

        DB = new MYDB(this);

        String titleint,messageint,startdateint;
        int annoucid;
        titleint = getIntent().getStringExtra("title");
        messageint = getIntent().getStringExtra("message");
        startdateint = getIntent().getStringExtra("startdate");
        annoucid =  Integer.parseInt( getIntent().getStringExtra("annoucementid"));

        title = (EditText) findViewById(R.id.annoucmentTitleEdt_update);
        updatebtn = (Button) findViewById(R.id.addBtnUpdateannouc);
        message = (EditText) findViewById(R.id.messageBox_update);
        stardate = (EditText) findViewById(R.id.startDateEdt_update);

        title.setText(titleint);
        message.setText(messageint);
        stardate.setText(startdateint);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DB.updateannoucement(title.getText().toString(),message.getText().toString(),stardate.getText().toString(),annoucid);



                Intent intent = new Intent(UpdateAnnoucementActivity.this,ViewAnnoucementAdmin.class);
                startActivity(intent);
            }
        });




    }
}