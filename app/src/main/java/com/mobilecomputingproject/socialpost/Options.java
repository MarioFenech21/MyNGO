package com.mobilecomputingproject.socialpost;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;

public class Options extends AppCompatActivity {

    //Class used for the functions of the elements found in the view options_view.xml
    private Switch darkModeSwitch;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private boolean nightMode;

    private static final String MODE_PREFS = "MODE_PREFS";
    private static final String NIGHT_MODE_KEY = "night_mode";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_view);

        darkModeSwitch = findViewById(R.id.darkModeSwitch);
        sharedPreferences = getSharedPreferences(MODE_PREFS, Context.MODE_PRIVATE);
        nightMode = sharedPreferences.getBoolean(NIGHT_MODE_KEY, false);

        if (nightMode) {
            darkModeSwitch.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        darkModeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Handle the Dark Mode toggle state change
                if (isChecked) {
                    // Dark Mode is enabled
                    enableDarkMode();
                } else {
                    // Dark Mode is disabled
                    disableDarkMode();
                }
            }
        });

        //Displaying the toolbar
        Toolbar toolbar = findViewById(R.id.user_toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setTitle("Settings");
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void disableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        editor = sharedPreferences.edit();
        editor.putBoolean(NIGHT_MODE_KEY, false);
        editor.apply();
        Toast.makeText(this, "Dark Mode disabled", Toast.LENGTH_SHORT).show();
    }

    private void enableDarkMode() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        editor = sharedPreferences.edit();
        editor.putBoolean(NIGHT_MODE_KEY, true);
        editor.apply();
        Toast.makeText(this, "Dark Mode enabled", Toast.LENGTH_SHORT).show();
    }
}
