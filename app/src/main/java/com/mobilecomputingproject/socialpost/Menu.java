package com.mobilecomputingproject.socialpost;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Menu extends AppCompatActivity {

    //Class used for the functions of the elements found in the view menu_view.xml
    private BottomNavigationView bottomNavigationView;
    private MenuItem currentMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_view);

        initializeViews();
        setupBottomNavigation();

        // Find the buttons by their IDs
        Button accountButton = findViewById(R.id.accountButton);
        Button websiteButton = findViewById(R.id.websiteButton);
        Button settingsButton = findViewById(R.id.settingsButton);

        // Set click listeners for the buttons
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToAccountDetails();
            }
        });

        websiteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToWebsite();
            }
        });

        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToSettings();
            }
        });
    }

    // Method to handle the account button click
    public void redirectToAccountDetails() {
        // Start the AccountDetailsActivity
        Intent intent = new Intent(this, UserInfo.class);
        startActivity(intent);
    }

    // Method to handle the website button click
    public void redirectToWebsite() {
        // Open a website in the browser
        String websiteUrl = "https://mariofenech21.wixsite.com/myngo";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(websiteUrl));
        startActivity(intent);
    }

    // Method to handle the settings button click
    public void redirectToSettings() {
        // Start the SettingsActivity
        Intent intent = new Intent(this, Options.class);
        startActivity(intent);
    }

    private void initializeViews() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        currentMenuItem = bottomNavigationView.getMenu().findItem(R.id.action_menu);
        currentMenuItem.setChecked(true);
        bottomNavigationView.getMenu().findItem(R.id.action_menu).setChecked(true);


    }

    //Function sets up the Bottom Navigation View

    private void setupBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.action_home) {
                    // Handle Home item click
                    Intent intentMain = new Intent(Menu.this, MainActivity.class);
                    startActivity(intentMain);
                    finish();
                    BottomNavigationView updatedBottomNavigationView = findViewById(R.id.bottom_navigation);
                    updatedBottomNavigationView.setOnNavigationItemSelectedListener(this);
                    return true;
                } else if (item.getItemId() == R.id.action_about_us) {
                    // Handle About Us item click
                    Intent intentMain = new Intent(Menu.this, AboutUs.class);
                    startActivity(intentMain);
                    finish();
                    BottomNavigationView updatedBottomNavigationView = findViewById(R.id.bottom_navigation);
                    updatedBottomNavigationView.setOnNavigationItemSelectedListener(this);
                    return true;
                } else if (item.getItemId() == R.id.action_menu) {
                    return true;
                } else if (item.getItemId() == R.id.action_events) {
                    Intent intentUserInfo = new Intent(Menu.this, Events.class);
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
