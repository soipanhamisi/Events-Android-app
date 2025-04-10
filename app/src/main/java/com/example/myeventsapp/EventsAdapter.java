package com.example.myeventsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventsViewHolder> {

    Context context;
    ArrayList<events> list;

    public EventsAdapter(Context context, ArrayList<events> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public EventsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.events_entry, null, false);
        return new EventsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EventsViewHolder holder, int position) {
        events event = list.get(position);
        holder.EventName.setText(event.getEventName());
        holder.EventDescription.setText(event.getEventDescription());
        holder.venue.setText(event.getVenue());
        holder.time.setText(event.getTime());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class EventsViewHolder extends RecyclerView.ViewHolder{
        TextView EventName, EventDescription, venue, time;
        public EventsViewHolder(@NonNull View itemView) {
            super(itemView);
            EventName = itemView.findViewById(R.id.EventName);
            EventDescription = itemView.findViewById(R.id.EventDescription);
            venue = itemView.findViewById(R.id.eventVenue);
            time = itemView.findViewById(R.id.eventTime);
        }

    }
}
