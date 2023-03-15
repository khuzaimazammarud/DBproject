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
import com.example.dbproject.model.Event;
import com.example.dbproject.model.User;

import java.util.ArrayList;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<Event> cursorUserArrayList;
    private Context context;

    // constructor
    public EventAdapter(ArrayList<Event> cursorUserArrayList, Context context) {
        this.cursorUserArrayList = cursorUserArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        Event modal = cursorUserArrayList.get(position);
        holder.eventName.setText(modal.getEventName());
        holder.startDate.setText(modal.getStartDate());
        holder.endDate.setText(modal.getEndDate());
        holder.societyId.setText(Integer.toString(modal.getSocietyId()));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateEvent.class);

                // below we are passing all our values.
                i.putExtra("eventname", modal.getEventName());
                i.putExtra("startdate", modal.getStartDate());
                i.putExtra("enddate", modal.getEndDate());
                i.putExtra("societyid", Integer.toString(modal.getSocietyId()));

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
        private TextView eventName, startDate, endDate, societyId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            eventName = itemView.findViewById(R.id.eventNameDy);
            startDate = itemView.findViewById(R.id.startDateDy);
            endDate = itemView.findViewById(R.id.endDateDy);
            societyId = itemView.findViewById(R.id.societyIdDy_event);
        }
    }
}
