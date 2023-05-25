package com.mobilecomputingproject.socialpost;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.EventViewHolder> {

    //Class in charge of the RecyclerView in the Events view
    private final List<Event> eventsList;
    private Context context;


    public EventsAdapter(List<Event> eventsList) {
        this.eventsList = eventsList;
    }

    public EventsAdapter(List<Event> eventsList, Context context) {
        this.eventsList = eventsList;
        this.context = context;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.event, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        Event event = eventsList.get(position);
        holder.eventTitleTextView.setText(event.getTitle());
        holder.eventTimeTextView.setText(event.getTime());

        if (holder.eventDetailsButton != null) {
            holder.eventDetailsButton.setOnClickListener(v -> {
                // Open the post link in a browser
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(event.getLink()));
                v.getContext().startActivity(intent);
            });
        }

    }


    @Override
    public int getItemCount() {
        if (eventsList != null) {
            return eventsList.size();
        } else {
            return 0;
        }
    }

    public class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventTitleTextView;
        TextView eventTimeTextView;

        Button eventDetailsButton;


        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            eventTitleTextView = itemView.findViewById(R.id.eventTitleTextView);
            eventTimeTextView = itemView.findViewById(R.id.eventTimeTextView);
            eventDetailsButton = itemView.findViewById(R.id.eventsDetailsButton);
        }
    }
}
