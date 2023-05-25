package com.mobilecomputingproject.socialpost;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;

//Class used for the functions of the elements found in the view about_us.xml
public class AboutUs extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private MenuItem currentMenuItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us);

        initializeViews();
        setupBottomNavigation();
    }

    //Function initialises the Bottom Navigation View
    private void initializeViews() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        currentMenuItem = bottomNavigationView.getMenu().findItem(R.id.action_about_us);
        currentMenuItem.setChecked(true);
        bottomNavigationView.getMenu().findItem(R.id.action_about_us).setChecked(true);


    }

    //Function sets up the Bottom Navigation View
    private void setupBottomNavigation() {

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    // Handle Home item click
                    Intent intentMain = new Intent(AboutUs.this, MainActivity.class);
                    startActivity(intentMain);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.action_about_us) {
                    // Handle About Us item click
                    return true;
                } else if (item.getItemId() == R.id.action_menu) {
                    Intent intentUserInfo = new Intent(AboutUs.this, Menu.class);
                    startActivity(intentUserInfo);
                    finish();
                    return true;
                } else if (item.getItemId() == R.id.action_events) {
                    Intent intentUserInfo = new Intent(AboutUs.this, Events.class);
                    startActivity(intentUserInfo);
                    finish();
                    return true;
                }
                // Add conditions for other menu items if needed
                return false;
            }
        });
    }
}
