package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dbproject.DB.MYDB;
import com.example.dbproject.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText username,password;
    Button Login;
    TextView logintext;
    TextView singup;
    MYDB DB;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        Login = (Button) findViewById(R.id.loginButton);
        singup = (TextView) findViewById(R.id.signupText);
        logintext = (TextView) findViewById(R.id.loginText);
        DB = new MYDB(this);

//        List<User> userList=DB.getAllUsers();
//
//        for(User alluser:userList){
//
//            Log.d("khzuema",alluser.getName());
//        }


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("") && pass.equals("")){
                    Intent intent = new Intent(MainActivity.this,AdminPanel.class);
                    startActivity(intent);
                }
                else if(DB.checkuserpass(user,pass))
                {

                    if(DB.checkhead(user))
                    {
                        Intent intent = new Intent(MainActivity.this,HeadDashboardActivity.class);
                        intent.putExtra("headname",user);
                        startActivity(intent);
                    }
                    else if(DB.checkNonMemberView(user,pass))
                    {
                        Intent intent = new Intent(MainActivity.this,NonMemberView.class);
                        intent.putExtra("username",user);
                        intent.putExtra("password",pass);
                        startActivity(intent);
                    }
                    else{
                        Intent intent = new Intent(MainActivity.this,UserViewActivity.class);
                        intent.putExtra("username",user);
                        intent.putExtra("password",pass);
                        startActivity(intent);
                    }

                }else
                {
                    Toast.makeText(MainActivity.this,"Wrong credential",Toast.LENGTH_SHORT).show();
                }
            }
        });

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,signUpActivity2.class);
                startActivity(intent);
            }
        });


    }
}