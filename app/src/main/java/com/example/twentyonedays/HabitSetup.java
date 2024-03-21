package com.example.twentyonedays;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.FirebaseDatabase;

public class HabitSetup extends AppCompatActivity {

    Spinner habitTypeSpinner, habitFrequencySpinner;
    Button createHabitBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_setup);

        habitTypeSpinner = findViewById(R.id.habit_type_spinner);
        habitFrequencySpinner = findViewById(R.id.habit_frequency_spinner);

        String[] types = getResources().getStringArray(R.array.Types_array);
        ArrayAdapter typeAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, types);
        habitTypeSpinner.setAdapter(typeAdapter);

        String[] fequencies = getResources().getStringArray(R.array.Freq_array);
        ArrayAdapter freqAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, fequencies);
        habitFrequencySpinner.setAdapter(freqAdapter);

        createHabitBtn = findViewById(R.id.create_habit_button);
        createHabitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}