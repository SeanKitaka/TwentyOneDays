package com.example.twentyonedays;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HabitDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_display);

        // Get the intent that started this activity
        Bundle extras = getIntent().getExtras();

        // Check if extras bundle is not null
        if (extras != null) {
            // Get habit information from extras bundle
            String habitName = extras.getString("habitName");
            String habitType = extras.getString("habitType");
            String habitFreq = extras.getString("habitFreq");
            String habitNum = extras.getString("habitNum");

            // Find TextViews in the layout
            TextView habitNameTextView = findViewById(R.id.habitNameTextView);
            TextView habitTypeTextView = findViewById(R.id.habitTypeTextView);
            TextView habitFreqTextView = findViewById(R.id.habitFreqTextView);
            TextView habitNumTextView = findViewById(R.id.habitNumTextView);

            // Set habit information to TextViews
            habitNameTextView.setText("Habit Name: " + habitName);
            habitTypeTextView.setText("Habit Type: " + habitType);
            habitFreqTextView.setText("Habit Frequency: " + habitFreq);
            habitNumTextView.setText("Habit Number: " + habitNum);
        }


    }
}