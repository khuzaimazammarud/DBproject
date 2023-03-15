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
import com.example.dbproject.model.Event;
import com.example.dbproject.model.societyMember;

import java.util.ArrayList;

public class ViewMemberAdapter extends RecyclerView.Adapter<ViewMemberAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<societyMember> cursorUserArrayList;
    private Context context;

    // constructor
    public ViewMemberAdapter(ArrayList<societyMember> cursorUserArrayList, Context context) {
        this.cursorUserArrayList = cursorUserArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        societyMember modal = cursorUserArrayList.get(position);
        holder.Name.setText(modal.getMemberName());
        holder.email.setText(modal.getMemberemail());
        holder.age.setText(modal.getMemberage());
        holder.regno.setText(modal.getMemberregno());

    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return cursorUserArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView Name, email, age, regno;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            Name = itemView.findViewById(R.id.memberlist_name);
            email = itemView.findViewById(R.id.memberlist_email);
            age = itemView.findViewById(R.id.memberlist_age);
            regno = itemView.findViewById(R.id.memberlist_regno);
        }
    }
}