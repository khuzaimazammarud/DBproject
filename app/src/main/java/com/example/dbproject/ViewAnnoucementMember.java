package com.example.dbproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.dbproject.Adapter.AnnoucementAdapter;
import com.example.dbproject.Adapter.AnnoucementAdapterMember;
import com.example.dbproject.DB.MYDB;
import com.example.dbproject.model.Annoucement;

import java.util.ArrayList;

public class ViewAnnoucementMember extends AppCompatActivity {


    private ArrayList<Annoucement> cursorUserArrayList_annouc;
    private AnnoucementAdapterMember courseRVAdapter_annouc;
    private RecyclerView coursesRV_annouc;
    MYDB DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_annoucement_member);

        DB = new MYDB(this);

        cursorUserArrayList_annouc = DB.readAllAnnoucement();

        // getting our course array
        // list from db handler class.


        // on below line passing our array lost to our adapter class.
        courseRVAdapter_annouc = new AnnoucementAdapterMember(cursorUserArrayList_annouc, ViewAnnoucementMember.this);
        coursesRV_annouc = findViewById(R.id.recyclerview_annouc);

        // setting layout manager for our recycler view.
        LinearLayoutManager linearLayoutManager_annouc = new LinearLayoutManager(ViewAnnoucementMember.this, RecyclerView.VERTICAL, false);
        coursesRV_annouc.setLayoutManager(linearLayoutManager_annouc);

        // setting our adapter to recycler view.
        coursesRV_annouc.setAdapter(courseRVAdapter_annouc);

    }
}