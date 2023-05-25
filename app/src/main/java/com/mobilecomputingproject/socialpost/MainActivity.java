package com.mobilecomputingproject.socialpost;

import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;
import android.net.Uri;


import com.google.android.material.bottomnavigation.BottomNavigationView;

//Class used for the functions of the elements found in the view activity_main.xml
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostAdapter postAdapter;
    private List<Post> postList;
    private BottomNavigationView bottomNavigationView;

    private MenuItem currentMenuItem;

    private static final String MODE_PREFS = "MODE_PREFS";
    private static final String NIGHT_MODE_KEY = "night_mode";



// Function used to initialise the RecyclerView
    private void initializeViews() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    //Function used to setup required views
    private void setupViews() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        postList = createPostList();
        postAdapter = new PostAdapter(postList);
        recyclerView.setAdapter(postAdapter);
    }

// Function used to create a list of social media posts
    private List<Post> createPostList() {
        List<Post> postList = new ArrayList<>();

        // Create and add Post objects to the list
        Post post1 = new Post("https://scontent.fmla1-1.fna.fbcdn.net/v/t39.30808-6/347434866_281428017652007_8459674350807706899_n.jpg?_nc_cat=107&ccb=1-7&_nc_sid=730e14&_nc_ohc=IQL_SxIExtAAX8XDJk7&_nc_ht=scontent.fmla1-1.fna&oh=00_AfAYr3jzigKsA_udg8HuA4pWT3HJL5vr5t79w2jkVbisFg&oe=647397C0", "Start of Summer BBQ Announced", "https://www.facebook.com/permalink.php?story_fbid=pfbid02djdK61Tch5XMaNZNjepspRqeYwLn9opwgC4Q2nJ3wu6WpsdrwvJkcdYsZJzDfC6Gl&id=100093066158565");
        Post post2 = new Post("https://scontent.fmla1-1.fna.fbcdn.net/v/t39.30808-6/347230594_577461367845948_5021630357408028323_n.jpg?stp=dst-jpg_p552x414&_nc_cat=102&ccb=1-7&_nc_sid=730e14&_nc_ohc=Kzkqv-db3t0AX9_En4J&_nc_ht=scontent.fmla1-1.fna&oh=00_AfC4CPJJReiQr6p-1De3LoHtMUwvyrTxT70htDkVQ6fzbQ&oe=647371A8", "Call out for new members", "https://www.facebook.com/permalink.php?story_fbid=pfbid0VAqABJ4zyRaBfwJG6ec9Hoouow1xhuHDdUj6FjiPcirKG2Pq6YtU9e2b5vh1svz8l&id=100093066158565");
        Post post3 = new Post("https://scontent.fmla1-2.fna.fbcdn.net/v/t39.30808-6/347251470_796812595390520_4221766451586127961_n.jpg?_nc_cat=111&ccb=1-7&_nc_sid=730e14&_nc_ohc=vRFcqr3vqXsAX-QZojA&_nc_ht=scontent.fmla1-2.fna&oh=00_AfA2yPyGAcB0L4CY4JDbx0R9HX9ssj6f7_eJOGytBq_wxg&oe=64734AB4","MyNGO wins prestigious award" ,"https://www.facebook.com/permalink.php?story_fbid=pfbid0LCSm7akuHrm2zbHKVatyS3cdqb4uSPGcR2Mc2eXxhmziqtyy3oSRJjrcBfj4HqFXl&id=100093066158565");

        postList.add(post1);
        postList.add(post2);
        postList.add(post3);

        return postList;
    }

   //Listener for the Join Us! button
    private void setupButtonClickListener() {
        Button postLinkButton = findViewById(R.id.post_link_button);
        postLinkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle post link button click
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://forms.gle/2cpmMaLSZ6vtJGdV7"));
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences(MODE_PREFS, Context.MODE_PRIVATE);
        boolean nightMode = sharedPreferences.getBoolean(NIGHT_MODE_KEY, false);

        if (nightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("channel_id", "Channel Name", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }



        setContentView(R.layout.activity_main);
        setupButtonClickListener();
        initializeViews();
        setupViews();

    //Setting up the Bottom Navigation View
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        currentMenuItem = bottomNavigationView.getMenu().findItem(R.id.action_home);
        currentMenuItem.setChecked(true);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    // Handle Home item click
                    currentMenuItem = item;
                    currentMenuItem.setChecked(true);
                    setContentView(R.layout.activity_main);
                    // Additional setup specific to the main view
                    initializeViews();
                    setupViews();
                    setupButtonClickListener();
                    BottomNavigationView updatedBottomNavigationView = findViewById(R.id.bottom_navigation);
                    updatedBottomNavigationView.setOnNavigationItemSelectedListener(this);
                    return true;
                } else if (item.getItemId() == R.id.action_about_us) {
                    currentMenuItem = item;
                    currentMenuItem.setChecked(true);
                    Intent intentAboutUs = new Intent(MainActivity.this, AboutUs.class);
                    startActivity(intentAboutUs);
                    finish();
                    BottomNavigationView updatedBottomNavigationView = findViewById(R.id.bottom_navigation);
                    updatedBottomNavigationView.setOnNavigationItemSelectedListener(this);
                    return true;
                } else if (item.getItemId() == R.id.action_menu) {
                    currentMenuItem = item;
                    currentMenuItem.setChecked(true);
                    Intent intentUserInfo = new Intent(MainActivity.this, Menu.class);
                    startActivity(intentUserInfo);
                    finish();
                    BottomNavigationView updatedBottomNavigationView = findViewById(R.id.bottom_navigation);
                    updatedBottomNavigationView.setOnNavigationItemSelectedListener(this);
                    return true;
                } else if (item.getItemId() == R.id.action_events) {
                    currentMenuItem = item;
                    currentMenuItem.setChecked(true);
                    Intent intentUserInfo = new Intent(MainActivity.this, Events.class);
                    startActivity(intentUserInfo);
                    finish();
                    BottomNavigationView updatedBottomNavigationView = findViewById(R.id.bottom_navigation);
                    updatedBottomNavigationView.setOnNavigationItemSelectedListener(this);
                    return true;
                }
                // Add conditions for other menu items if needed
                return false;
            }
        });
    }
}
