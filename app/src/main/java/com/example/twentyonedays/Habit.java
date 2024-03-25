package com.example.twentyonedays;

import android.widget.SpinnerAdapter;

public class Habit {
    private String id; // Unique ID for the habit
    private String habitName; // Name of the habit
    private String frequency; // Frequency of the habit

    private String habitType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHabitName() {
        return habitName;
    }

    public void setHabitName(String habitName) {
        this.habitName = habitName;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getHabitType() {
        return habitType;
    }

    public void setHabitType(String habitType) {
        this.habitType = habitType;
    }

    // Default constructor required for calls to DataSnapshot.getValue(Habit.class)
    public Habit() {
    }

    // Constructor with parameters
    public Habit(String id, String habitName, String frequency) {
        this.id = id;
        this.habitName = habitName;
        this.frequency = frequency;
    }

    public Habit(String id, String habitName, String frequency, String habitType) {
        this.id = id;
        this.habitName = habitName;
        this.frequency = frequency;
        this.habitType = habitType;
    }


}
