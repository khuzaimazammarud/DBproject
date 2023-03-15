package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.example.dbproject.Adapter.AnnoucementAdapter;
import com.example.dbproject.DB.MYDB;
import com.example.dbproject.model.Annoucement;

import java.util.ArrayList;

public class ViewAnnoucementAdmin extends AppCompatActivity {


    private ArrayList<Annoucement> cursorUserArrayList_annouc;
    private AnnoucementAdapter courseRVAdapter_annouc;
    private RecyclerView coursesRV_annouc;
    MYDB DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_annoucement_admin);

        DB = new MYDB(this);

        cursorUserArrayList_annouc = DB.readAllAnnoucement();

        // getting our course array
        // list from db handler class.


        // on below line passing our array lost to our adapter class.
        courseRVAdapter_annouc = new AnnoucementAdapter(cursorUserArrayList_annouc, ViewAnnoucementAdmin.this);
        coursesRV_annouc = findViewById(R.id.recyclerview_annouc);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager_annouc = new LinearLayoutManager(ViewAnnoucementAdmin.this, RecyclerView.VERTICAL, false);
        coursesRV_annouc.setLayoutManager(linearLayoutManager_annouc);

        // setting our adapter to recycler view.
        coursesRV_annouc.setAdapter(courseRVAdapter_annouc);

    }
}