package com.example.twentyonedays.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twentyonedays.HabitAdapter;
import com.example.twentyonedays.HabitDisplay;
import com.example.twentyonedays.HabitList;
import com.example.twentyonedays.HabitModel;
import com.example.twentyonedays.R;
import com.example.twentyonedays.RecyclerInterface;
import com.example.twentyonedays.databinding.FragmentHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements RecyclerInterface {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private HabitAdapter adapter;
    private ArrayList<HabitModel> habits = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = root.findViewById(R.id.HabitRecyclerView);

        adapter = new HabitAdapter(getActivity(), habits, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fetchHabitsFromFirebase();
        return root;
    }

    private void fetchHabitsFromFirebase() {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String uid = currentUser.getUid();
            DatabaseReference habitsRef = FirebaseDatabase.getInstance().getReference().child("users").child(uid).child("habits");

            habitsRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    habits.clear();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        HabitModel habit = snapshot.getValue(HabitModel.class);
                        if (habit != null) {
                            habits.add(habit);
                        }
                    }
                    adapter.notifyDataSetChanged();
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    // Handle error
                }
            });
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(requireActivity(), HabitDisplay.class);
        intent.putExtra("habitName", habits.get(position).getHabitName());
        intent.putExtra("habitType", habits.get(position).getHabitType());
        intent.putExtra("habitFreq", habits.get(position).getHabitFreq());
        intent.putExtra("habitNum", habits.get(position).getHabitNum());

        startActivity(intent);
    }
}