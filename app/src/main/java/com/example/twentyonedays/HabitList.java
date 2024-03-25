package com.example.twentyonedays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class HabitList extends AppCompatActivity {

    ArrayList<HabitModel>Habits = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_list);
        RecyclerView recyclerView = findViewById(R.id.HabitListRecycler);
        setUpHabits();
        HabitAdapter adapter = new HabitAdapter(this, Habits);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    private void setUpHabits(){
        String[] habitNames = getResources().getStringArray(R.array.test_habit_names);
        String[] habitTypes = getResources().getStringArray(R.array.test_habit_types);
        String[] habitFrequencies = getResources().getStringArray(R.array.test_habit_freq);
        String[] habitNums = getResources().getStringArray(R.array.test_habit_nums);

        for(int i = 0; i < habitNames.length; i++){
            Habits.add(new HabitModel(habitNames[i], habitTypes[i], habitFrequencies[i], habitNums[i]));
        }

    }
}