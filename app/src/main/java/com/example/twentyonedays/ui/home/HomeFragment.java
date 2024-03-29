package com.example.twentyonedays.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.twentyonedays.HabitAdapter;
import com.example.twentyonedays.HabitDisplay;
import com.example.twentyonedays.HabitModel;
import com.example.twentyonedays.R;
import com.example.twentyonedays.RecyclerInterface;
import com.example.twentyonedays.databinding.FragmentHomeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements RecyclerInterface {

    private FragmentHomeBinding binding;
    private HabitAdapter adapter;
    private ArrayList<HabitModel> habits = new ArrayList<>();
    private ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = root.findViewById(R.id.HabitRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Show loading indicator
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        String uid = getActivity().getIntent().getStringExtra("uid");
        pullDBData(uid);

        adapter = new HabitAdapter(getActivity(), habits, this);
        recyclerView.setAdapter(adapter);

        return root;
    }

    private void pullDBData(String uid) {
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(uid).child("habits");
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    habits.clear();
                    for (DataSnapshot data : snapshot.getChildren()) {
                        String habitName = data.child("habitName").getValue(String.class);
                        String habitType = data.child("habitType").getValue(String.class);
                        String habitFreq = data.child("habitFreq").getValue(String.class);
                        String habitNum = data.child("habitNum").getValue(String.class);

                        // Assuming timestamp is stored as a String
                        String timestamp = data.child("timestamp").getValue(String.class);
                        String daysDifference = calculateDaysDifference(timestamp);

                        habits.add(new HabitModel(habitName, habitType, habitFreq, habitNum, daysDifference));
                    }
                    adapter.notifyDataSetChanged();
                } else {
                    Log.d("Firebase", "No data exists at the specified path");
                }

                // Dismiss loading indicator after data retrieval
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Error reading data: " + error.getMessage());

                // Dismiss loading indicator if data retrieval fails
                progressDialog.dismiss();
            }
        };
        reference.addListenerForSingleValueEvent(eventListener);
    }

    private String calculateDaysDifference(String timestamp) {
        // Implement your method to calculate days difference
        return ""; // Placeholder, implement your logic here
    }

    @Override
    public void onItemClick(int position) {
        // Handle item click
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
