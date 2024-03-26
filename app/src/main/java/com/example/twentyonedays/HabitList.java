package com.example.twentyonedays;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HabitList extends AppCompatActivity implements RecyclerInterface {

    ArrayList<HabitModel> Habits = new ArrayList<>();
    DatabaseReference habitsRef;
    HabitAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_list);

        RecyclerView recyclerView = findViewById(R.id.HabitListRecycler);

        adapter = new HabitAdapter(this, Habits, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        setUpHabits();
    }

    private void setUpHabits() {
        // Get reference to the "habits" node under the current user's ID
        String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        habitsRef = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("habits");

        // Add a ValueEventListener to fetch habit data from Firebase
        habitsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Clear existing habits
                Habits.clear();

                // Iterate through each habit and add it to the list
                for (DataSnapshot habitSnapshot : dataSnapshot.getChildren()) {
                    HabitModel habit = habitSnapshot.getValue(HabitModel.class);
                    if (habit != null) {
                        Habits.add(habit);
                    }
                }

                // Notify the adapter that the data set has changed
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("HabitList", "Error fetching habits from Firebase: " + databaseError.getMessage());
            }
        });
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(HabitList.this, HabitDisplay.class);
        intent.putExtra("habitName", Habits.get(position).getHabitName());
        intent.putExtra("habitType", Habits.get(position).getHabitType());
        intent.putExtra("habitFreq", Habits.get(position).getHabitFreq());
        intent.putExtra("habitNum", Habits.get(position).getHabitNum());

        startActivity(intent);
    }
}
