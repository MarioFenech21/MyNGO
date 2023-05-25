package com.mobilecomputingproject.socialpost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class Events extends AppCompatActivity {

    //Class used for the functions of the elements found in the view events_page.xml

    private Context context;
    private List<Event> eventList;
    private RecyclerView recyclerView;
    private EventsAdapter eventsAdapter;

    private TextView timerTextView;

    private BottomNavigationView bottomNavigationView;

    private MenuItem currentMenuItem;

    //Function that initialises the Bottom navigation View
    private void initializeViews() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        currentMenuItem = bottomNavigationView.getMenu().findItem(R.id.action_events);
        currentMenuItem.setChecked(true);
        bottomNavigationView.getMenu().findItem(R.id.action_events).setChecked(true);
    }

    //Function that creates the list of events to be displayed
    private List<Event> createEventsList() {
        List<Event> eventList = new ArrayList<>();

        // Create and add Event objects to the list
        Event event1 = new Event("Start of Summer BBQ", "Friday 2nd June 2023 8pm", "https://calendar.google.com/calendar/event?action=TEMPLATE&tmeid=MnRtMW9wNWt0YXBwNnRvMmZrbzJsNHVhdW0gY19kMTNjMTYwNmU3ZGJkOWExNWJkMjhjMzQ0NmUxODQ5MzhiY2U2ODhmZTI5MjA3YTM2NzIzY2YyMGFhNTUzNDk1QGc&tmsrc=c_d13c1606e7dbd9a15bd28c3446e184938bce688fe29207a36723cf20aa553495%40group.calendar.google.com");
        Event event2 = new Event("Charity Car Wash", "Saturday 10th June 2023 10am", "https://calendar.google.com/calendar/event?action=TEMPLATE&tmeid=MG1vM2Y2amExNmZjbm1iMjVncW0xMmQ0bG8gY19kMTNjMTYwNmU3ZGJkOWExNWJkMjhjMzQ0NmUxODQ5MzhiY2U2ODhmZTI5MjA3YTM2NzIzY2YyMGFhNTUzNDk1QGc&tmsrc=c_d13c1606e7dbd9a15bd28c3446e184938bce688fe29207a36723cf20aa553495%40group.calendar.google.com");
        Event event3 = new Event("Members Workshop", "Friday 30th June 2023 6pm", "https://calendar.google.com/calendar/event?action=TEMPLATE&tmeid=NnN2MHB0dmdtNmU1amFjN2Y4dmhoZXJudXUgY19kMTNjMTYwNmU3ZGJkOWExNWJkMjhjMzQ0NmUxODQ5MzhiY2U2ODhmZTI5MjA3YTM2NzIzY2YyMGFhNTUzNDk1QGc&tmsrc=c_d13c1606e7dbd9a15bd28c3446e184938bce688fe29207a36723cf20aa553495%40group.calendar.google.com");
        Event event4 = new Event("MyNGO 12th Anniversary Dinner", "Wednesday 12th July 2023 8pm", "https://calendar.google.com/calendar/event?action=TEMPLATE&tmeid=NnQ4cDJmZ3Y4aW40ajlwb3U1cDBmYmlkZTcgY19kMTNjMTYwNmU3ZGJkOWExNWJkMjhjMzQ0NmUxODQ5MzhiY2U2ODhmZTI5MjA3YTM2NzIzY2YyMGFhNTUzNDk1QGc&tmsrc=c_d13c1606e7dbd9a15bd28c3446e184938bce688fe29207a36723cf20aa553495%40group.calendar.google.com");

        // Add more events as needed

        eventList.add(event1);
        eventList.add(event2);
        eventList.add(event3);
        eventList.add(event4);

        return eventList;
    }


    //Function that sets up all the views required in the Events view
    private void setupViews() {
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            eventList = createEventsList();
            eventsAdapter = new EventsAdapter(eventList, this);
            recyclerView.setAdapter(eventsAdapter);

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.events_page);

        initializeViews();
        setupBottomNavigation();

        recyclerView = findViewById(R.id.eventsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setupViews();

        timerTextView = findViewById(R.id.timerTextView);

        Calendar targetDate = Calendar.getInstance();
        targetDate.set(Calendar.YEAR, 2023);
        targetDate.set(Calendar.MONTH, Calendar.JUNE);
        targetDate.set(Calendar.DAY_OF_MONTH, 2);
        targetDate.set(Calendar.HOUR_OF_DAY, 20);
        targetDate.set(Calendar.MINUTE, 0);
        targetDate.set(Calendar.SECOND, 0);

        long currentTime = System.currentTimeMillis();
        long remainingTime = targetDate.getTimeInMillis() - currentTime;

        // Start the countdown timer
        CountDownTimer countDownTimer = new CountDownTimer(remainingTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                long minutes = seconds / 60;
                long hours = minutes / 60;
                long days = hours / 24;

                String timerText = String.format(Locale.getDefault(), "%02d Days %02d Hours %02d Minutes %02d Seconds", days, hours % 24, minutes % 60, seconds % 60);
                timerTextView.setText(timerText);
            }

            @Override
            public void onFinish() {
                timerTextView.setText("Event has started");
            }
        };

        countDownTimer.start();
    }



    //Function sets up the Bottom Navigation View
    private void setupBottomNavigation() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    // Handle Home item click
                    Intent intentMain = new Intent(Events.this, MainActivity.class);
                    startActivity(intentMain);
                    finish();
                    BottomNavigationView updatedBottomNavigationView = findViewById(R.id.bottom_navigation);
                    updatedBottomNavigationView.setOnNavigationItemSelectedListener(this);
                    return true;
                } else if (item.getItemId() == R.id.action_about_us) {
                    Intent intentUserInfo = new Intent(Events.this, AboutUs.class);
                    startActivity(intentUserInfo);
                    finish();
                    BottomNavigationView updatedBottomNavigationView = findViewById(R.id.bottom_navigation);
                    updatedBottomNavigationView.setOnNavigationItemSelectedListener(this);
                    return true;
                } else if (item.getItemId() == R.id.action_menu) {
                    Intent intentUserInfo = new Intent(Events.this, Menu.class);
                    startActivity(intentUserInfo);
                    finish();
                    BottomNavigationView updatedBottomNavigationView = findViewById(R.id.bottom_navigation);
                    updatedBottomNavigationView.setOnNavigationItemSelectedListener(this);
                    return true;
                } else if (item.getItemId() == R.id.action_events) {
                    // Handle Events item click
                    return true;
                }
                // Add conditions for other menu items if needed
                return false;
            }
        });
    }


}