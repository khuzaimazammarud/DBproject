package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbproject.DB.MYDB;

public class AddAnnoucementActivity extends AppCompatActivity {


    EditText annoucementTitle,annoucementDescribtion,annoucementstartdate;
    Button AddBtn;
    MYDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_annoucement);

        DB = new MYDB(this);
        annoucementTitle = findViewById(R.id.annoucmentTitleEdt);
        annoucementDescribtion = findViewById(R.id.messageBox);
        annoucementstartdate = findViewById(R.id.startDateEdt);
        AddBtn = findViewById(R.id.addBtnA);

        AddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DB.addannoucement(annoucementTitle.getText().toString(),annoucementDescribtion.getText().toString(),annoucementstartdate.getText().toString());
                Toast.makeText(AddAnnoucementActivity.this, "Annoucement Added..", Toast.LENGTH_SHORT).show();

            }
        });





    }
}