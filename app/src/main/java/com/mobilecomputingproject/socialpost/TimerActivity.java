package com.mobilecomputingproject.socialpost;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Locale;

public class TimerActivity extends AppCompatActivity {

    //Class used to create the countdown timer function found in events_page.xml

    private TextView timerTextView;
    private Handler handler;
    private Runnable runnable;

    private Calendar targetDateTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerTextView = findViewById(R.id.timerTextView);

        // Set the target date and time
        targetDateTime = Calendar.getInstance();
        targetDateTime.set(Calendar.YEAR, 2023);
        targetDateTime.set(Calendar.MONTH, Calendar.DECEMBER);
        targetDateTime.set(Calendar.DAY_OF_MONTH, 31);
        targetDateTime.set(Calendar.HOUR_OF_DAY, 23);
        targetDateTime.set(Calendar.MINUTE, 59);
        targetDateTime.set(Calendar.SECOND, 59);

        // Start updating the timer display
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                updateTimer();
                handler.postDelayed(this, 1000); // Update every second
            }
        };
        handler.postDelayed(runnable, 0); // Start immediately
    }

    private void updateTimer() {
        long currentTime = System.currentTimeMillis();
        long remainingTime = targetDateTime.getTimeInMillis() - currentTime;

        if (remainingTime > 0) {
            long days = remainingTime / (24 * 60 * 60 * 1000);
            long hours = (remainingTime % (24 * 60 * 60 * 1000)) / (60 * 60 * 1000);
            long minutes = (remainingTime % (60 * 60 * 1000)) / (60 * 1000);
            long seconds = (remainingTime % (60 * 1000)) / 1000;

            String timerText = String.format(Locale.getDefault(), "%02d:%02d:%02d:%02d", days, hours, minutes, seconds);
            timerTextView.setText(timerText);
        } else {
            // Timer reached the target date and time
            timerTextView.setText("Timer expired");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable); // Stop updating the timer when the activity is destroyed
    }
}
