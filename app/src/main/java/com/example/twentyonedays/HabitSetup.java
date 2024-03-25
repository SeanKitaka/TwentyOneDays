package com.example.twentyonedays;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class HabitSetup extends AppCompatActivity {

    Spinner habitTypeSpinner, habitFrequencySpinner;
    Button createHabitBtn;
    EditText habitNameText;
    DatabaseReference databaseHabits;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_setup);

        databaseHabits = FirebaseDatabase.getInstance().getReference("habits");

        habitTypeSpinner = findViewById(R.id.habit_type_spinner);
        habitFrequencySpinner = findViewById(R.id.habit_frequency_spinner);
        habitNameText = findViewById(R.id.habit_name);

        String[] types = getResources().getStringArray(R.array.Types_array);
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, types);
        habitTypeSpinner.setAdapter(typeAdapter);

        String[] frequencies = getResources().getStringArray(R.array.Freq_array);
        ArrayAdapter<String> freqAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, frequencies);
        habitFrequencySpinner.setAdapter(freqAdapter);

        createHabitBtn = findViewById(R.id.create_habit_button);
        createHabitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addHabit();
            }
        });
    }

    private void addHabit() {
        String habitName = habitNameText.getText().toString().trim();
        String habitType = habitTypeSpinner.getSelectedItem().toString();
        String frequency = habitFrequencySpinner.getSelectedItem().toString();

        // Validate input fields
        if (habitName.isEmpty()) {
            Toast.makeText(HabitSetup.this, "Please enter habit name", Toast.LENGTH_SHORT).show();
            return;
        }

        if (habitType.equals("Select")) {
            Toast.makeText(HabitSetup.this, "Please select habit type", Toast.LENGTH_SHORT).show();
            return;
        }

        if (frequency.equals("Select")) {
            Toast.makeText(HabitSetup.this, "Please select habit frequency", Toast.LENGTH_SHORT).show();
            return;
        }

        // Add habit to database
        String habitId = databaseHabits.push().getKey();
        Habit habit = new Habit(habitId, habitName, habitType, frequency);
        databaseHabits.child(habitId).setValue(habit);

        Toast.makeText(HabitSetup.this, "Habit added successfully", Toast.LENGTH_SHORT).show();
        finish(); // Finish the activity and go back to the previous one
    }
}
