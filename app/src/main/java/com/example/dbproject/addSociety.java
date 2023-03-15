package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbproject.DB.MYDB;

public class addSociety extends AppCompatActivity {


    Button AddButton;
    EditText SocietyName;
    EditText Type;
    MYDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_society);

        AddButton = (Button) findViewById(R.id.addButton);
        SocietyName = (EditText) findViewById(R.id.societyname);
        Type = (EditText) findViewById(R.id.societyType);

        DB = new MYDB(this);

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String societyname=SocietyName.getText().toString();

                if(societyname.isEmpty())
                {
                    Toast.makeText(addSociety.this, "Field Is Empty....", Toast.LENGTH_SHORT).show();
                }
                else if(!societyname.isEmpty())
                {

                    if(DB.checkSociety(societyname))
                    {
                        Toast.makeText(addSociety.this, "Society already exist..", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        DB.addSociety(societyname);
                        Toast.makeText(addSociety.this, "Society Added..", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent(addSociety.this, AdminPanel.class);
                        startActivity(i);
                    }

                }


            }
        });


    }
}