package com.example.dbproject.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dbproject.R;
import com.example.dbproject.UpdateEvent;
import com.example.dbproject.UpdateUserActivity;
import com.example.dbproject.model.User;
import com.example.dbproject.model.userViewSociety;

import java.util.ArrayList;

public class UserEventAdapter extends RecyclerView.Adapter<UserEventAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<userViewSociety> cursorUserArrayList;
    private Context context;

    // constructor
    public UserEventAdapter(ArrayList<userViewSociety> cursorUserArrayList, Context context) {
        this.cursorUserArrayList = cursorUserArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.usereventlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        userViewSociety modal = cursorUserArrayList.get(position);
        holder.userName.setText(modal.getSocietyname());
        holder.userEmail.setText(modal.getEventname());
        holder.userPassword.setText(modal.getStartdate());
        holder.userSocietyId.setText(modal.getEnddate());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateEvent.class);

                // below we are passing all our values.
                i.putExtra("eventname", modal.getEventname());
                i.putExtra("startdate", modal.getStartdate());
                i.putExtra("enddate", modal.getEnddate());
                i.putExtra("societyid", Integer.toString(modal.getSocietyid()));

                // starting our activity.
                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return cursorUserArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView userName, userEmail, userPassword, userSocietyId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            userName = itemView.findViewById(R.id.societyNameDy_userevent);
            userEmail = itemView.findViewById(R.id.eventNameDy_userevent);
            userPassword = itemView.findViewById(R.id.venueEdt);
            userSocietyId = itemView.findViewById(R.id.startDateDy_userevent);
        }
    }
}
