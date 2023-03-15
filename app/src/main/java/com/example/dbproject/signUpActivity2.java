package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dbproject.DB.MYDB;
import com.example.dbproject.model.User;

import kotlin.jvm.internal.Intrinsics;

public class signUpActivity2 extends AppCompatActivity {

    EditText username,password,email,regnumber;
    Button signup;
    MYDB DB;
    User users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        regnumber = (EditText) findViewById(R.id.RegistrationEdt);

        DB = new MYDB(this);
        users = new User();
        signup = (Button) findViewById(R.id.signupButton);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                String user = username.getText().toString();
                String pass = password.getText().toString();
                String eml = email.getText().toString();

                if(user.isEmpty() || pass.isEmpty() || eml.isEmpty())
                {

                    Toast.makeText(signUpActivity2.this, "Filed is Emplty", Toast.LENGTH_SHORT).show();

                }
                else if(!user.isEmpty() && !pass.isEmpty() && !eml.isEmpty()) {

                    if(DB.checkuser(user)){

                        Toast.makeText(signUpActivity2.this, "User already Exists...", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        int userid;

                        DB.addUser(user,eml,pass,regnumber.getText().toString());

                        Intent i = new Intent(signUpActivity2.this,MainActivity.class);
                        startActivity(i);
                    }
                }
            }

        });



    }
}