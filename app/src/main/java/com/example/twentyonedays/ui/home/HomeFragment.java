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

import java.util.ArrayList;

public class HomeFragment extends Fragment implements RecyclerInterface {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    private HabitAdapter adapter;
    ArrayList<HabitModel> Habits = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        RecyclerView recyclerView = root.findViewById(R.id.HabitRecyclerView);
        setUpHabits();
        adapter = new HabitAdapter(getActivity(), Habits, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(requireActivity(), HabitDisplay.class);
        intent.putExtra("habitName", Habits.get(position).getHabitName());
        intent.putExtra("habitType", Habits.get(position).getHabitType());
        intent.putExtra("habitFreq", Habits.get(position).getHabitFreq());
        intent.putExtra("habitNum", Habits.get(position).getHabitNum());

        startActivity(intent);
    }
}